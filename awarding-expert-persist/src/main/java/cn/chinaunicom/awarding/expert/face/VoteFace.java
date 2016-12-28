package cn.chinaunicom.awarding.expert.face;

import cn.chinaunicom.awarding.expert.persist.Award;
import cn.chinaunicom.awarding.expert.persist.Expert;

public interface VoteFace {
	void setExpert(Expert expert);

	void setAward(Award award);
}
