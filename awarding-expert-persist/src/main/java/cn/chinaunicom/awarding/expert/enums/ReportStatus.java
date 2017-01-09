package cn.chinaunicom.awarding.expert.enums;

import limeng32.mybatis.accessory.AdvancedEnumTypeFace;
import cn.chinaunicom.awarding.expert.persist.Report;

public enum ReportStatus implements AdvancedEnumTypeFace {

	avoids("a", Report.STATUS_AVOIDS), declaration("d",
			Report.STATUS_DECLARATION);

	private final String flag;

	private final String text;

	private ReportStatus(String flag, String text) {
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
