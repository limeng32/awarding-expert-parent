package cn.chinaunicom.awarding.expert.enums;

import limeng32.mybatis.accessory.AdvancedEnumTypeFace;
import cn.chinaunicom.awarding.expert.persist.Vote;

public enum VoteStatus implements AdvancedEnumTypeFace {

	group("g", Vote.STATUS_GROUP), central("c", Vote.STATUS_CENTRAL), defense(
			"d", Vote.STATUS_DEFENSE);

	private final String flag;

	private final String text;

	private VoteStatus(String flag, String text) {
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
