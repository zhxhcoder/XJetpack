package com.zhxh.xjetpack;

/**
 * Created by zhxh on 2018/8/9
 */
class JniCall private constructor() {
    companion object {

        val instance: JniCall by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            JniCall()
        }

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    external fun stringFromJNI(): String

}

