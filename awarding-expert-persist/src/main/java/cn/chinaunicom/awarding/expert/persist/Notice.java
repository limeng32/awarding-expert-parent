package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;
import cn.chinaunicom.awarding.expert.face.NoticeBucketFace;
import cn.chinaunicom.awarding.project.persist.Task;

import com.alibaba.fastjson.annotation.JSONField;

public class Notice extends PojoSupport<Notice> implements Serializable,
		cn.chinaunicom.awarding.project.face.NoticeFace,
		cn.chinaunicom.awarding.expert.face.NoticeFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	private java.lang.String id;
	/**
	 * 状态值，对应枚举类变量，包括“项目申报须知、评审会会议须知”等状态
	 * 
	 */
	private java.lang.String status;

	private java.util.Collection<NoticeBucketFace> noticeBucket;

	private Task task;

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

	public Task getTask() {
		return task;
	}

	@Override
	public void setTask(Task newTask) {
		if (this.task == null || this.task != newTask) {
			if (this.task != null) {
				Task oldTask = this.task;
				this.task = null;
				oldTask.removeNotice(this);
			}
			if (newTask != null) {
				this.task = newTask;
				this.task.addNotice(this);
			}
		}
	}

	public java.util.Collection<NoticeBucketFace> getNoticeBucket() {
		if (noticeBucket == null)
			noticeBucket = new java.util.LinkedHashSet<NoticeBucketFace>();
		return noticeBucket;
	}

	@JSONField(serialize = false)
	public java.util.Iterator<NoticeBucketFace> getIteratorNoticeBucket() {
		if (noticeBucket == null)
			noticeBucket = new java.util.LinkedHashSet<NoticeBucketFace>();
		return noticeBucket.iterator();
	}

	public void setNoticeBucket(
			java.util.Collection<? extends NoticeBucketFace> newNoticeBucket) {
		removeAllNoticeBucket();
		for (java.util.Iterator<? extends NoticeBucketFace> iter = newNoticeBucket
				.iterator(); iter.hasNext();)
			addNoticeBucket((NoticeBucketFace) iter.next());
	}

	public void addNoticeBucket(NoticeBucketFace newNoticeBucket) {
		if (newNoticeBucket == null)
			return;
		if (this.noticeBucket == null)
			this.noticeBucket = new java.util.LinkedHashSet<NoticeBucketFace>();
		if (!this.noticeBucket.contains(newNoticeBucket)) {
			this.noticeBucket.add(newNoticeBucket);
			newNoticeBucket.setNotice(this);
		} else {
			for (NoticeBucketFace temp : this.noticeBucket) {
				if (newNoticeBucket.equals(temp)) {
					if (temp != newNoticeBucket) {
						removeNoticeBucket(temp);
						this.noticeBucket.add(newNoticeBucket);
						newNoticeBucket.setNotice(this);
					}
					break;
				}
			}
		}
	}

	public void removeNoticeBucket(NoticeBucketFace oldNoticeBucket) {
		if (oldNoticeBucket == null)
			return;
		if (this.noticeBucket != null)
			if (this.noticeBucket.contains(oldNoticeBucket)) {
				for (NoticeBucketFace temp : this.noticeBucket) {
					if (oldNoticeBucket.equals(temp)) {
						if (temp != oldNoticeBucket) {
							temp.setNotice((Notice) null);
						}
						break;
					}
				}
				this.noticeBucket.remove(oldNoticeBucket);
				oldNoticeBucket.setNotice((Notice) null);
			}
	}

	public void removeAllNoticeBucket() {
		if (noticeBucket != null) {
			NoticeBucketFace oldNoticeBucket;
			for (java.util.Iterator<NoticeBucketFace> iter = getIteratorNoticeBucket(); iter
					.hasNext();) {
				oldNoticeBucket = (NoticeBucketFace) iter.next();
				iter.remove();
				oldNoticeBucket.setNotice((Notice) null);
			}
			noticeBucket.clear();
		}
	}
}
