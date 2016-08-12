#ifndef _InGameFetchLayer_H_
#define _InGameFetchLayer_H_

#include "cocos2d.h"
#include "PayHelper.h"

class InGameFetchLayer : public cocos2d::CCLayer, public IPayHelper
{
public:
	InGameFetchLayer();
	~InGameFetchLayer();
	CREATE_FUNC(InGameFetchLayer);
	virtual bool init();
	virtual void onEnter();
	virtual void keyBackClicked();
	void quit();

	void getGift();
	//void showExchange();

	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);
	//touche
	virtual bool ccTouchBegan(cocos2d::CCTouch *pTouch, cocos2d::CCEvent *pEvent);
	void registerWithTouchDispatcher();

	//¼àÌý¸¶·Ñ
	//virtual void paymentCallback(int retcode, int diamonds);
	virtual void paymentCallback(int retcode, const char *orderNo);
	void scheduleDiamondLabels( float dt );
private:
	cocos2d::CCSprite *m_confirmBtn;
	cocos2d::CCSprite *m_cancelBtn;
	cocos2d::CCSprite *m_exchange;
	int m_needAddDiamonds;
};


#endif /* _InGameFetchLayer_H_ */
