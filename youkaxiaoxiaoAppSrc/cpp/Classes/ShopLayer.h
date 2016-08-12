#ifndef _SHOPLAYER_H_
#define _SHOPLAYER_H_

#include "cocos2d.h"
#include "PayHelper.h"

class ShopLayer : public cocos2d::CCLayer, public IPayHelper
{
public:
	ShopLayer();
	~ShopLayer();
	CREATE_FUNC(ShopLayer);
	virtual bool init();
	virtual void onEnter();
	virtual void onExit();
	virtual void keyBackClicked();
	void quit();

	int getDiamonds( const char *orderNo );

	//¼àÌý¸¶·Ñ
	//virtual void paymentCallback(int retcode, int diamonds);
	virtual void paymentCallback(int retcode, const char *orderNo);

	void createListMenu();
	cocos2d::CCNode *createListItem(int diamondNum, int giveDiamondNum, int dollar);

	void updateDiamond();
	void scheduleDiamondLabels(float dt);

	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);
	//touches
	virtual void ccTouchesEnded(cocos2d::CCSet* touches, cocos2d::CCEvent* event);
	void registerWithTouchDispatcher();
private:
	cocos2d::CCSprite *m_closeBtn;
	cocos2d::CCLabelTTF *m_diamondTotalLabel;
	int m_needAddDiamonds;
};


#endif /* _SHOPLAYER_H_ */
