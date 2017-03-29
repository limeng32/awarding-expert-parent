package cn.chinaunicom.awarding.expert.condition;

import cn.chinaunicom.awarding.expert.persist.TaskExpert;
import indi.mybatis.flying.models.Conditionable;
import indi.mybatis.flying.models.Limitable;
import indi.mybatis.flying.models.Sortable;

public class TaskExpertCondition extends TaskExpert implements Conditionable {

	private static final long serialVersionUID = 1L;

	private Limitable limiter;

	private Sortable sorter;

	@Override
	public Limitable getLimiter() {
		return limiter;
	}

	@Override
	public Sortable getSorter() {
		return sorter;
	}

	@Override
	public void setLimiter(Limitable arg0) {
		this.limiter = arg0;
	}

	@Override
	public void setSorter(Sortable arg0) {
		this.sorter = arg0;
	}

}
