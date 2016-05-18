#ifndef __MainLayer_H__
#define __MainLayer_H__

#include "cocos2d.h"
#include "cocos-ext.h"
#include "Contants.h"

USING_NS_CC;
USING_NS_CC_EXT;
using namespace gui;

class MainLayer : public CCLayer
{
public:
	MainLayer();
	~MainLayer();

	virtual bool init();
	CREATE_FUNC(MainLayer);

	virtual void onEnter();
	virtual void onExit();

	void doFloatAction();
	void doBlinkAction();
	void switchPets();
	void switchCallback(CCNode *pSender);
	void setPriceTipText(PetType type);
	void updatePetImage(PetType type);
	void startGame();
	bool isPetOpen(PetType pet);

	//我添加的登陆
	void loginUserCallback(CCObject* sender, TouchEventType type);

	//购买100个樱桃
	void buyCherryCallback(CCObject *sender);
	//退出登录
	void  logoutCallback(CCObject *sender);

	 void menuCloseCallback(CCObject* pSender);
	 void notifyHandle(CCObject* obj);

	void updateMainText(int cherry, int times, int bestScore);

	void pressPetImageCallback(CCObject* sender, TouchEventType type);
	void pressStartButtonCallback(CCObject* sender, TouchEventType type);
	void pressChangeRoleCallback(CCObject* sender, TouchEventType type);

	void enableKeypad()
	{
		this->setKeypadEnabled(true);
	}

	void disableKeypad()
	{
		this->setKeypadEnabled(false);
	}

	virtual void keyBackClicked();

	//同步数据到网络

	//彩票
	void lotteryCallback(CCObject *sender);



private:
	UILayer *m_uiLayer;
	UILayout *m_layoutWidget;
	UIImageView *m_petUIImage;
	UIImageView *m_startUIImage;
	UILabelBMFont *m_changeLabel;

	UILabelBMFont *m_cherryLabel;
	UILabelBMFont *m_bestScoreLabel;
	UILabelBMFont *m_gameTimesScoreLabel;

	UILabel *m_priceTipsLabel;
	PetType m_petType;
	CCPoint m_centerPos;
};


#endif //#ifndef __MainLayer_H__