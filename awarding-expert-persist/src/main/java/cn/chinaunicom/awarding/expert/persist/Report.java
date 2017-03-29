package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import cn.chinaunicom.awarding.expert.enums.ReportStatus;
import cn.chinaunicom.awarding.project.persist.Task;
import indi.mybatis.flying.annotations.FieldMapperAnnotation;
import indi.mybatis.flying.annotations.TableMapperAnnotation;
import limeng32.mirage.util.pojo.PojoSupport;

@TableMapperAnnotation(tableName = "report")
public class Report extends PojoSupport<Report> implements Serializable,
		cn.chinaunicom.awarding.project.face.ReportFace, cn.chinaunicom.awarding.expert.face.ReportFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "report_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;

	/**
	 * 状态值，对应枚举类变量，包括“项目申报报表、项目回避报表”等状态
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.VARCHAR)
	private ReportStatus status;

	@FieldMapperAnnotation(dbFieldName = "task_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "task_id")
	private Task task;

	public static final String STATUS_AVOIDS = "项目回避报表";

	public static final String STATUS_DECLARATION = "项目申报报表";

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public ReportStatus getStatus() {
		return status;
	}

	public void setStatus(ReportStatus status) {
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
				oldTask.removeReport(this);
			}
			if (newTask != null) {
				this.task = newTask;
				this.task.addReport(this);
			}
		}
	}
}
