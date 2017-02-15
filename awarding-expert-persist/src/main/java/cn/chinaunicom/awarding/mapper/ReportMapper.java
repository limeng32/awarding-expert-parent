package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Report;
import cn.chinaunicom.awarding.project.persist.Task;
import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Task.class }, TriggerClass = { Report.class })
public interface ReportMapper extends MapperFace<Report> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Report select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Report> selectAll(Report t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Report selectOne(Report t);

	@Override
	public void insert(Report t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Report t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Report t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Report t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Report t);

	public void loadTask(Task task, Report report);
}
