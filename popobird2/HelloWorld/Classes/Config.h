#ifndef __Config_H__
#define __Config_H__

#include <string>

class Config
{
public:
	virtual ~Config();
	static Config* instance();

	void load();
	void flush();

	bool mute() { return m_mute; }
	void setMute(bool mute);

	int highScore() { return m_savedHighScore; }
	void updateHighScore(int score);

	void savePetString(std::string petSaves);
	void savePetType(int type);

	void addCherry(int num);
	void useCherry(int num);

	void addUid(std::string uid);
	std::string getUid();

	//…Ë÷√”£Ã“
	void setCherry(int num);

	int getCherry();

	void addGameTimes();

	int cherryNum()	{ return m_savedCherryNum; }
	int gameTimes()     { return m_savedGameTimes; }
	int petType()       { return m_savedPetType; }
	std::string petString()   { return m_savedPetString; }
private:
	Config();
	Config(const Config&) {};
	static Config* m_instance;

	bool m_mute;
	int  m_savedCherryNum;
	int  m_savedGameTimes;
	int  m_savedPetType;
	int  m_savedHighScore;
	std::string m_savedPetString;
};

#endif /* __Config_H__ */
