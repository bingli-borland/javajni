package com.leancloud.api.web.util;

import com.leancloud.api.web.function.jni.HelloWorld;

public class NativeUtils {

    static {
        System.loadLibrary("hello");
    }

    public static void test() {
        new HelloWorld().hello();
    }
}
