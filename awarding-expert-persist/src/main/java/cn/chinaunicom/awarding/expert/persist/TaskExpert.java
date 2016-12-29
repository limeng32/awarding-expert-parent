package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;
import cn.chinaunicom.awarding.project.persist.Task;

public class TaskExpert extends PojoSupport<TaskExpert> implements
		Serializable, cn.chinaunicom.awarding.project.face.TaskExpertFace,
		cn.chinaunicom.awarding.expert.face.TaskExpertFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	private java.lang.String id;

	private Expert expert;
	private Task task;

	public Expert getExpert() {
		return expert;
	}

	@Override
	public void setExpert(Expert newExpert) {
		if (this.expert == null || this.expert != newExpert) {
			if (this.expert != null) {
				Expert oldExpert = this.expert;
				this.expert = null;
				oldExpert.removeTaskExpert(this);
			}
			if (newExpert != null) {
				this.expert = newExpert;
				this.expert.addTaskExpert(this);
			}
		}
	}

	public Task getTask() {
		return task;
	}

	@Override
	public void setTask(Task newTask) {
		if (this.task == null || this.task != newTask) {
			if (this.task != null) {
				Task oldTask = this.task;
				this.task = null;
				oldTask.removeTaskExpert(this);
			}
			if (newTask != null) {
				this.task = newTask;
				this.task.addTaskExpert(this);
			}
		}
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

}
