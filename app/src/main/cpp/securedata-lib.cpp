#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_app_myinapp_data_network_NetworkConstants_getAPIKEY(JNIEnv *env, jobject thiz) {
    std::string string = "x1rqvPKlrWj15hUunRzWy6T9WXLpJrXGFVcWi7fxaH1rb96FmSaVWKed";
    return env->NewStringUTF(string.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_app_myinapp_data_network_NetworkConstants_getBaseURL(JNIEnv *env, jobject thiz) {
    std::string string = "api.pexels.com/v1";
    return env->NewStringUTF(string.c_str());
}