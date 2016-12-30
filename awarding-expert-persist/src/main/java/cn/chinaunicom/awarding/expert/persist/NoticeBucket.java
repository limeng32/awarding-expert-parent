package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import limeng32.mirage.util.pojo.PojoSupport;

public class NoticeBucket extends PojoSupport<NoticeBucket> implements
		Serializable, cn.chinaunicom.awarding.expert.face.NoticeBucketFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	private java.lang.String id;
	/**
	 * 内容
	 * 
	 */
	private java.lang.String content;

	private Notice notice;

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public Notice getNotice() {
		return notice;
	}

	@Override
	public void setNotice(Notice newNotice) {
		if (this.notice == null || this.notice != newNotice) {
			if (this.notice != null) {
				Notice oldNotice = this.notice;
				this.notice = null;
				oldNotice.removeNoticeBucket(this);
			}
			if (newNotice != null) {
				this.notice = newNotice;
				this.notice.addNoticeBucket(this);
			}
		}
	}
}
