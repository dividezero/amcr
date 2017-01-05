package com.angkasa.model;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "survey_response_answer")
//@Indexed
@XmlRootElement
public class SurveyResponseAnswer extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    private Long id;
    private Long surveyResponseId;
    private SurveyResponse surveyResponse;

    private Long surveyQuestionChoiceId;
    private SurveyQuestionChoice surveyQuestionChoice;
    private String answerValue;

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

    @Column(name = "survey_response_id", nullable = false)
    public Long getSurveyResponseId() {
        return surveyResponseId;
    }

    public void setSurveyResponseId(Long surveyResponseId) {
        this.surveyResponseId = surveyResponseId;
    }

    @ManyToOne
    @JoinColumn(name = "survey_response_id", referencedColumnName = "id", insertable = false, updatable = false)
    public SurveyResponse getSurveyResponse() {
        return surveyResponse;
    }

    public void setSurveyResponse(SurveyResponse surveyResponse) {
        this.surveyResponse = surveyResponse;
    }

    @Column(name = "survey_question_choice_id", nullable = true)
    public Long getSurveyQuestionChoiceId() {
        return surveyQuestionChoiceId;
    }

    public void setSurveyQuestionChoiceId(Long surveyQuestionChoiceId) {
        this.surveyQuestionChoiceId = surveyQuestionChoiceId;
    }

    @ManyToOne
    @JoinColumn(name = "survey_question_choice_id", referencedColumnName = "id", insertable = false, updatable = false)
    public SurveyQuestionChoice getSurveyQuestionChoice() {
        return surveyQuestionChoice;
    }

    public void setSurveyQuestionChoice(SurveyQuestionChoice surveyQuestionChoice) {
        this.surveyQuestionChoice = surveyQuestionChoice;
    }

    @Column(name = "answer_value", nullable = true)
    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    @Override
    public String toString() {
        return "SurveyResponseAnswer{" +
                "id=" + id +
                ", surveyResponseId=" + surveyResponseId +
                ", surveyResponse=" + surveyResponse +
                ", surveyQuestionChoiceId=" + surveyQuestionChoiceId +
                ", surveyQuestionChoice=" + surveyQuestionChoice +
                ", answerValue='" + answerValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyResponseAnswer that = (SurveyResponseAnswer) o;

        if (answerValue != null ? !answerValue.equals(that.answerValue) : that.answerValue != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (surveyQuestionChoice != null ? !surveyQuestionChoice.equals(that.surveyQuestionChoice) : that.surveyQuestionChoice != null)
            return false;
        if (surveyQuestionChoiceId != null ? !surveyQuestionChoiceId.equals(that.surveyQuestionChoiceId) : that.surveyQuestionChoiceId != null)
            return false;
        if (surveyResponse != null ? !surveyResponse.equals(that.surveyResponse) : that.surveyResponse != null)
            return false;
        if (surveyResponseId != null ? !surveyResponseId.equals(that.surveyResponseId) : that.surveyResponseId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surveyResponseId != null ? surveyResponseId.hashCode() : 0);
        result = 31 * result + (surveyResponse != null ? surveyResponse.hashCode() : 0);
        result = 31 * result + (surveyQuestionChoiceId != null ? surveyQuestionChoiceId.hashCode() : 0);
        result = 31 * result + (surveyQuestionChoice != null ? surveyQuestionChoice.hashCode() : 0);
        result = 31 * result + (answerValue != null ? answerValue.hashCode() : 0);
        return result;
    }
}
