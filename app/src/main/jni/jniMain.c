//
// Created by  wangmin on 2018/4/9.
//

#include "jniMain.h"

JNIEXPORT jstring JNICALL Java_com_mihua_frameproject_ndk_NdkDemo_getStringFromC
  (JNIEnv * env, jclass jclz){



    return (*env)->NewStringUTF(env,"c string");

 };
