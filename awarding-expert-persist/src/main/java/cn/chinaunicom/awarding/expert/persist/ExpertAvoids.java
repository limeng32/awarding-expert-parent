package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;
import limeng32.mybatis.mybatisPlugin.mapperPlugin.annotation.FieldMapperAnnotation;
import limeng32.mybatis.mybatisPlugin.mapperPlugin.annotation.TableMapperAnnotation;

import org.apache.ibatis.type.JdbcType;

import cn.chinaunicom.awarding.project.persist.Task;

@TableMapperAnnotation(tableName = "expertAvoids")
public class ExpertAvoids extends PojoSupport<ExpertAvoids> implements
		Serializable, cn.chinaunicom.awarding.project.face.ExpertAvoidsFace,
		cn.chinaunicom.awarding.expert.face.ExpertAvoidsFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "expertAvoids_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;

	@FieldMapperAnnotation(dbFieldName = "task_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "task_id")
	private Task task;

	@FieldMapperAnnotation(dbFieldName = "acception_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "acception_id")
	private Acception acception;

	@FieldMapperAnnotation(dbFieldName = "expert_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "expert_id")
	private Expert expert;

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public Expert getExpert() {
		return expert;
	}

	@Override
	public void setExpert(Expert newExpert) {
		if (this.expert == null || this.expert != newExpert) {
			if (this.expert != null) {
				Expert oldExpert = this.expert;
				this.expert = null;
				oldExpert.removeExpertAvoids(this);
			}
			if (newExpert != null) {
				this.expert = newExpert;
				this.expert.addExpertAvoids(this);
			}
		}
	}

	public Acception getAcception() {
		return acception;
	}

	@Override
	public void setAcception(Acception newAcception) {
		if (this.acception == null || this.acception != newAcception) {
			if (this.acception != null) {
				Acception oldAcception = this.acception;
				this.acception = null;
				oldAcception.removeExpertAvoids(this);
			}
			if (newAcception != null) {
				this.acception = newAcception;
				this.acception.addExpertAvoids(this);
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
				oldTask.removeExpertAvoids(this);
			}
			if (newTask != null) {
				this.task = newTask;
				this.task.addExpertAvoids(this);
			}
		}
	}
}
