package cn.chinaunicom.awarding.expert.enums;

import limeng32.mybatis.accessory.AdvancedEnumTypeFace;
import cn.chinaunicom.awarding.expert.persist.ExpertTeamExpert;

public enum ExpertTeamExpertStatus implements AdvancedEnumTypeFace {

	leader("l", ExpertTeamExpert.STATUS_LEADER), deputy("d",
			ExpertTeamExpert.STATUS_DEPUTY), member("m",
			ExpertTeamExpert.STATUS_MEMBER);

	private final String flag;

	private final String text;

	private ExpertTeamExpertStatus(String flag, String text) {
		this.flag = flag;
		this.text = text;
	}

	public String text() {
		return text;
	}

	@Override
	public String flag() {
		return flag;
	}
}
