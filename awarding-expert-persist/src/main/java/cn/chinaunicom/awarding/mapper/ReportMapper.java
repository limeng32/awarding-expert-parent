package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Report;
import cn.chinaunicom.awarding.project.persist.Task;

public interface ReportMapper extends MapperFace<Report> {

	@Override
	@CacheAnnotation(MappedClass = { Task.class }, role = CacheRoleType.Observer)
	public Report select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Task.class }, role = CacheRoleType.Observer)
	public Collection<Report> selectAll(Report t);

	@Override
	public void insert(Report t);

	@Override
	@CacheAnnotation(MappedClass = { Report.class }, role = CacheRoleType.Trigger)
	public int update(Report t);

	@Override
	@CacheAnnotation(MappedClass = { Report.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(Report t);

	@Override
	public void retrieve(Report t);

	@Override
	public void retrieveOnlyNull(Report t);

	@Override
	@CacheAnnotation(MappedClass = { Report.class }, role = CacheRoleType.Trigger)
	public int delete(Report t);

	@Override
	@CacheAnnotation(MappedClass = { Task.class }, role = CacheRoleType.Observer)
	public int count(Report t);

	public void loadTask(Task task, Report report);
}
