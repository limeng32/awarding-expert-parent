package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;
import cn.chinaunicom.awarding.expert.face.ExpertTeamFace;
import cn.chinaunicom.awarding.expert.face.ResultFace;
import cn.chinaunicom.awarding.expert.face.VoteFace;

import com.alibaba.fastjson.annotation.JSONField;

public class Award extends PojoSupport<Award> implements Serializable,
		cn.chinaunicom.awarding.expert.face.AwardFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	public java.lang.String id;

	/**
	 * 奖项名称
	 * 
	 */
	public java.lang.String name;

	public java.util.Collection<VoteFace> vote;

	public java.util.Collection<ResultFace> result;

	public java.util.Collection<ExpertTeamFace> expertTeam;

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
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
			newVote.setAward(this);
		} else {
			for (VoteFace temp : this.vote) {
				if (newVote.equals(temp)) {
					if (temp != newVote) {
						removeVote(temp);
						this.vote.add(newVote);
						newVote.setAward(this);
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
							temp.setAward((Award) null);
						}
						break;
					}
				}
				this.vote.remove(oldVote);
				oldVote.setAward((Award) null);
			}
	}

	public void removeAllVote() {
		if (vote != null) {
			VoteFace oldVote;
			for (java.util.Iterator<VoteFace> iter = getIteratorVote(); iter
					.hasNext();) {
				oldVote = (VoteFace) iter.next();
				iter.remove();
				oldVote.setAward((Award) null);
			}
			vote.clear();
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
			newResult.setAward(this);
		} else {
			for (ResultFace temp : this.result) {
				if (newResult.equals(temp)) {
					if (temp != newResult) {
						removeResult(temp);
						this.result.add(newResult);
						newResult.setAward(this);
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
							temp.setAward((Award) null);
						}
						break;
					}
				}
				this.result.remove(oldResult);
				oldResult.setAward((Award) null);
			}
	}

	public void removeAllResult() {
		if (result != null) {
			ResultFace oldResult;
			for (java.util.Iterator<ResultFace> iter = getIteratorResult(); iter
					.hasNext();) {
				oldResult = (ResultFace) iter.next();
				iter.remove();
				oldResult.setAward((Award) null);
			}
			result.clear();
		}
	}

	public java.util.Collection<ExpertTeamFace> getExpertTeam() {
		if (expertTeam == null)
			expertTeam = new java.util.LinkedHashSet<ExpertTeamFace>();
		return expertTeam;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<ExpertTeamFace> getIteratorExpertTeam() {
		if (expertTeam == null)
			expertTeam = new java.util.LinkedHashSet<ExpertTeamFace>();
		return expertTeam.iterator();
	}

	public void setExpertTeam(
			java.util.Collection<? extends ExpertTeamFace> newExpertTeam) {
		removeAllExpertTeam();
		for (java.util.Iterator<? extends ExpertTeamFace> iter = newExpertTeam
				.iterator(); iter.hasNext();)
			addExpertTeam((ExpertTeamFace) iter.next());
	}

	public void addExpertTeam(ExpertTeamFace newExpertTeam) {
		if (newExpertTeam == null)
			return;
		if (this.expertTeam == null)
			this.expertTeam = new java.util.LinkedHashSet<ExpertTeamFace>();
		if (!this.expertTeam.contains(newExpertTeam)) {
			this.expertTeam.add(newExpertTeam);
			newExpertTeam.setAward(this);
		} else {
			for (ExpertTeamFace temp : this.expertTeam) {
				if (newExpertTeam.equals(temp)) {
					if (temp != newExpertTeam) {
						removeExpertTeam(temp);
						this.expertTeam.add(newExpertTeam);
						newExpertTeam.setAward(this);
					}
					break;
				}
			}
		}
	}

	public void removeExpertTeam(ExpertTeamFace oldExpertTeam) {
		if (oldExpertTeam == null)
			return;
		if (this.expertTeam != null)
			if (this.expertTeam.contains(oldExpertTeam)) {
				for (ExpertTeamFace temp : this.expertTeam) {
					if (oldExpertTeam.equals(temp)) {
						if (temp != oldExpertTeam) {
							temp.setAward((Award) null);
						}
						break;
					}
				}
				this.expertTeam.remove(oldExpertTeam);
				oldExpertTeam.setAward((Award) null);
			}
	}

	public void removeAllExpertTeam() {
		if (expertTeam != null) {
			ExpertTeamFace oldExpertTeam;
			for (java.util.Iterator<ExpertTeamFace> iter = getIteratorExpertTeam(); iter
					.hasNext();) {
				oldExpertTeam = (ExpertTeamFace) iter.next();
				iter.remove();
				oldExpertTeam.setAward((Award) null);
			}
			expertTeam.clear();
		}
	}
}
