package cn.chinaunicom.awarding.mapper;

import java.util.Collection;

import cn.chinaunicom.awarding.expert.persist.Expert;
import cn.chinaunicom.awarding.expert.persist.TaskExpert;
import cn.chinaunicom.awarding.project.persist.Task;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;
import limeng32.mirage.util.mapper.MapperFace;

@CacheRoleAnnotation(ObserverClass = { Expert.class, Task.class }, TriggerClass = { TaskExpert.class })
public interface TaskExpertMapper extends MapperFace<TaskExpert> {

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public TaskExpert select(Object id);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<TaskExpert> selectAll(TaskExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public TaskExpert selectOne(TaskExpert t);

	@Override
	public void insert(TaskExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(TaskExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(TaskExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(TaskExpert t);

	@Override
	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(TaskExpert t);

	public void loadExpert(Expert expert, TaskExpert taskExpert);

	public void loadTask(Task task, TaskExpert taskExpert);
}
