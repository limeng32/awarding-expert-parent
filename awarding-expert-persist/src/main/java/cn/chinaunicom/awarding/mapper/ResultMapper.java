package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Acception;
import cn.chinaunicom.awarding.expert.persist.Award;
import cn.chinaunicom.awarding.expert.persist.Result;
import cn.chinaunicom.awarding.project.persist.Task;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

@CacheRoleAnnotation(ObserverClass = { Task.class, Acception.class, Award.class }, TriggerClass = { Result.class })
public interface ResultMapper extends MapperFace<Result> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Result select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Result> selectAll(Result t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Result selectOne(Result t);

	@Override
	public void insert(Result t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Result t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Result t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Result t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Result t);

	public void loadTask(Task task, Result result);

	public void loadAcception(Acception acception, Result result);

	public void loadAward(Award award, Result result);
}
