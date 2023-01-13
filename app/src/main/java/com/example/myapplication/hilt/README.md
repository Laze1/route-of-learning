[TOC]
### Hilt 注解
##### @HiltAndroidApp
所有使用 Hilt 的应用都必须包含一个带有 `@HiltAndroidApp` 注解的 `Application` 类。
`@HiltAndroidApp` 会触发 Hilt 的代码生成操作，生成的代码包括应用的一个基类，该基类充当应用级依赖项容器。

##### @AndroidEntryPoint
在 `Application` 类中设置了 Hilt 且有了应用级组件后，Hilt 可以为带有 `@AndroidEntryPoint` 注解的其他 `Android` 类提供依赖项

```kotlin
@AndroidEntryPoint
class ExampleActivity : AppCompatActivity() {  }
```

如果您使用 `@AndroidEntryPoint` 为某个 `Android` 类添加注解，则还必须为依赖于该类的 `Android` 类添加注解。
例如，如果您为某个 `fragment` 添加注解，则还必须为使用该 `fragment` 的所有 `activity` 添加注解。

>在 Hilt 对 Android 类的支持方面适用以下几项例外情况：
Hilt 仅支持扩展 ComponentActivity 的 activity，如 AppCompatActivity。
Hilt 仅支持扩展 androidx.Fragment 的 Fragment。
Hilt 不支持保留的 fragment。

`@AndroidEntryPoint` 会为项目中的每个 Android 类生成一个单独的 Hilt 组件。
这些组件可以从它们各自的父类接收依赖项，如组件层次结构中所述。

##### @Inject
如需从组件获取依赖项，请使用 `@Inject` 注解执行字段注入
```kotlin
class AnalyticsAdapter @Inject constructor(
  private val service: AnalyticsService
) {  }
```
在一个类的代码中，带有注解的构造函数的参数即是该类的依赖项。
在本例中，`AnalyticsService` 是 `AnalyticsAdapter` 的一个依赖项。
因此，Hilt 还必须知道如何提供 `AnalyticsService` 的实例。
>在构建时，Hilt 会为 Android 类生成 Dagger 组件。然后，Dagger 会走查您的代码，并执行以下步骤：
构建并验证依赖关系图，确保没有未满足的依赖关系且没有依赖循环。
生成它在运行时用来创建实际对象及其依赖项的类。

##### @Module @InstallIn
与 Dagger 模块一样，它会告知 Hilt 如何提供某些类型的实例。
与 Dagger 模块不同的是，您必须使用 `@InstallIn` 为 Hilt 模块添加注解，以告知 Hilt 每个模块将用在或安装在哪个 Android 类中。

##### @Binds
如果 `AnalyticsService` 是一个接口，则您无法通过构造函数注入它，而应向 Hilt 提供绑定信息，方法是在 Hilt 模块内创建一个带有 `@Binds` 注解的抽象函数。
@Binds 注解会告知 Hilt 在需要提供接口的实例时要使用哪种实现。

带有注解的函数会向 Hilt 提供以下信息：
* 函数返回类型会告知 Hilt 该函数提供哪个接口的实例。
* 函数参数会告知 Hilt 要提供哪种实现。

```kotlin
interface AnalyticsService {
  fun analyticsMethods()
}

// Constructor-injected, because Hilt needs to know how to
// provide instances of AnalyticsServiceImpl, too.
class AnalyticsServiceImpl @Inject constructor(
  
) : AnalyticsService {  }

@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule {

  @Binds
  abstract fun bindAnalyticsService(
    analyticsServiceImpl: AnalyticsServiceImpl
  ): AnalyticsService
}
```
Hilt 模块 `AnalyticsModule` 带有 `@InstallIn(ActivityComponent::class)` 注解，
因为您希望 Hilt 将该依赖项注入 `ExampleActivity`。此注解意味着，`AnalyticsModule` 中的所有依赖项都可以在应用的所有 `activity` 中使用。

##### @Provides 
接口不是无法通过构造函数注入类型的唯一一种情况。如果某个类不归您所有（因为它来自外部库，如 `Retrofit`、`OkHttpClient` 或 `Room` 数据库等类），或者必须使用构建器模式创建实例，也无法通过构造函数注入。

接着前面的例子来讲。如果 `AnalyticsService` 类不直接归您所有，您可以告知 Hilt 如何提供此类型的实例，方法是在 Hilt 模块内创建一个函数，并使用 @Provides 为该函数添加注解。

带有注解的函数会向 Hilt 提供以下信息：
* 函数返回类型会告知 Hilt 函数提供哪个类型的实例。
* 函数参数会告知 Hilt 相应类型的依赖项。
* 函数主体会告知 Hilt 如何提供相应类型的实例。每当需要提供该类型的实例时，Hilt 都会执行函数主体。

```kotlin
@Module
@InstallIn(ActivityComponent::class)
object AnalyticsModule {

  @Provides
  fun provideAnalyticsService(
    // Potential dependencies of this type
  ): AnalyticsService {
      return Retrofit.Builder()
               .baseUrl("https://example.com")
               .build()
               .create(AnalyticsService::class.java)
  }
}
```

### 应用
##### 为同一类型提供多个绑定
如果您需要让 Hilt 以依赖项的形式提供同一类型的不同实现，必须向 Hilt 提供多个绑定。您可以使用限定符为同一类型定义多个绑定。

限定符是一种注解，当为某个类型定义了多个绑定时，您可以使用它来标识该类型的特定绑定。

仍然接着前面的例子来讲。如果需要拦截对 `AnalyticsService` 的调用，您可以使用带有拦截器的 `OkHttpClient` 对象。对于其他服务，您可能需要以不同的方式拦截调用。在这种情况下，您需要告知 Hilt 如何提供两种不同的 OkHttpClient 实现。

首先，定义要用于为 `@Binds` 或 `@Provides` 方法添加注解的限定符：
```kotlin
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient
```
然后，Hilt 需要知道如何提供与每个限定符对应的类型的实例。在这种情况下，您可以使用带有 `@Provides` 的 Hilt 模块。这两种方法具有相同的返回类型，但限定符将它们标记为两个不同的绑定：
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @AuthInterceptorOkHttpClient
  @Provides
  fun provideAuthInterceptorOkHttpClient(
    authInterceptor: AuthInterceptor
  ): OkHttpClient {
      return OkHttpClient.Builder()
               .addInterceptor(authInterceptor)
               .build()
  }

  @OtherInterceptorOkHttpClient
  @Provides
  fun provideOtherInterceptorOkHttpClient(
    otherInterceptor: OtherInterceptor
  ): OkHttpClient {
      return OkHttpClient.Builder()
               .addInterceptor(otherInterceptor)
               .build()
  }
}
```
您可以通过使用相应的限定符为字段或参数添加注解来注入所需的特定类型：
```kotlin
// As a dependency of another class.
@Module
@InstallIn(ActivityComponent::class)
object AnalyticsModule {

  @Provides
  fun provideAnalyticsService(
    @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
  ): AnalyticsService {
      return Retrofit.Builder()
               .baseUrl("https://example.com")
               .client(okHttpClient)
               .build()
               .create(AnalyticsService::class.java)
  }
}

// As a dependency of a constructor-injected class.
class ExampleServiceImpl @Inject constructor(
  @AuthInterceptorOkHttpClient private val okHttpClient: OkHttpClient
) : X

// At field injection.
@AndroidEntryPoint
class ExampleActivity: AppCompatActivity() {

  @AuthInterceptorOkHttpClient
  @Inject lateinit var okHttpClient: OkHttpClient
}
```

##### Hilt 中的预定义限定符
Hilt 提供了一些预定义的限定符。例如，由于您可能需要来自应用或 `activity` 的 `Context` 类，因此 Hilt 提供了 `@ApplicationContext` 和 `@ActivityContext` 限定符。

假设本例中的 `AnalyticsAdapter` 类需要 `activity` 的上下文。以下代码演示了如何向 `AnalyticsAdapter` 提供 `activity` 上下文：
```kotlin
class AnalyticsAdapter @Inject constructor(
    @ActivityContext private val context: Context,
    private val service: AnalyticsService
) {  }
```
