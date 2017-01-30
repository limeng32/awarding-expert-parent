package cn.chinaunicom.awarding.expert.condition;

import limeng32.mybatis.mybatisPlugin.cachePlugin.Conditionable;
import limeng32.mybatis.mybatisPlugin.cachePlugin.Limitable;
import limeng32.mybatis.mybatisPlugin.cachePlugin.Sortable;
import cn.chinaunicom.awarding.expert.persist.Expert;

public class ExpertCondition extends Expert implements Conditionable {

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
