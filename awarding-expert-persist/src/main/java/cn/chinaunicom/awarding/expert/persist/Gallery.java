package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;

public class Gallery extends PojoSupport<Gallery> implements Serializable,
		cn.chinaunicom.awarding.expert.face.GalleryFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	private java.lang.String id;
	/**
	 * 创建时间
	 * 
	 */
	private java.util.Date createTime;
	/**
	 * 封面图url
	 * 
	 */
	private java.lang.String surface;
	/**
	 * 简介
	 * 
	 */
	private java.lang.String synopsis;

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getSurface() {
		return surface;
	}

	public void setSurface(java.lang.String surface) {
		this.surface = surface;
	}

	public java.lang.String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(java.lang.String synopsis) {
		this.synopsis = synopsis;
	}

	private Result result;

	public Result getResult() {
		return result;
	}

	@Override
	public void setResult(Result newResult) {
		if (this.result == null || this.result != newResult) {
			if (this.result != null) {
				Result oldResult = this.result;
				this.result = null;
				oldResult.removeGallery(this);
			}
			if (newResult != null) {
				this.result = newResult;
				this.result.addGallery(this);
			}
		}
	}

}
