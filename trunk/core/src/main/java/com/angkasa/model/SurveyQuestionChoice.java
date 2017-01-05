package com.angkasa.model;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "survey_question_choice")
//@Indexed
@XmlRootElement
public class SurveyQuestionChoice extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    private Long id;
    private Long surveyQuestionId;
    private SurveyQuestion surveyQuestion;

    private int sequence;
    private String description;
    private String value;

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

    @Column(name = "survey_question_id", nullable = false)
    public Long getSurveyQuestionId() {
        return surveyQuestionId;
    }

    public void setSurveyQuestionId(Long surveyQuestionId) {
        this.surveyQuestionId = surveyQuestionId;
    }

    @ManyToOne
    @JoinColumn(name = "survey_question_id", referencedColumnName = "id", insertable = false, updatable = false)
    public SurveyQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }

    @Column(nullable = false)
    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Column(name = "description", nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "value", nullable = false, length = 200)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SurveyQuestionChoice{" +
                "id=" + id +
                ", surveyQuestionId=" + surveyQuestionId +
                ", surveyQuestion=" + surveyQuestion +
                ", sequence=" + sequence +
                ", description='" + description + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyQuestionChoice that = (SurveyQuestionChoice) o;

        if (sequence != that.sequence) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (surveyQuestion != null ? !surveyQuestion.equals(that.surveyQuestion) : that.surveyQuestion != null)
            return false;
        if (surveyQuestionId != null ? !surveyQuestionId.equals(that.surveyQuestionId) : that.surveyQuestionId != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surveyQuestionId != null ? surveyQuestionId.hashCode() : 0);
        result = 31 * result + (surveyQuestion != null ? surveyQuestion.hashCode() : 0);
        result = 31 * result + sequence;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
