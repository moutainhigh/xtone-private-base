#ifndef _MAINMENULAYER_H_
#define _MAINMENULAYER_H_

#include "cocos2d.h"
#include "PayHelper.h"

class MainMenuLayer : public cocos2d::CCLayer, public IPayHelper
{
public:
	MainMenuLayer();
	~MainMenuLayer();
	CREATE_FUNC(MainMenuLayer);
	virtual bool init();
	virtual void onEnter();
	virtual void onExit();
	virtual void keyBackClicked();

	void resumeMainMenu();

	void enableInput();
	void disableInput();
	bool isInputEnabled();
	void enableTouchEvent();
	void disableTouchEvent();
	void enableKeypad();
	void disableKeypad();

	//payhelper
	//virtual void paymentCallback(int retcode, int diamonds);
	virtual void paymentCallback(int retcode, const char *orderNo);
	virtual void notifyCallback();
	
	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);
	//touches
	//virtual void ccTouchesBegan(cocos2d::CCSet *touches, cocos2d::CCEvent *pEvent);
	virtual void ccTouchesEnded(cocos2d::CCSet* touches, cocos2d::CCEvent* event);
	void registerWithTouchDispatcher();

	void myAccount();
	void callAccount(CCObject *sender);

	void notifyHandle(CCObject* obj);

private:
	void startNewPuzzle();
	void resumePuzzle();
	void showShop();
	void showHelp();
	void showExchange();
	void showGift();
	void createUI();

	void showGiftSchedule(float dt);
	void showUISchedule(float dt);

	bool m_isRequestedGift;
	bool m_isShowed;

	cocos2d::CCSprite *m_newGame;
	cocos2d::CCSprite *m_resumeGame;
	cocos2d::CCSprite *m_shop;

	cocos2d::CCSprite *m_exchange;
	cocos2d::CCSprite *m_gift;
	cocos2d::CCSprite *m_moreGame;

	cocos2d::CCSprite *m_help;
	cocos2d::CCSprite *m_sound;
	cocos2d::CCSpriteFrame *m_muteFrame;
	cocos2d::CCSpriteFrame *m_soundFrame;
};

#endif /* _MAINMENULAYER_H_ */
