package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import indi.mybatis.flying.annotations.FieldMapperAnnotation;
import indi.mybatis.flying.annotations.TableMapperAnnotation;
import limeng32.mirage.util.pojo.PojoSupport;

@TableMapperAnnotation(tableName = "noticeBucket")
public class NoticeBucket extends PojoSupport<NoticeBucket>
		implements Serializable, cn.chinaunicom.awarding.expert.face.NoticeBucketFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "noticeBucket_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;

	/**
	 * 内容
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "content", jdbcType = JdbcType.VARCHAR)
	private java.lang.String content;

	@FieldMapperAnnotation(dbFieldName = "notice_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "notice_id")
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
