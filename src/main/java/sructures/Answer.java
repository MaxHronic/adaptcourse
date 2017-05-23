package sructures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Max on 05.03.2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Answer", propOrder = {
        "topicId",
        "questionId",
        "q_weight",
        "a_weight"
})
public class Answer {
    @XmlElement(name = "topicId", nillable = true)
    private long topicId;

    @XmlElement(name = "questionId")
    private long questionId;

    @XmlElement(name = "q_weight")
    private int q_weight;

    @XmlElement(name = "a_weight")
    private int a_weight;

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getQ_weight() {
        return q_weight;
    }

    public void setQ_weight(int q_weight) {
        this.q_weight = q_weight;
    }

    public int getA_weight() {
        return a_weight;
    }

    public void setA_weight(int a_weight) {
        this.a_weight = a_weight;
    }

}