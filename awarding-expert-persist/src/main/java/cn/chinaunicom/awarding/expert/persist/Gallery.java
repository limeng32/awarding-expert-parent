package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import indi.mybatis.flying.annotations.FieldMapperAnnotation;
import indi.mybatis.flying.annotations.TableMapperAnnotation;
import limeng32.mirage.util.pojo.PojoSupport;

@TableMapperAnnotation(tableName = "gallery")
public class Gallery extends PojoSupport<Gallery>
		implements Serializable, cn.chinaunicom.awarding.expert.face.GalleryFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "gallery_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;

	/**
	 * 创建时间
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "createTime", jdbcType = JdbcType.TIMESTAMP)
	private java.util.Date createTime;

	/**
	 * 封面图url
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "surface", jdbcType = JdbcType.VARCHAR)
	private java.lang.String surface;

	/**
	 * 简介
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "synopsis", jdbcType = JdbcType.VARCHAR)
	private java.lang.String synopsis;

	@FieldMapperAnnotation(dbFieldName = "result_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "result_id")
	private Result result;

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
