#include <iostream>
#include <jni.h>

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_myapplication_jni_JNILoader_intFromJNI(JNIEnv *env, jobject thiz) {
    return 5;
}