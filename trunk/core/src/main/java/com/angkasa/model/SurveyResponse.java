package com.angkasa.model;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "survey_response")
//@Indexed
@XmlRootElement
public class SurveyResponse extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    private Long id;
    private Long surveyId;
    private Survey survey;

    private Long coopMemberId;
    private CoopMember coopMember;

    private Date submitTime;

    @Override
    @Transient
    public String getPrimaryKey() {
        return getStringKey(this.id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "survey_id", nullable = false)
    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    @ManyToOne
    @JoinColumn(name = "survey_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    @Column(name = "coop_member_id", nullable = true)
    public Long getCoopMemberId() {
        return coopMemberId;
    }

    public void setCoopMemberId(Long coopMemberId) {
        this.coopMemberId = coopMemberId;
    }

    @ManyToOne
    @JoinColumn(name = "coop_member_id", referencedColumnName = "id", insertable = false, updatable = false)
    public CoopMember getCoopMember() {
        return coopMember;
    }

    public void setCoopMember(CoopMember coopMember) {
        this.coopMember = coopMember;
    }

    @Column(name = "submit_time", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
//    @Field
    @DateBridge(resolution = Resolution.DAY)
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "SurveyResponse{" +
                "id=" + id +
                ", surveyId=" + surveyId +
                ", survey=" + survey +
                ", coopMemberId=" + coopMemberId +
                ", coopMember=" + coopMember +
                ", submitTime=" + submitTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyResponse that = (SurveyResponse) o;

        if (coopMember != null ? !coopMember.equals(that.coopMember) : that.coopMember != null) return false;
        if (coopMemberId != null ? !coopMemberId.equals(that.coopMemberId) : that.coopMemberId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (survey != null ? !survey.equals(that.survey) : that.survey != null) return false;
        if (surveyId != null ? !surveyId.equals(that.surveyId) : that.surveyId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surveyId != null ? surveyId.hashCode() : 0);
        result = 31 * result + (survey != null ? survey.hashCode() : 0);
        result = 31 * result + (coopMemberId != null ? coopMemberId.hashCode() : 0);
        result = 31 * result + (coopMember != null ? coopMember.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        return result;
    }
}
