package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Notice;
import cn.chinaunicom.awarding.project.persist.Task;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

@CacheRoleAnnotation(ObserverClass = { Task.class }, TriggerClass = { Notice.class })
public interface NoticeMapper extends MapperFace<Notice> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Notice select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Notice> selectAll(Notice t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Notice selectOne(Notice t);

	@Override
	public void insert(Notice t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Notice t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Notice t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Notice t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Notice t);

	public void loadTask(Task task, Notice notice);
}
