package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import limeng32.mirage.util.mapper.MapperFace;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheAnnotation;
import limeng32.mybatis.mybatisPlugin.cachePlugin.annotation.CacheRoleType;
import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.expert.persist.TaskExpert;
import cn.chinaunicom.awarding.project.persist.Task;

public interface TaskExpertMapper extends MapperFace<TaskExpert> {

	@Override
	@CacheAnnotation(MappedClass = { Expert.class, Task.class }, role = CacheRoleType.Observer)
	public TaskExpert select(Object id);

	@Override
	@CacheAnnotation(MappedClass = { Expert.class, Task.class }, role = CacheRoleType.Observer)
	public Collection<TaskExpert> selectAll(TaskExpert t);

	@Override
	public void insert(TaskExpert t);

	@Override
	@CacheAnnotation(MappedClass = { TaskExpert.class }, role = CacheRoleType.Trigger)
	public int update(TaskExpert t);

	@Override
	@CacheAnnotation(MappedClass = { TaskExpert.class }, role = CacheRoleType.Trigger)
	public int updatePersistent(TaskExpert t);

	@Override
	public void retrieve(TaskExpert t);

	@Override
	public void retrieveOnlyNull(TaskExpert t);

	@Override
	@CacheAnnotation(MappedClass = { TaskExpert.class }, role = CacheRoleType.Trigger)
	public int delete(TaskExpert t);

	@Override
	@CacheAnnotation(MappedClass = { Expert.class, Task.class }, role = CacheRoleType.Observer)
	public int count(TaskExpert t);

	public void loadExpert(Expert expert, TaskExpert taskExpert);

	public void loadTask(Task task, TaskExpert taskExpert);
}
