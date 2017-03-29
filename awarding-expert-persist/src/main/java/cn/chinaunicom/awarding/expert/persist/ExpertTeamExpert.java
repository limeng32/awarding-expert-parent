package cn.chinaunicom.awarding.expert.persist;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import cn.chinaunicom.awarding.expert.enums.ExpertTeamExpertStatus;
import indi.mybatis.flying.annotations.FieldMapperAnnotation;
import indi.mybatis.flying.annotations.TableMapperAnnotation;
import limeng32.mirage.util.pojo.PojoSupport;

@TableMapperAnnotation(tableName = "expertTeamExpert")
public class ExpertTeamExpert extends PojoSupport<ExpertTeamExpert>
		implements Serializable, cn.chinaunicom.awarding.expert.face.ExpertTeamExpertFace {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "expertTeamExpert_id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private java.lang.String id;

	/**
	 * 状态值，对应枚举类变量，包括“组长，副组长，组员”等状态
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "status", jdbcType = JdbcType.CHAR)
	private ExpertTeamExpertStatus status;

	@FieldMapperAnnotation(dbFieldName = "expert_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "expert_id")
	private Expert expert;

	@FieldMapperAnnotation(dbFieldName = "expertTeam_id", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "expertTeam_id")
	private ExpertTeam expertTeam;

	public static final String STATUS_LEADER = "组长";

	public static final String STATUS_DEPUTY = "副组长";

	public static final String STATUS_MEMBER = "组员";

	public ExpertTeamExpertStatus getStatus() {
		return status;
	}

	public void setStatus(ExpertTeamExpertStatus status) {
		this.status = status;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
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
				oldExpert.removeExpertTeamExpert(this);
			}
			if (newExpert != null) {
				this.expert = newExpert;
				this.expert.addExpertTeamExpert(this);
			}
		}
	}

	public ExpertTeam getExpertTeam() {
		return expertTeam;
	}

	@Override
	public void setExpertTeam(ExpertTeam newExpertTeam) {
		if (this.expertTeam == null || this.expertTeam != newExpertTeam) {
			if (this.expertTeam != null) {
				ExpertTeam oldExpertTeam = this.expertTeam;
				this.expertTeam = null;
				oldExpertTeam.removeExpertTeamExpert(this);
			}
			if (newExpertTeam != null) {
				this.expertTeam = newExpertTeam;
				this.expertTeam.addExpertTeamExpert(this);
			}
		}
	}

}
