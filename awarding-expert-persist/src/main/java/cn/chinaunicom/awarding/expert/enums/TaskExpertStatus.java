package cn.chinaunicom.awarding.expert.enums;

import limeng32.mybatis.accessory.AdvancedEnumTypeFace;
import cn.chinaunicom.awarding.expert.persist.TaskExpert;

public enum TaskExpertStatus implements AdvancedEnumTypeFace {

	invite("i", TaskExpert.STATUS_INVITE), email("e", TaskExpert.STATUS_EMAIL), confirm(
			"c", TaskExpert.STATUS_CONFIRM);

	private final String flag;

	private final String text;

	private TaskExpertStatus(String flag, String text) {
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
