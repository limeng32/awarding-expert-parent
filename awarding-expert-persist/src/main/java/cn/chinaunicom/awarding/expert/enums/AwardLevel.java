package cn.chinaunicom.awarding.expert.enums;

import limeng32.mybatis.accessory.AdvancedEnumTypeFace;
import cn.chinaunicom.awarding.expert.persist.Result;

public enum AwardLevel implements AdvancedEnumTypeFace {

	outstanding("o", Result.LEVEL_OUTSTANDING), first("f", Result.LEVEL_FIRST), second(
			"s", Result.LEVEL_SECOND), third("t", Result.LEVEL_THIRD), honorable(
			"h", Result.LEVEL_HONORABLE);

	private final String flag;

	private final String text;

	private AwardLevel(String flag, String text) {
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
