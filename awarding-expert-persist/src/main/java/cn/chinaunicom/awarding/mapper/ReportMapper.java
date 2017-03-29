package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Report;
import cn.chinaunicom.awarding.project.persist.Task;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

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
