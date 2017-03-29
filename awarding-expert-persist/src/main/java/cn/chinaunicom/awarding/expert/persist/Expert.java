package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.annotation.JSONField;

import cn.chinaunicom.awarding.account.persist.Account;
import cn.chinaunicom.awarding.expert.face.AcceptionFace;
import cn.chinaunicom.awarding.expert.face.ExpertAvoidsFace;
import cn.chinaunicom.awarding.expert.face.ExpertTeamExpertFace;
import cn.chinaunicom.awarding.expert.face.TaskExpertFace;
import cn.chinaunicom.awarding.expert.face.VoteFace;
import indi.mybatis.flying.annotations.FieldMapperAnnotation;
import indi.mybatis.flying.annotations.TableMapperAnnotation;
import indi.mybatis.flying.statics.OpLockType;
import limeng32.mirage.util.pojo.PojoSupport;

@TableMapperAnnotation(tableName = "expert")
public class Expert extends PojoSupport<Expert> implements Serializable,
		cn.chinaunicom.awarding.account.face.ExpertFace, cn.chinaunicom.awarding.expert.face.ExpertFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "expert_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;
	/**
	 * 乐观锁
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "opLock", jdbcType = JdbcType.INTEGER, opLockType = OpLockType.Version)
	@JSONField(serialize = false)
	private Integer opLock;

	@FieldMapperAnnotation(dbFieldName = "account_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "account_id")
	public Account account;

	private java.util.Collection<AcceptionFace> acception;

	private java.util.Collection<TaskExpertFace> taskExpert;

	private java.util.Collection<ExpertAvoidsFace> expertAvoids;

	private java.util.Collection<VoteFace> vote;

	private java.util.Collection<ExpertTeamExpertFace> expertTeamExpert;

	public Integer getOpLock() {
		return opLock;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public void setAccount(Account newAccount) {
		if (this.account == null || this.account != newAccount) {
			if (this.account != null) {
				Account oldAccount = this.account;
				this.account = null;
				oldAccount.removeExpert(this);
			}
			if (newAccount != null) {
				this.account = newAccount;
				this.account.addExpert(this);
			}
		}
	}

	public java.util.Collection<AcceptionFace> getAcception() {
		if (acception == null)
			acception = new java.util.LinkedHashSet<AcceptionFace>();
		return acception;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<AcceptionFace> getIteratorAcception() {
		if (acception == null)
			acception = new java.util.LinkedHashSet<AcceptionFace>();
		return acception.iterator();
	}

	public void setAcception(java.util.Collection<? extends AcceptionFace> newAcception) {
		removeAllAcception();
		for (java.util.Iterator<? extends AcceptionFace> iter = newAcception.iterator(); iter.hasNext();)
			addAcception((AcceptionFace) iter.next());
	}

	public void addAcception(AcceptionFace newAcception) {
		if (newAcception == null)
			return;
		if (this.acception == null)
			this.acception = new java.util.LinkedHashSet<AcceptionFace>();
		if (!this.acception.contains(newAcception)) {
			this.acception.add(newAcception);
			newAcception.setExpert(this);
		} else {
			for (AcceptionFace temp : this.acception) {
				if (newAcception.equals(temp)) {
					if (temp != newAcception) {
						removeAcception(temp);
						this.acception.add(newAcception);
						newAcception.setExpert(this);
					}
					break;
				}
			}
		}
	}

	public void removeAcception(AcceptionFace oldAcception) {
		if (oldAcception == null)
			return;
		if (this.acception != null)
			if (this.acception.contains(oldAcception)) {
				for (AcceptionFace temp : this.acception) {
					if (oldAcception.equals(temp)) {
						if (temp != oldAcception) {
							temp.setExpert((Expert) null);
						}
						break;
					}
				}
				this.acception.remove(oldAcception);
				oldAcception.setExpert((Expert) null);
			}
	}

	public void removeAllAcception() {
		if (acception != null) {
			AcceptionFace oldAcception;
			for (java.util.Iterator<AcceptionFace> iter = getIteratorAcception(); iter.hasNext();) {
				oldAcception = (AcceptionFace) iter.next();
				iter.remove();
				oldAcception.setExpert((Expert) null);
			}
			acception.clear();
		}
	}

	public java.util.Collection<TaskExpertFace> getTaskExpert() {
		if (taskExpert == null)
			taskExpert = new java.util.LinkedHashSet<TaskExpertFace>();
		return taskExpert;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<TaskExpertFace> getIteratorTaskExpert() {
		if (taskExpert == null)
			taskExpert = new java.util.LinkedHashSet<TaskExpertFace>();
		return taskExpert.iterator();
	}

	public void setTaskExpert(java.util.Collection<? extends TaskExpertFace> newTaskExpert) {
		removeAllTaskExpert();
		for (java.util.Iterator<? extends TaskExpertFace> iter = newTaskExpert.iterator(); iter.hasNext();)
			addTaskExpert((TaskExpertFace) iter.next());
	}

	public void addTaskExpert(TaskExpertFace newTaskExpert) {
		if (newTaskExpert == null)
			return;
		if (this.taskExpert == null)
			this.taskExpert = new java.util.LinkedHashSet<TaskExpertFace>();
		if (!this.taskExpert.contains(newTaskExpert)) {
			this.taskExpert.add(newTaskExpert);
			newTaskExpert.setExpert(this);
		} else {
			for (TaskExpertFace temp : this.taskExpert) {
				if (newTaskExpert.equals(temp)) {
					if (temp != newTaskExpert) {
						removeTaskExpert(temp);
						this.taskExpert.add(newTaskExpert);
						newTaskExpert.setExpert(this);
					}
					break;
				}
			}
		}
	}

	public void removeTaskExpert(TaskExpertFace oldTaskExpert) {
		if (oldTaskExpert == null)
			return;
		if (this.taskExpert != null)
			if (this.taskExpert.contains(oldTaskExpert)) {
				for (TaskExpertFace temp : this.taskExpert) {
					if (oldTaskExpert.equals(temp)) {
						if (temp != oldTaskExpert) {
							temp.setExpert((Expert) null);
						}
						break;
					}
				}
				this.taskExpert.remove(oldTaskExpert);
				oldTaskExpert.setExpert((Expert) null);
			}
	}

	public void removeAllTaskExpert() {
		if (taskExpert != null) {
			TaskExpertFace oldTaskExpert;
			for (java.util.Iterator<TaskExpertFace> iter = getIteratorTaskExpert(); iter.hasNext();) {
				oldTaskExpert = (TaskExpertFace) iter.next();
				iter.remove();
				oldTaskExpert.setExpert((Expert) null);
			}
			taskExpert.clear();
		}
	}

	public java.util.Collection<ExpertAvoidsFace> getExpertAvoids() {
		if (expertAvoids == null)
			expertAvoids = new java.util.LinkedHashSet<ExpertAvoidsFace>();
		return expertAvoids;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<ExpertAvoidsFace> getIteratorExpertAvoids() {
		if (expertAvoids == null)
			expertAvoids = new java.util.LinkedHashSet<ExpertAvoidsFace>();
		return expertAvoids.iterator();
	}

	public void setExpertAvoids(java.util.Collection<? extends ExpertAvoidsFace> newExpertAvoids) {
		removeAllExpertAvoids();
		for (java.util.Iterator<? extends ExpertAvoidsFace> iter = newExpertAvoids.iterator(); iter.hasNext();)
			addExpertAvoids((ExpertAvoidsFace) iter.next());
	}

	public void addExpertAvoids(ExpertAvoidsFace newExpertAvoids) {
		if (newExpertAvoids == null)
			return;
		if (this.expertAvoids == null)
			this.expertAvoids = new java.util.LinkedHashSet<ExpertAvoidsFace>();
		if (!this.expertAvoids.contains(newExpertAvoids)) {
			this.expertAvoids.add(newExpertAvoids);
			newExpertAvoids.setExpert(this);
		} else {
			for (ExpertAvoidsFace temp : this.expertAvoids) {
				if (newExpertAvoids.equals(temp)) {
					if (temp != newExpertAvoids) {
						removeExpertAvoids(temp);
						this.expertAvoids.add(newExpertAvoids);
						newExpertAvoids.setExpert(this);
					}
					break;
				}
			}
		}
	}

	public void removeExpertAvoids(ExpertAvoidsFace oldExpertAvoids) {
		if (oldExpertAvoids == null)
			return;
		if (this.expertAvoids != null)
			if (this.expertAvoids.contains(oldExpertAvoids)) {
				for (ExpertAvoidsFace temp : this.expertAvoids) {
					if (oldExpertAvoids.equals(temp)) {
						if (temp != oldExpertAvoids) {
							temp.setExpert((Expert) null);
						}
						break;
					}
				}
				this.expertAvoids.remove(oldExpertAvoids);
				oldExpertAvoids.setExpert((Expert) null);
			}
	}

	public void removeAllExpertAvoids() {
		if (expertAvoids != null) {
			ExpertAvoidsFace oldExpertAvoids;
			for (java.util.Iterator<ExpertAvoidsFace> iter = getIteratorExpertAvoids(); iter.hasNext();) {
				oldExpertAvoids = (ExpertAvoidsFace) iter.next();
				iter.remove();
				oldExpertAvoids.setExpert((Expert) null);
			}
			expertAvoids.clear();
		}
	}

	public java.util.Collection<VoteFace> getVote() {
		if (vote == null)
			vote = new java.util.LinkedHashSet<VoteFace>();
		return vote;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<VoteFace> getIteratorVote() {
		if (vote == null)
			vote = new java.util.LinkedHashSet<VoteFace>();
		return vote.iterator();
	}

	public void setVote(java.util.Collection<? extends VoteFace> newVote) {
		removeAllVote();
		for (java.util.Iterator<? extends VoteFace> iter = newVote.iterator(); iter.hasNext();)
			addVote((VoteFace) iter.next());
	}

	public void addVote(VoteFace newVote) {
		if (newVote == null)
			return;
		if (this.vote == null)
			this.vote = new java.util.LinkedHashSet<VoteFace>();
		if (!this.vote.contains(newVote)) {
			this.vote.add(newVote);
			newVote.setExpert(this);
		} else {
			for (VoteFace temp : this.vote) {
				if (newVote.equals(temp)) {
					if (temp != newVote) {
						removeVote(temp);
						this.vote.add(newVote);
						newVote.setExpert(this);
					}
					break;
				}
			}
		}
	}

	public void removeVote(VoteFace oldVote) {
		if (oldVote == null)
			return;
		if (this.vote != null)
			if (this.vote.contains(oldVote)) {
				for (VoteFace temp : this.vote) {
					if (oldVote.equals(temp)) {
						if (temp != oldVote) {
							temp.setExpert((Expert) null);
						}
						break;
					}
				}
				this.vote.remove(oldVote);
				oldVote.setExpert((Expert) null);
			}
	}

	public void removeAllVote() {
		if (vote != null) {
			VoteFace oldVote;
			for (java.util.Iterator<VoteFace> iter = getIteratorVote(); iter.hasNext();) {
				oldVote = (VoteFace) iter.next();
				iter.remove();
				oldVote.setExpert((Expert) null);
			}
			vote.clear();
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
			newExpertTeamExpert.setExpert(this);
		} else {
			for (ExpertTeamExpertFace temp : this.expertTeamExpert) {
				if (newExpertTeamExpert.equals(temp)) {
					if (temp != newExpertTeamExpert) {
						removeExpertTeamExpert(temp);
						this.expertTeamExpert.add(newExpertTeamExpert);
						newExpertTeamExpert.setExpert(this);
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
							temp.setExpert((Expert) null);
						}
						break;
					}
				}
				this.expertTeamExpert.remove(oldExpertTeamExpert);
				oldExpertTeamExpert.setExpert((Expert) null);
			}
	}

	public void removeAllExpertTeamExpert() {
		if (expertTeamExpert != null) {
			ExpertTeamExpertFace oldExpertTeamExpert;
			for (java.util.Iterator<ExpertTeamExpertFace> iter = getIteratorExpertTeamExpert(); iter.hasNext();) {
				oldExpertTeamExpert = (ExpertTeamExpertFace) iter.next();
				iter.remove();
				oldExpertTeamExpert.setExpert((Expert) null);
			}
			expertTeamExpert.clear();
		}
	}

}
