#include "PayHelper.h"

using namespace cocos2d;

PayHelper* PayHelper::m_instance = NULL;

PayHelper::PayHelper()
{
	m_payDelegate = NULL;
	m_retCode = -1;
	m_retResult = 0;
	m_isPayingReturn = false;

	this->onEnter();
	this->onEnterTransitionDidFinish();
}

PayHelper* PayHelper::instance()
{
	if (!m_instance)
		m_instance = new PayHelper;
		
	return m_instance;
}

void PayHelper::initialize()
{
	this->schedule(schedule_selector(PayHelper::updateTimer), 0.3f);
}

void PayHelper::payment(int dollar, const char *orderNo, const char *orderDesc)
{
	m_isPayingReturn = false;
	startPay(dollar, orderNo, orderDesc);
}

void PayHelper::resultCallback(int code, const char *orderNo)
{
	CCLOG("PayHelper code = %d", code);
	m_retCode = code;
	m_retOrderId = orderNo;

	m_isPayingReturn = true;
}

void PayHelper::updateTimer( float dt )
{
	if ( m_isPayingReturn && m_payDelegate ) {
		m_payDelegate->paymentCallback(m_retCode, m_retOrderId.c_str());
		m_retCode = -1;
		m_retResult = 0;
		m_retOrderId.clear();

		m_isPayingReturn = false;
	}
}

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

#define JAVA_PACKAGE_NAME "com/huaxuan/heart/JniHelper"

//
//C==>JAVA
//
void startPay(int dollar, const char *orderNo, const char *orderDesc)
{
	JniMethodInfo minfo;
	bool isHave = JniHelper::getStaticMethodInfo(minfo, JAVA_PACKAGE_NAME, "startPayment", "(ILjava/lang/String;Ljava/lang/String;)V");

	jstring strOrderNo = minfo.env->NewStringUTF(orderNo);
	jstring strOrderDesc = minfo.env->NewStringUTF(orderDesc);

	if(!isHave) {
		CCLog("jni:startPay not find");
	} else {
		minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID, dollar, strOrderNo, strOrderDesc);

		//release
		minfo.env->DeleteLocalRef(strOrderNo);
		minfo.env->DeleteLocalRef(strOrderDesc);
		minfo.env->DeleteLocalRef(minfo.classID);
		CCLog("jni:startPay ok"); 
	}
}

#ifdef __cplusplus
extern "C" {
#endif
//
//JAVA==>C
//
JNIEXPORT void JNICALL Java_com_huaxuan_heart_JniHelper_payResultNative(JNIEnv* env, jobject thiz, jint code, jstring orderNo)
{
	CCLOG("JAVA-->C code = %d", code);
	
	const char* pszText = env->GetStringUTFChars(orderNo, NULL);

	CCLOG("JAVA-->C orderNo = %s", pszText);

	//PayHelper
	PayHelper::instance()->resultCallback(code, pszText);

	//release
	env->ReleaseStringUTFChars(orderNo, pszText);
}

//
//JNIEXPORT void JNICALL Java_org_cocos2dx_lib_Cocos2dxRenderer_nativeInsertText(JNIEnv* env, jobject thiz, jstring text) {
//	const char* pszText = env->GetStringUTFChars(text, NULL);
//	cocos2d::CCIMEDispatcher::sharedDispatcher()->dispatchInsertText(pszText, strlen(pszText));
//	env->ReleaseStringUTFChars(text, pszText);
//}

#ifdef __cplusplus
}
#endif

#else //win32

void startPay(int dollar, const char *orderNo, const char *orderDesc)
{
	PayHelper::instance()->resultCallback(0, orderNo);
}


#endif //#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
