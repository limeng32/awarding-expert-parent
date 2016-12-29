package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;
import cn.chinaunicom.awarding.project.persist.Task;

public class Report extends PojoSupport<Report> implements Serializable,
		cn.chinaunicom.awarding.project.face.ReportFace,
		cn.chinaunicom.awarding.expert.face.ReportFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	public java.lang.String id;

	/**
	 * 状态值，对应枚举类变量，包括“项目申报报表、项目回避报表”等状态
	 * 
	 */
	public java.lang.String status;

	public Task task;

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
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
				oldTask.removeReport(this);
			}
			if (newTask != null) {
				this.task = newTask;
				this.task.addReport(this);
			}
		}
	}
}
