#include <iostream>
#include <jni.h>

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_myapplication_jni_JNILoader_intFromJNI(JNIEnv *env, jobject thiz) {
    return 5;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_myapplication_jni_JNILoader_stringFromJNI(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("success");
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_myapplication_jni_JNILoader_setJniName(JNIEnv *env, jobject thiz, jstring name) {
    jclass jniGetInfoClass = env->FindClass("com/example/myapplication/jni/JniGetInfo");
    jmethodID setJniNameMethod = env->GetStaticMethodID(jniGetInfoClass, "setJniName", "(Ljava/lang/String;)V");
    env->CallStaticVoidMethod(jniGetInfoClass, setJniNameMethod, name);

    env->DeleteLocalRef(name);

}