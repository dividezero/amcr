package com.angkasa.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

/**
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
@Entity
@Table(name = "survey_question")
@Indexed
@XmlRootElement
public class SurveyQuestion extends BaseObject implements Serializable {
    private static final long serialVersionUID = 3832626162173359416L;

    private Long id;
    private Long surveyId;
    private Survey survey;

    private String description;

    private String questionType;

    public static final String SUBJECTIVE = "SUBJECTIVE";
    public static final String MULTI_OPTION = "MULTI_OPTION";

    private Set<SurveyQuestionChoice> surveyQuestionChoices;

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

    @Column(name = "description", nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "question_type", nullable = false, length = 50)
    @Field
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @OneToMany(mappedBy = "surveyQuestion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<SurveyQuestionChoice> getSurveyQuestionChoices() {
        return surveyQuestionChoices;
    }

    public void setSurveyQuestionChoices(Set<SurveyQuestionChoice> surveyQuestionChoices) {
        this.surveyQuestionChoices = surveyQuestionChoices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyQuestion that = (SurveyQuestion) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (questionType != null ? !questionType.equals(that.questionType) : that.questionType != null) return false;
        if (survey != null ? !survey.equals(that.survey) : that.survey != null) return false;
        if (surveyId != null ? !surveyId.equals(that.surveyId) : that.surveyId != null) return false;
        if (surveyQuestionChoices != null ? !surveyQuestionChoices.equals(that.surveyQuestionChoices) : that.surveyQuestionChoices != null)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "SurveyQuestion{" +
                "id=" + id +
                ", surveyId=" + surveyId +
                ", survey=" + survey +
                ", description='" + description + '\'' +
                ", questionType='" + questionType + '\'' +
                ", surveyQuestionChoices=" + surveyQuestionChoices +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surveyId != null ? surveyId.hashCode() : 0);
        result = 31 * result + (survey != null ? survey.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (questionType != null ? questionType.hashCode() : 0);
        return result;
    }


}
