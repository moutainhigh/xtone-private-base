#ifndef _EXCHANGELAYER_H_
#define _EXCHANGELAYER_H_

#include "cocos2d.h"
#include "PayHelper.h"
using namespace cocos2d;

class ExchangeLayer : public cocos2d::CCLayer, public cocos2d::CCTextFieldDelegate, public IPayHelper
{
public:
	ExchangeLayer();
	~ExchangeLayer();
	CREATE_FUNC(ExchangeLayer);
	virtual bool init();
	virtual void onEnter();
	virtual void keyBackClicked();

	void exchange();
	void quit();
	void updateTipsWithCode(int retcode, int diamonds);

	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);
	//touches
	virtual bool ccTouchBegan(cocos2d::CCTouch *pTouch, cocos2d::CCEvent *pEvent);
	void registerWithTouchDispatcher();

	//��������
	//virtual void paymentCallback(int retcode, int diamonds);
	virtual void paymentCallback(int retcode, const char *orderNo);
	void scheduleDiamondLabels( float dt );

	//���봦��
	//���û������������ʱ�Ļص�����
	virtual bool onTextFieldAttachWithIME(CCTextFieldTTF *pSender);
	//���û��ر��������ʱ�Ļص�����
	virtual bool onTextFieldDetachWithIME(CCTextFieldTTF *pSender);
	//���û���������ʱ�Ļص�����
	virtual bool onTextFieldInsertText(CCTextFieldTTF * sender, const char * text, int nLen);
	//���û�ɾ������ʱ�Ļص�����
	virtual bool onTextFieldDeleteBackward(CCTextFieldTTF * sender, const char * delText, int nLen);
private:
	cocos2d::CCSprite *m_close;
	cocos2d::CCLabelTTF *m_cdkeyTips;
	cocos2d::CCSprite *m_fetchBtn;
	cocos2d::CCSprite *m_menuTextBtn;
	cocos2d::CCTextFieldTTF *m_textFieldTTF;
	std::string m_string;
	cocos2d::CCLabelTTF *m_cdkeyLabel;
	int m_needAddDiamonds;
};


#endif /* _EXCHANGELAYER_H_ */
