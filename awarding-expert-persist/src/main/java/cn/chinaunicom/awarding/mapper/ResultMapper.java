package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Acception;
import cn.chinaunicom.awarding.expert.persist.Award;
import cn.chinaunicom.awarding.expert.persist.Result;
import cn.chinaunicom.awarding.project.persist.Task;

public interface ResultMapper extends MapperFace<Result> {

	@Override
	@CacheAnnotation(MappedClass = { Task.class, Acception.class, Award.class }, role = CacheRoleType.Observer)
	public Result select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Task.class, Acception.class, Award.class }, role = CacheRoleType.Observer)
	public Collection<Result> selectAll(Result t);

	@Override
	public void insert(Result t);

	@Override
	@CacheAnnotation(MappedClass = { Result.class }, role = CacheRoleType.Trigger)
	public int update(Result t);

	@Override
	@CacheAnnotation(MappedClass = { Result.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(Result t);

	@Override
	public void retrieve(Result t);

	@Override
	public void retrieveOnlyNull(Result t);

	@Override
	@CacheAnnotation(MappedClass = { Result.class }, role = CacheRoleType.Trigger)
	public int delete(Result t);

	@Override
	@CacheAnnotation(MappedClass = { Task.class, Acception.class, Award.class }, role = CacheRoleType.Observer)
	public int count(Result t);

	public void loadTask(Task task, Result result);

	public void loadAcception(Acception acception, Result result);

	public void loadAward(Award award, Result result);
}
