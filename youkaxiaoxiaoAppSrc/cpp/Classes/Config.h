#ifndef _CONFIG_H_
#define _CONFIG_H_

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

	bool hasSavedPuzzle() { return m_hasSavedPuzzle; }
	bool isGameOver() { return m_isGameOver; }

	bool isShowed() { return m_isShowed; };
	void setShowed();

	int highScore() { return m_highScore; }
	void updateHighScore(int score) {
		if (m_highScore < score)
			m_highScore = score;
	}

	void addDiamond(int num);
	bool consumeDiamond(int num);
	int diamonds() { return m_savedDiamonds; };
	int stageScore() {return m_savedStageScore; };
	void savedStageScore(int score);

	void clearSavedPuzzle();
	void savePuzzle(std::string puzzle, int row, int col, int stage, int score, int target, bool bGameOver);

	int score()     { return m_savedScore;  }
	int target()	{ return m_savedTarget; }
	int stage()     { return m_savedStage;  }
	int row()       { return m_savedRow;    }
	int col()       { return m_savedCol;    }
	std::string puzzle()   { return m_savedPuzzle; }
private:
	Config();
	Config(const Config&) {};
	static Config* m_instance;

	bool m_mute;
	bool m_hasSavedPuzzle;
	bool m_isGameOver;
	bool m_isShowed;
	int  m_highScore;
	int  m_savedScore;
	int  m_savedTarget;
	int  m_savedStage;
	int  m_savedRow;
	int  m_savedCol;
	int  m_savedDiamonds;
	int  m_savedStageScore;
	std::string m_savedPuzzle;
};

#endif /* _CONFIG_H_ */
