#ifndef __GAMESCENE_H__
#define __GAMESCENE_H__

#include "cocos2d.h"
#include "StarPuzzle.h"
#include "PayHelper.h"

class GameLayer;
class MainMenuLayer;
class StarPuzzle;
class GameOverLayer;
class GamePauseLayer;
class FetchLayer;
class FireworkLayer;

#define MAX_STAGE 10
#define DESIGN_WIDTH	(480)
#define DESIGN_HEIGHT   (800)

class GameScene
{
public:
	virtual ~GameScene();
	static GameScene* instance();
	static cocos2d::CCScene* scene() { return instance()->m_scene; }

	void showMainMenu();
	void showGameOver();
	void showPause();
	void showFetchLayer();
	void returnToMainMenuFromShop();
	void returnToMainMenuFromHelp();
	void returnToMainMenuFromExchange();
	void returnToMainMenuFromGift();
	void buyDiamonds(int dollar, const char *orderId, const char *orderDesc);
	void resumeGameFromPause();
	void returnToMainMenuFromPause();
	void closeGameOver();
	void continueGameOver();
	void startNewPuzzle();
	void resumePuzzle();
	void playEffect(const char *file);
	void useBrushByType(int type, int index);
	void returnToGameLayerFromBrushLayer();
	void returnToGameLayerFromFetchLayer();
	void returnToGameLayerFromInGameFetchLayer();
	void firework();
	void stopFirework();
	void delayedFire();
	cocos2d::CCSize visibleSize() { return m_visibleSize; }
	cocos2d::CCPoint visibleOrigin() { return m_origin; }
	
private:
	GameScene();
	cocos2d::CCScene *m_scene;
	GameLayer *m_gameLayer;
	MainMenuLayer *m_mainMenuLayer;
	GameOverLayer *m_gameOverLayer;
	GamePauseLayer *m_gamePauseLayer;
	FetchLayer *m_fetchLayer;
	FireworkLayer *m_fireworkLayer;
	static GameScene *m_instance;

	cocos2d::CCSize m_visibleSize;
	cocos2d::CCPoint m_origin;
};

class Status
{
public:
	bool mute;
	int highScore;
	int stage;
	int score;
	int target;
	int lastTarget;

	Status()
	{
		mute = false;
		highScore = 0;
		stage = 0;
		score = 0;
		target = 0;
		lastTarget = 0;
	}
};

class GameLayer : public cocos2d::CCLayer, public IPayHelper
{
public:
	GameLayer();
	~GameLayer();
	CREATE_FUNC(GameLayer);
    virtual bool init();
	virtual void onEnter();

	void resumePuzzle(std::string puzzle, int row, int col, int stage, int score, int target);
	void createPuzzle(int row, int col);
	void savePuzzle(bool bGameOver);
	void enableInput();
	void disableInput();
	bool isInputEnabled();
	void enableTouchEvent();
	void disableTouchEvent();
	void enableKeypad();
	void disableKeypad();
	void newStage();
	void nextStage();
	int target(int stage);
	void returnToMainMenu();
	void resetStatus();
	void showBrush(float arrowX, float y, int index);
	void useBrush(int starType, int index);

	void gamelayerWinAnimation();
	void backStageStartScore();
	void registPaymentDelegate();

	//touches
	virtual void ccTouchesBegan(cocos2d::CCSet *touches, cocos2d::CCEvent *pEvent);
	virtual void ccTouchesEnded(cocos2d::CCSet* touches, cocos2d::CCEvent* pEvent);

	//key
	virtual void keyBackClicked();

	//payment result
	//virtual void paymentCallback(int retcode, int diamonds);
	virtual void paymentCallback(int retcode, const char *orderNo);

private:
	enum Animation
	{
		GameOver,
		WinPuzzle,
		NextStage,
		MainMenu,
		PuzzleEnd,
		ExplodeTailStar,
		ExplodeRemainStars,
		PopOneStar,
		BonusMoveOut,
		StartGame,
		refreshTurnOver,
		refreshOver,
		startTips,
		PopstarEnd
	};

	typedef enum
	{
		PropNone,
		PropBomb,
		PropBrush,
		PropRefresh
	}PropType;

	void checkPuzzleSolved();

	void scheduleAnimation(Animation anim, float delay = 0.0f);
	void explodeTailStarAnimation();
	void explodeRemainStarAnimation();
	void explodeOneStar();
	void puzzleEnd();
	void gameOverAnimation();
	void winPuzzleAnimation();
	void refreshTurnOverAnimation();
	void refreshOverAnimation();
	void popstarEndCallback();
	void startTipTimer();
	void stopTipTimer();
	void updateScoreLabel();
	void updateStatus();
	void updateComboLabel(int combos);
	void updatePropTipsLabel(PropType type);
	void updateHighScore(int score);
	void addScore(int score);
	void updateDiamond();

	void bonusMoveOutCallback();
	void bonusMoveInCallback();
	void bonusSpawnOutAnimation();
	void bonusMoveOutAnimation();
	void addBonusCallback(CCNode *node, void *param);
	void goodComboAnimation(int count);
	void starScoreFlyAnimation(int index);
	void addScoreEndCallback(CCNode *node, void *param);
	bool isStageClear(int score);
	float stageReportAnimation();
	void startGameAnimation();
	void showStageInfoAndStart();
	float showBonusAnimation();
	void scheduleDiamondLabels(float dt);
	void autoTipsHandler(float dt);

	void handleResult(HitResult& hr);
	bool isTouchInsideNode(cocos2d::CCTouch* touch, cocos2d::CCNode *node);

	void showInGameFetchLayer();

	StarPuzzle *m_puzzle;
	Status m_status;
	int m_targetScoreArray[MAX_STAGE+1];
	bool m_isStageClear;

	bool m_isPoping;
	std::set<int> m_popStarSet;
	int m_popCount;

	PropType m_propType;

	int m_currDiamonds;
	int m_needAddDiamonds;
	
	cocos2d::CCPoint  m_topCenter;
	cocos2d::CCPoint  m_rightCenter;
	
	cocos2d::CCLabelTTF *m_bestScoreLabel;
	cocos2d::CCLabelTTF *m_stageNumLabel;
	cocos2d::CCLabelTTF *m_targetScoreLabel;
	cocos2d::CCLabelTTF *m_scoreNumLabel;
	cocos2d::CCLabelTTF *m_currScoreTotalLabel;

	cocos2d::CCLabelTTF *m_bonusScoreLabel;
	cocos2d::CCLabelTTF *m_remainStarLabel;

	cocos2d::CCLabelTTF *m_nextStageLabel;
	cocos2d::CCLabelTTF *m_nextTargetScoreLabel;

	cocos2d::CCPoint m_scoreLabelPos;

	cocos2d::CCSprite *m_goodPopstar;
	cocos2d::CCSprite *m_betterPopstar;
	cocos2d::CCSprite *m_fantasticPopstar;
	cocos2d::CCSprite *m_stageClearSprite;

	cocos2d::CCSprite *m_bombSprite;
	cocos2d::CCSprite *m_brushSprite;
	cocos2d::CCSprite *m_refreshSprite;

	cocos2d::CCSprite *m_diamondFetchBtn;
	cocos2d::CCLabelTTF *m_diamondLabel;
	cocos2d::CCSprite *m_pauseBtn;
};

#endif // __GAMESCENE_H__
