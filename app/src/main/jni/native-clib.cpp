#include <iostream>
#include <jni.h>
#include <jni.h>

extern "C" jint
Java_com_example_myapplication_jni_JNIClass_intFromJNI(JNIEnv *env, jobject thiz) {
    return 8;
}
