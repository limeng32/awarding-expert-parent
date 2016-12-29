package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;
import cn.chinaunicom.awarding.expert.face.AcceptionBucketFace;
import cn.chinaunicom.awarding.expert.face.ExpertAvoidsFace;
import cn.chinaunicom.awarding.expert.face.ResultFace;
import cn.chinaunicom.awarding.expert.face.VoteFace;
import cn.chinaunicom.awarding.project.persist.Project;

import com.alibaba.fastjson.annotation.JSONField;

public class Acception extends PojoSupport<Acception> implements Serializable,
		cn.chinaunicom.awarding.project.face.AcceptionFace,
		cn.chinaunicom.awarding.expert.face.AcceptionFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	private java.lang.String id;
	/**
	 * 参评项目名称
	 * 
	 */
	private java.lang.String name;
	/**
	 * 乐观锁
	 * 
	 */
	private Integer opLock;

	private Project project;

	private Expert expert;

	private java.util.Collection<AcceptionBucketFace> acceptionBucket;

	private java.util.Collection<ExpertAvoidsFace> expertAvoids;

	private java.util.Collection<VoteFace> vote;

	private java.util.Collection<ResultFace> result;

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

	public Integer getOpLock() {
		return opLock;
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
				oldExpert.removeAcception(this);
			}
			if (newExpert != null) {
				this.expert = newExpert;
				this.expert.addAcception(this);
			}
		}
	}

	public Project getProject() {
		return project;
	}

	@Override
	public void setProject(Project newProject) {
		if (this.project == null || this.project != newProject) {
			if (this.project != null) {
				Project oldProject = this.project;
				this.project = null;
				oldProject.removeAcception(this);
			}
			if (newProject != null) {
				this.project = newProject;
				this.project.addAcception(this);
			}
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
		for (java.util.Iterator<? extends VoteFace> iter = newVote.iterator(); iter
				.hasNext();)
			addVote((VoteFace) iter.next());
	}

	public void addVote(VoteFace newVote) {
		if (newVote == null)
			return;
		if (this.vote == null)
			this.vote = new java.util.LinkedHashSet<VoteFace>();
		if (!this.vote.contains(newVote)) {
			this.vote.add(newVote);
			newVote.setAcception(this);
		} else {
			for (VoteFace temp : this.vote) {
				if (newVote.equals(temp)) {
					if (temp != newVote) {
						removeVote(temp);
						this.vote.add(newVote);
						newVote.setAcception(this);
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
							temp.setAcception((Acception) null);
						}
						break;
					}
				}
				this.vote.remove(oldVote);
				oldVote.setAcception((Acception) null);
			}
	}

	public void removeAllVote() {
		if (vote != null) {
			VoteFace oldVote;
			for (java.util.Iterator<VoteFace> iter = getIteratorVote(); iter
					.hasNext();) {
				oldVote = (VoteFace) iter.next();
				iter.remove();
				oldVote.setAcception((Acception) null);
			}
			vote.clear();
		}
	}

	public java.util.Collection<AcceptionBucketFace> getAcceptionBucket() {
		if (acceptionBucket == null)
			acceptionBucket = new java.util.LinkedHashSet<AcceptionBucketFace>();
		return acceptionBucket;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<AcceptionBucketFace> getIteratorAcceptionBucket() {
		if (acceptionBucket == null)
			acceptionBucket = new java.util.LinkedHashSet<AcceptionBucketFace>();
		return acceptionBucket.iterator();
	}

	public void setAcceptionBucket(
			java.util.Collection<? extends AcceptionBucketFace> newAcceptionBucket) {
		removeAllAcceptionBucket();
		for (java.util.Iterator<? extends AcceptionBucketFace> iter = newAcceptionBucket
				.iterator(); iter.hasNext();)
			addAcceptionBucket((AcceptionBucketFace) iter.next());
	}

	public void addAcceptionBucket(AcceptionBucketFace newAcceptionBucket) {
		if (newAcceptionBucket == null)
			return;
		if (this.acceptionBucket == null)
			this.acceptionBucket = new java.util.LinkedHashSet<AcceptionBucketFace>();
		if (!this.acceptionBucket.contains(newAcceptionBucket)) {
			this.acceptionBucket.add(newAcceptionBucket);
			newAcceptionBucket.setAcception(this);
		} else {
			for (AcceptionBucketFace temp : this.acceptionBucket) {
				if (newAcceptionBucket.equals(temp)) {
					if (temp != newAcceptionBucket) {
						removeAcceptionBucket(temp);
						this.acceptionBucket.add(newAcceptionBucket);
						newAcceptionBucket.setAcception(this);
					}
					break;
				}
			}
		}
	}

	public void removeAcceptionBucket(AcceptionBucketFace oldAcceptionBucket) {
		if (oldAcceptionBucket == null)
			return;
		if (this.acceptionBucket != null)
			if (this.acceptionBucket.contains(oldAcceptionBucket)) {
				for (AcceptionBucketFace temp : this.acceptionBucket) {
					if (oldAcceptionBucket.equals(temp)) {
						if (temp != oldAcceptionBucket) {
							temp.setAcception((Acception) null);
						}
						break;
					}
				}
				this.acceptionBucket.remove(oldAcceptionBucket);
				oldAcceptionBucket.setAcception((Acception) null);
			}
	}

	public void removeAllAcceptionBucket() {
		if (acceptionBucket != null) {
			AcceptionBucketFace oldAcceptionBucket;
			for (java.util.Iterator<AcceptionBucketFace> iter = getIteratorAcceptionBucket(); iter
					.hasNext();) {
				oldAcceptionBucket = (AcceptionBucketFace) iter.next();
				iter.remove();
				oldAcceptionBucket.setAcception((Acception) null);
			}
			acceptionBucket.clear();
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

	public void setExpertAvoids(
			java.util.Collection<? extends ExpertAvoidsFace> newExpertAvoids) {
		removeAllExpertAvoids();
		for (java.util.Iterator<? extends ExpertAvoidsFace> iter = newExpertAvoids
				.iterator(); iter.hasNext();)
			addExpertAvoids((ExpertAvoidsFace) iter.next());
	}

	public void addExpertAvoids(ExpertAvoidsFace newExpertAvoids) {
		if (newExpertAvoids == null)
			return;
		if (this.expertAvoids == null)
			this.expertAvoids = new java.util.LinkedHashSet<ExpertAvoidsFace>();
		if (!this.expertAvoids.contains(newExpertAvoids)) {
			this.expertAvoids.add(newExpertAvoids);
			newExpertAvoids.setAcception(this);
		} else {
			for (ExpertAvoidsFace temp : this.expertAvoids) {
				if (newExpertAvoids.equals(temp)) {
					if (temp != newExpertAvoids) {
						removeExpertAvoids(temp);
						this.expertAvoids.add(newExpertAvoids);
						newExpertAvoids.setAcception(this);
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
							temp.setAcception((Acception) null);
						}
						break;
					}
				}
				this.expertAvoids.remove(oldExpertAvoids);
				oldExpertAvoids.setAcception((Acception) null);
			}
	}

	public void removeAllExpertAvoids() {
		if (expertAvoids != null) {
			ExpertAvoidsFace oldExpertAvoids;
			for (java.util.Iterator<ExpertAvoidsFace> iter = getIteratorExpertAvoids(); iter
					.hasNext();) {
				oldExpertAvoids = (ExpertAvoidsFace) iter.next();
				iter.remove();
				oldExpertAvoids.setAcception((Acception) null);
			}
			expertAvoids.clear();
		}
	}

	public java.util.Collection<ResultFace> getResult() {
		if (result == null)
			result = new java.util.LinkedHashSet<ResultFace>();
		return result;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<ResultFace> getIteratorResult() {
		if (result == null)
			result = new java.util.LinkedHashSet<ResultFace>();
		return result.iterator();
	}

	public void setResult(java.util.Collection<? extends ResultFace> newResult) {
		removeAllResult();
		for (java.util.Iterator<? extends ResultFace> iter = newResult
				.iterator(); iter.hasNext();)
			addResult((ResultFace) iter.next());
	}

	public void addResult(ResultFace newResult) {
		if (newResult == null)
			return;
		if (this.result == null)
			this.result = new java.util.LinkedHashSet<ResultFace>();
		if (!this.result.contains(newResult)) {
			this.result.add(newResult);
			newResult.setAcception(this);
		} else {
			for (ResultFace temp : this.result) {
				if (newResult.equals(temp)) {
					if (temp != newResult) {
						removeResult(temp);
						this.result.add(newResult);
						newResult.setAcception(this);
					}
					break;
				}
			}
		}
	}

	public void removeResult(ResultFace oldResult) {
		if (oldResult == null)
			return;
		if (this.result != null)
			if (this.result.contains(oldResult)) {
				for (ResultFace temp : this.result) {
					if (oldResult.equals(temp)) {
						if (temp != oldResult) {
							temp.setAcception((Acception) null);
						}
						break;
					}
				}
				this.result.remove(oldResult);
				oldResult.setAcception((Acception) null);
			}
	}

	public void removeAllResult() {
		if (result != null) {
			ResultFace oldResult;
			for (java.util.Iterator<ResultFace> iter = getIteratorResult(); iter
					.hasNext();) {
				oldResult = (ResultFace) iter.next();
				iter.remove();
				oldResult.setAcception((Acception) null);
			}
			result.clear();
		}
	}
}
