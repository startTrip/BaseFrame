#include "com_mihua_frameproject_ndk_Hello.h"

JNIEXPORT jstring JNICALL Java_com_mihua_frameproject_ndk_Hello_getStringFromC
  (JNIEnv * env, jclass jclz){

  //返回一句话
      return (*env)->NewStringUTF(env,"JNI hahahahahahahaha");

 };
