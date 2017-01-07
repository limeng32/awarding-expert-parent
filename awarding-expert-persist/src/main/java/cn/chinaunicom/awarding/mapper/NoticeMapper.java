package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Notice;
import cn.chinaunicom.awarding.project.persist.Task;

public interface NoticeMapper extends MapperFace<Notice> {

	@Override
	@CacheAnnotation(MappedClass = { Task.class }, role = CacheRoleType.Observer)
	public Notice select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Task.class }, role = CacheRoleType.Observer)
	public Collection<Notice> selectAll(Notice t);

	@Override
	public void insert(Notice t);

	@Override
	@CacheAnnotation(MappedClass = { Notice.class }, role = CacheRoleType.Trigger)
	public int update(Notice t);

	@Override
	@CacheAnnotation(MappedClass = { Notice.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(Notice t);

	@Override
	public void retrieve(Notice t);

	@Override
	public void retrieveOnlyNull(Notice t);

	@Override
	@CacheAnnotation(MappedClass = { Notice.class }, role = CacheRoleType.Trigger)
	public int delete(Notice t);

	@Override
	@CacheAnnotation(MappedClass = { Task.class }, role = CacheRoleType.Observer)
	public int count(Notice t);

	public void loadTask(Task task, Notice notice);
}
