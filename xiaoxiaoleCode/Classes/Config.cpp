#include "Config.h"
#include "cocos2d.h"
#include "SimpleAudioEngine.h"
#include <map>

const char* CONFIG_KEY_MUTE				= "mute";
const char* CONFIG_KEY_HASSAVEDPUZZLE	= "hasSavedPuzzle";
const char* CONFIG_KEY_HIGHSCORE		= "highScore";
const char* CONFIG_KEY_SAVEDSCORE		= "savedScore";
const char* CONFIG_KEY_SAVEDTARGET		= "savedTarget";
const char* CONFIG_KEY_SAVEDSTAGE		= "savedStage";
const char* CONFIG_KEY_SAVEDROW			= "savedRow";
const char* CONFIG_KEY_SAVEDCOL			= "savedCol";
const char* CONFIG_KEY_SAVEDPUZZLE		= "savedPuzzle";
const char* CONFIG_KEY_SAVEDDIAMONDS	= "savedDiamonds";
const char* CONFIG_KEY_ISGAMEOVER   	= "isGameOver";
const char* CONFIG_KEY_ISSHOWED   		= "isShowed";
const char* CONFIG_KEY_SAVEDSTAGESCORE  = "savedStageScore";

using namespace cocos2d;

Config* Config::m_instance = NULL;

Config::Config()
: m_mute(false)
, m_hasSavedPuzzle(false)
, m_isGameOver(false)
, m_isShowed(false)
, m_highScore(0)
, m_savedScore(0)
, m_savedTarget(0)
, m_savedStage(0)
, m_savedRow(0)
, m_savedCol(0)
, m_savedDiamonds(0)
, m_savedStageScore(0)
{

}

Config::~Config()
{

}

Config* Config::instance()
{
	if (m_instance == NULL)
		m_instance = new Config;
	return m_instance;
}

void Config::load()
{
	CCUserDefault* ud = CCUserDefault::sharedUserDefault();
	m_mute = ud->getBoolForKey(CONFIG_KEY_MUTE);
	m_hasSavedPuzzle = ud->getBoolForKey(CONFIG_KEY_HASSAVEDPUZZLE);
	m_highScore = ud->getIntegerForKey(CONFIG_KEY_HIGHSCORE);
	m_savedDiamonds = ud->getIntegerForKey(CONFIG_KEY_SAVEDDIAMONDS);
	m_savedStageScore = ud->getIntegerForKey(CONFIG_KEY_SAVEDSTAGESCORE);

	if (m_hasSavedPuzzle) {
		m_savedScore = ud->getIntegerForKey(CONFIG_KEY_SAVEDSCORE);
		m_savedTarget = ud->getIntegerForKey(CONFIG_KEY_SAVEDTARGET);
		m_savedStage = ud->getIntegerForKey(CONFIG_KEY_SAVEDSTAGE);
		m_savedRow = ud->getIntegerForKey(CONFIG_KEY_SAVEDROW);
		m_savedCol = ud->getIntegerForKey(CONFIG_KEY_SAVEDCOL);
		m_savedPuzzle = ud->getStringForKey(CONFIG_KEY_SAVEDPUZZLE);
		m_isGameOver = ud->getBoolForKey(CONFIG_KEY_ISGAMEOVER);
	}

	m_isShowed = ud->getBoolForKey(CONFIG_KEY_ISSHOWED);
}

void Config::flush()
{
	CCUserDefault* ud = CCUserDefault::sharedUserDefault();
	ud->setBoolForKey(CONFIG_KEY_MUTE, m_mute);
	ud->setBoolForKey(CONFIG_KEY_HASSAVEDPUZZLE, m_hasSavedPuzzle);
	ud->setIntegerForKey(CONFIG_KEY_HIGHSCORE, m_highScore);
	ud->setIntegerForKey(CONFIG_KEY_SAVEDSCORE, m_savedScore);
	ud->setIntegerForKey(CONFIG_KEY_SAVEDTARGET, m_savedTarget);
	ud->setIntegerForKey(CONFIG_KEY_SAVEDSTAGE, m_savedStage);
	ud->setIntegerForKey(CONFIG_KEY_SAVEDROW, m_savedRow);
	ud->setIntegerForKey(CONFIG_KEY_SAVEDCOL, m_savedCol);
	ud->setStringForKey(CONFIG_KEY_SAVEDPUZZLE, m_savedPuzzle);
	ud->setIntegerForKey(CONFIG_KEY_SAVEDDIAMONDS, m_savedDiamonds);
	ud->setIntegerForKey(CONFIG_KEY_SAVEDSTAGESCORE, m_savedStageScore);
	ud->setBoolForKey(CONFIG_KEY_ISGAMEOVER, m_isGameOver);
	ud->flush();
}

void Config::setMute(bool mute)
{
	m_mute = mute;
	CCUserDefault* ud = CCUserDefault::sharedUserDefault();
	ud->setBoolForKey(CONFIG_KEY_MUTE, m_mute);

	if (m_mute) {
		CocosDenshion::SimpleAudioEngine::sharedEngine()->stopBackgroundMusic();
		CocosDenshion::SimpleAudioEngine::sharedEngine()->stopAllEffects();
	}
	else {
		CocosDenshion::SimpleAudioEngine::sharedEngine()->playBackgroundMusic("music.ogg", true);
	}
}

void Config::addDiamond(int num)
{
	m_savedDiamonds += num;
	CCUserDefault* ud = CCUserDefault::sharedUserDefault();
	ud->setIntegerForKey(CONFIG_KEY_SAVEDDIAMONDS, m_savedDiamonds);
}

bool Config::consumeDiamond( int num )
{
	if (m_savedDiamonds < num) {
		return false;
	} else {
		m_savedDiamonds -= num;
		if (m_savedDiamonds < 0) {
			m_savedDiamonds = 0;
		}
		CCUserDefault* ud = CCUserDefault::sharedUserDefault();
		ud->setIntegerForKey(CONFIG_KEY_SAVEDDIAMONDS, m_savedDiamonds);

		return true;
	}
}

void Config::savedStageScore(int score)
{
	m_savedStageScore = score;
	CCUserDefault* ud = CCUserDefault::sharedUserDefault();
	ud->setIntegerForKey(CONFIG_KEY_SAVEDSTAGESCORE, m_savedStageScore);
}

void Config::clearSavedPuzzle()
{
	m_hasSavedPuzzle = false;
	m_savedRow = 0;
	m_savedCol = 0;
	m_savedStage = 0;
	m_savedScore = 0;
	m_savedTarget = 0;
	m_savedPuzzle = "";
	m_isGameOver = false;
}

void Config::savePuzzle(std::string puzzle, int row, int col, int stage, int score, int target, bool bGameOver)
{
	m_savedRow = row;
	m_savedCol = col;
	m_savedStage = stage;
	m_savedScore = score;
	m_savedTarget = target;
	m_savedPuzzle = puzzle;
	m_hasSavedPuzzle = true;
	m_isGameOver = bGameOver;
}

void Config::setShowed()
{
	m_isShowed = true;
	CCUserDefault* ud = CCUserDefault::sharedUserDefault();
	ud->setBoolForKey(CONFIG_KEY_ISSHOWED, m_isShowed);
}
