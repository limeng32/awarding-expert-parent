package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;
import limeng32.mybatis.mybatisPlugin.mapperPlugin.annotation.FieldMapperAnnotation;
import limeng32.mybatis.mybatisPlugin.mapperPlugin.annotation.TableMapperAnnotation;

import org.apache.ibatis.type.JdbcType;

import cn.chinaunicom.awarding.expert.enums.AwardLevel;
import cn.chinaunicom.awarding.expert.face.GalleryFace;
import cn.chinaunicom.awarding.project.persist.Task;

import com.alibaba.fastjson.annotation.JSONField;

@TableMapperAnnotation(tableName = "result")
public class Result extends PojoSupport<Result> implements Serializable,
		cn.chinaunicom.awarding.project.face.ResultFace,
		cn.chinaunicom.awarding.expert.face.ResultFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "result_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;

	/**
	 * 级别值，对应枚举类变量，包括“特等奖、一等奖、二等奖、三等奖、优秀奖”等级别
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "level", jdbcType = JdbcType.CHAR)
	private AwardLevel level;

	@FieldMapperAnnotation(dbFieldName = "award_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "award_id")
	private Award award;

	@FieldMapperAnnotation(dbFieldName = "acception_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "acception_id")
	private Acception acception;

	@FieldMapperAnnotation(dbFieldName = "task_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "task_id")
	private Task task;

	private java.util.Collection<GalleryFace> gallery;

	public static final String LEVEL_OUTSTANDING = "特等奖";

	public static final String LEVEL_FIRST = "一等奖";

	public static final String LEVEL_SECOND = "二等奖";

	public static final String LEVEL_THIRD = "三等奖";

	public static final String LEVEL_HONORABLE = "优秀奖";

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public AwardLevel getLevel() {
		return level;
	}

	public void setLevel(AwardLevel level) {
		this.level = level;
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
				oldAward.removeResult(this);
			}
			if (newAward != null) {
				this.award = newAward;
				this.award.addResult(this);
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
				oldAcception.removeResult(this);
			}
			if (newAcception != null) {
				this.acception = newAcception;
				this.acception.addResult(this);
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
				oldTask.removeResult(this);
			}
			if (newTask != null) {
				this.task = newTask;
				this.task.addResult(this);
			}
		}
	}

	public java.util.Collection<GalleryFace> getGallery() {
		if (gallery == null)
			gallery = new java.util.LinkedHashSet<GalleryFace>();
		return gallery;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<GalleryFace> getIteratorGallery() {
		if (gallery == null)
			gallery = new java.util.LinkedHashSet<GalleryFace>();
		return gallery.iterator();
	}

	public void setGallery(
			java.util.Collection<? extends GalleryFace> newGallery) {
		removeAllGallery();
		for (java.util.Iterator<? extends GalleryFace> iter = newGallery
				.iterator(); iter.hasNext();)
			addGallery((GalleryFace) iter.next());
	}

	public void addGallery(GalleryFace newGallery) {
		if (newGallery == null)
			return;
		if (this.gallery == null)
			this.gallery = new java.util.LinkedHashSet<GalleryFace>();
		if (!this.gallery.contains(newGallery)) {
			this.gallery.add(newGallery);
			newGallery.setResult(this);
		} else {
			for (GalleryFace temp : this.gallery) {
				if (newGallery.equals(temp)) {
					if (temp != newGallery) {
						removeGallery(temp);
						this.gallery.add(newGallery);
						newGallery.setResult(this);
					}
					break;
				}
			}
		}
	}

	public void removeGallery(GalleryFace oldGallery) {
		if (oldGallery == null)
			return;
		if (this.gallery != null)
			if (this.gallery.contains(oldGallery)) {
				for (GalleryFace temp : this.gallery) {
					if (oldGallery.equals(temp)) {
						if (temp != oldGallery) {
							temp.setResult((Result) null);
						}
						break;
					}
				}
				this.gallery.remove(oldGallery);
				oldGallery.setResult((Result) null);
			}
	}

	public void removeAllGallery() {
		if (gallery != null) {
			GalleryFace oldGallery;
			for (java.util.Iterator<GalleryFace> iter = getIteratorGallery(); iter
					.hasNext();) {
				oldGallery = (GalleryFace) iter.next();
				iter.remove();
				oldGallery.setResult((Result) null);
			}
			gallery.clear();
		}
	}
}
