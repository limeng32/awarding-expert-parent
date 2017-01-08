package cn.chinaunicom.awarding.expert.persist;

import java.util.Collection;

import limeng32.mirage.util.service.ServiceSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.chinaunicom.awarding.mapper.TaskExpertMapper;
import cn.chinaunicom.awarding.project.persist.Task;

@Service
public class TaskExpertService extends ServiceSupport<TaskExpert> implements
		TaskExpertMapper {
	@Autowired
	private TaskExpertMapper mapper;

	@Override
	public TaskExpert select(Object id) {
		return supportSelect(mapper, id);
	}

	@Override
	public void insert(TaskExpert t) {
		supportInsert(mapper, t);
	}

	@Override
	public int update(TaskExpert t) {
		return supportUpdate(mapper, t);
	}

	@Override
	public Collection<TaskExpert> selectAll(TaskExpert t) {
		return supportSelectAll(mapper, t);
	}

	@Override
	public int updatePersistent(TaskExpert t) {
		return supportUpdatePersistent(mapper, t);
	}

	@Override
	public void retrieve(TaskExpert t) {
		supportRetrieve(mapper, t);
	}

	@Override
	public void retrieveOnlyNull(TaskExpert t) {
		supportRetrieveOnlyNull(mapper, t);
	}

	@Override
	public int delete(TaskExpert t) {
		return supportDelete(mapper, t);
	}

	@Override
	public int count(TaskExpert t) {
		return supportCount(mapper, t);
	}

	@Override
	public void loadExpert(Expert expert, TaskExpert taskExpert) {
		expert.removeAllTaskExpert();
		taskExpert.setExpert(expert);
		expert.setTaskExpert(mapper.selectAll(taskExpert));
	}

	@Override
	public void loadTask(Task task, TaskExpert taskExpert) {
		task.removeAllTaskExpert();
		taskExpert.setTask(task);
		task.setTaskExpert(mapper.selectAll(taskExpert));
	}
}