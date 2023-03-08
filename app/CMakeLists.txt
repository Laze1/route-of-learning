cmake_minimum_required(VERSION 3.22.1)

add_library(native-clib
        SHARED
        src/main/jni/native-clib.cpp)