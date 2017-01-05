package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;
import limeng32.mybatis.mybatisPlugin.mapperPlugin.annotation.FieldMapperAnnotation;
import limeng32.mybatis.mybatisPlugin.mapperPlugin.annotation.TableMapperAnnotation;

import org.apache.ibatis.type.JdbcType;

import cn.chinaunicom.awarding.project.persist.Task;

@TableMapperAnnotation(tableName = "vote")
public class Vote extends PojoSupport<Vote> implements Serializable,
		cn.chinaunicom.awarding.project.face.VoteFace,
		cn.chinaunicom.awarding.expert.face.VoteFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "vote_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;

	/**
	 * 状态值，对应枚举类变量，包括“分组投票、集中投票、答辩投票”等状态
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.CHAR)
	private java.lang.String status;

	/**
	 * 级别值，对应枚举类变量，包括“一等奖、二等奖、三等奖、优秀奖”等级别
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "level", jdbcType = JdbcType.CHAR)
	private java.lang.String level;

	@FieldMapperAnnotation(dbFieldName = "task_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "task_id")
	private Task task;

	@FieldMapperAnnotation(dbFieldName = "expert_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "expert_id")
	private Expert expert;

	@FieldMapperAnnotation(dbFieldName = "acception_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "acception_id")
	private Acception acception;

	@FieldMapperAnnotation(dbFieldName = "award_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "award_id")
	private Award award;

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

	public java.lang.String getLevel() {
		return level;
	}

	public void setLevel(java.lang.String level) {
		this.level = level;
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
				oldExpert.removeVote(this);
			}
			if (newExpert != null) {
				this.expert = newExpert;
				this.expert.addVote(this);
			}
		}
	}

	public Award getAward() {
		return award;
	}

	@Override
	public void setAward(Award newAward) {
		if (this.award == null || this.award != newAward) {
			if (this.award != null) {
				Award oldAward = this.award;
				this.award = null;
				oldAward.removeVote(this);
			}
			if (newAward != null) {
				this.award = newAward;
				this.award.addVote(this);
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
				oldTask.removeVote(this);
			}
			if (newTask != null) {
				this.task = newTask;
				this.task.addVote(this);
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
				oldAcception.removeVote(this);
			}
			if (newAcception != null) {
				this.acception = newAcception;
				this.acception.addVote(this);
			}
		}
	}
}
