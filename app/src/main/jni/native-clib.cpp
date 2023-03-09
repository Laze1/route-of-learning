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