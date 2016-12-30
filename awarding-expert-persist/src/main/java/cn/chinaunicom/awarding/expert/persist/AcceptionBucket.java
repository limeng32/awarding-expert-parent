package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;

public class AcceptionBucket extends PojoSupport<AcceptionBucket> implements
		Serializable, cn.chinaunicom.awarding.expert.face.AcceptionBucketFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	private java.lang.String id;
	/**
	 * 评审意见
	 * 
	 */
	private java.lang.String review;

	private Acception acception;

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getReview() {
		return review;
	}

	public void setReview(java.lang.String review) {
		this.review = review;
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
				oldAcception.removeAcceptionBucket(this);
			}
			if (newAcception != null) {
				this.acception = newAcception;
				this.acception.addAcceptionBucket(this);
			}
		}
	}
}
