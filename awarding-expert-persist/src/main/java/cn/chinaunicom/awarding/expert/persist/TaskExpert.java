package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import cn.chinaunicom.awarding.expert.enums.TaskExpertStatus;
import cn.chinaunicom.awarding.project.persist.Task;
import indi.mybatis.flying.annotations.FieldMapperAnnotation;
import indi.mybatis.flying.annotations.TableMapperAnnotation;
import limeng32.mirage.util.pojo.PojoSupport;

@TableMapperAnnotation(tableName = "taskExpert")
public class TaskExpert extends PojoSupport<TaskExpert> implements Serializable,
		cn.chinaunicom.awarding.project.face.TaskExpertFace, cn.chinaunicom.awarding.expert.face.TaskExpertFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "taskExpert_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;

	/**
	 * 状态值，对应枚举类变量，包括“邀请、发送邮件、回复确认”等状态
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.VARCHAR)
	private TaskExpertStatus status;

	/**
	 * 本条记录建立时间
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "timeStamp", jdbcType = JdbcType.TIMESTAMP)
	private java.util.Date timeStamp;

	@FieldMapperAnnotation(dbFieldName = "expert_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "expert_id")
	private Expert expert;

	@FieldMapperAnnotation(dbFieldName = "task_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "task_id")
	private Task task;

	public static final String STATUS_INVITE = "邀请";

	public static final String STATUS_EMAIL = "发送邮件";

	public static final String STATUS_CONFIRM = "回复确认";

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

	public TaskExpertStatus getStatus() {
		return status;
	}

	public void setStatus(TaskExpertStatus status) {
		this.status = status;
	}

	public java.util.Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(java.util.Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
