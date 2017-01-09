package cn.chinaunicom.awarding.expert.enums;

import limeng32.mybatis.accessory.AdvancedEnumTypeFace;
import cn.chinaunicom.awarding.expert.persist.Notice;

public enum NoticeStatus implements AdvancedEnumTypeFace {

	conference("c", Notice.STATUS_CONFERENCE), declaration("d",
			Notice.STATUS_DECLARATION);

	private final String flag;

	private final String text;

	private NoticeStatus(String flag, String text) {
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
