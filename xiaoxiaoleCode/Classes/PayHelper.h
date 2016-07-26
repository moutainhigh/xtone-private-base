#ifndef __PayHelper_H__
#define __PayHelper_H__

#include "cocos2d.h"

#define 	PAY_REQUEST_OK 		(0)
#define		PAY_RESULT_FAIL		(1)

struct ShopData
{
	int diamonds;
	int gives;
	int dollar;
	const char *orderId;
};

static const ShopData s_shop_data[] = {
	{10, 5, 200, "001"},
	{20, 10, 400, "002"},
	{30, 20, 600, "003"},
	{40, 35, 800, "004"},
	{50, 50, 1000, "005"},
	{60, 60, 1200, "006"},
	{70, 70, 1500, "007"},
	{80, 80, 2000, "008"},
	{90, 90, 2500, "009"},
	{100, 100, 3000, "010"}
};

struct IPayHelper
{
	IPayHelper() {}
	virtual ~IPayHelper() {}
	virtual void paymentCallback(int retcode, const char *orderNo) = 0;
};

class PayHelper : public cocos2d::CCNode
{
public:
	PayHelper();
	virtual ~PayHelper() {};
	static PayHelper* instance();

	void initialize();
	void payment(int dollar, const char *orderNo, const char *orderDesc);
	void addDelegate(IPayHelper *pDelegate){ m_payDelegate = pDelegate; };
	void removeDelegate() { m_payDelegate = NULL; };
	
	void resultCallback(int code, const char *orderNo);

	void updateTimer(float dt);

private:
	static PayHelper *m_instance;
	IPayHelper *m_payDelegate;
	int m_retCode;
	int m_retResult;
	std::string m_retOrderId;
	bool m_isPayingReturn;
};


#ifdef __cplusplus
extern "C" {
#endif

extern void startPay(int dollar, const char *orderNo, const char *orderDesc);

//jni
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include "platform/android/jni/JniHelper.h"

JNIEXPORT void JNICALL Java_com_huaxuan_heart_JniHelper_payResultNative(JNIEnv* env, jobject thiz, jint code, jstring orderNo);
#endif  //#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

#ifdef __cplusplus
}
#endif


#endif //#ifndef __PayHelper_H__