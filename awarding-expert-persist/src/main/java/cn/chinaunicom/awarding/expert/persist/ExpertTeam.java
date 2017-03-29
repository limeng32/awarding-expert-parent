package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.annotation.JSONField;

import cn.chinaunicom.awarding.expert.face.ExpertTeamExpertFace;
import cn.chinaunicom.awarding.project.persist.Task;
import indi.mybatis.flying.annotations.FieldMapperAnnotation;
import indi.mybatis.flying.annotations.TableMapperAnnotation;
import limeng32.mirage.util.pojo.PojoSupport;

@TableMapperAnnotation(tableName = "expertTeam")
public class ExpertTeam extends PojoSupport<ExpertTeam> implements Serializable,
		cn.chinaunicom.awarding.project.face.ExpertTeamFace, cn.chinaunicom.awarding.expert.face.ExpertTeamFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "expertTeam_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;

	/**
	 * 小组名称
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "name", jdbcType = JdbcType.VARCHAR)
	private java.lang.String name;

	@FieldMapperAnnotation(dbFieldName = "task_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "task_id")
	private Task task;

	@FieldMapperAnnotation(dbFieldName = "award_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "award_id")
	private Award award;

	private java.util.Collection<ExpertTeamExpertFace> expertTeamExpert;

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
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
				oldTask.removeExpertTeam(this);
			}
			if (newTask != null) {
				this.task = newTask;
				this.task.addExpertTeam(this);
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
				oldAward.removeExpertTeam(this);
			}
			if (newAward != null) {
				this.award = newAward;
				this.award.addExpertTeam(this);
			}
		}
	}

	public java.util.Collection<ExpertTeamExpertFace> getExpertTeamExpert() {
		if (expertTeamExpert == null)
			expertTeamExpert = new java.util.LinkedHashSet<ExpertTeamExpertFace>();
		return expertTeamExpert;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<ExpertTeamExpertFace> getIteratorExpertTeamExpert() {
		if (expertTeamExpert == null)
			expertTeamExpert = new java.util.LinkedHashSet<ExpertTeamExpertFace>();
		return expertTeamExpert.iterator();
	}

	public void setExpertTeamExpert(java.util.Collection<? extends ExpertTeamExpertFace> newExpertTeamExpert) {
		removeAllExpertTeamExpert();
		for (java.util.Iterator<? extends ExpertTeamExpertFace> iter = newExpertTeamExpert.iterator(); iter.hasNext();)
			addExpertTeamExpert((ExpertTeamExpertFace) iter.next());
	}

	public void addExpertTeamExpert(ExpertTeamExpertFace newExpertTeamExpert) {
		if (newExpertTeamExpert == null)
			return;
		if (this.expertTeamExpert == null)
			this.expertTeamExpert = new java.util.LinkedHashSet<ExpertTeamExpertFace>();
		if (!this.expertTeamExpert.contains(newExpertTeamExpert)) {
			this.expertTeamExpert.add(newExpertTeamExpert);
			newExpertTeamExpert.setExpertTeam(this);
		} else {
			for (ExpertTeamExpertFace temp : this.expertTeamExpert) {
				if (newExpertTeamExpert.equals(temp)) {
					if (temp != newExpertTeamExpert) {
						removeExpertTeamExpert(temp);
						this.expertTeamExpert.add(newExpertTeamExpert);
						newExpertTeamExpert.setExpertTeam(this);
					}
					break;
				}
			}
		}
	}

	public void removeExpertTeamExpert(ExpertTeamExpertFace oldExpertTeamExpert) {
		if (oldExpertTeamExpert == null)
			return;
		if (this.expertTeamExpert != null)
			if (this.expertTeamExpert.contains(oldExpertTeamExpert)) {
				for (ExpertTeamExpertFace temp : this.expertTeamExpert) {
					if (oldExpertTeamExpert.equals(temp)) {
						if (temp != oldExpertTeamExpert) {
							temp.setExpertTeam((ExpertTeam) null);
						}
						break;
					}
				}
				this.expertTeamExpert.remove(oldExpertTeamExpert);
				oldExpertTeamExpert.setExpertTeam((ExpertTeam) null);
			}
	}

	public void removeAllExpertTeamExpert() {
		if (expertTeamExpert != null) {
			ExpertTeamExpertFace oldExpertTeamExpert;
			for (java.util.Iterator<ExpertTeamExpertFace> iter = getIteratorExpertTeamExpert(); iter.hasNext();) {
				oldExpertTeamExpert = (ExpertTeamExpertFace) iter.next();
				iter.remove();
				oldExpertTeamExpert.setExpertTeam((ExpertTeam) null);
			}
			expertTeamExpert.clear();
		}
	}
}
