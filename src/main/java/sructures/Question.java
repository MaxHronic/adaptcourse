package sructures;

import javax.xml.bind.annotation.*;

/**
 * Created by Max on 26.02.2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Question", propOrder = {
        "questionId",
        "topicId",
        "text",
        "ansver1",
        "weight1",
        "ansver2",
        "weight2",
        "ansver3",
        "weight3",
        "ansver4",
        "weight4",
        "weight_q",
        "type_control"
})

public class Question {
    @XmlElement(name = "questionId")
    @XmlSchemaType(name = "long")
    protected long questionId;

    @XmlElement(name = "topicId")
    @XmlSchemaType(name = "string")
    protected long topicId;

    @XmlElement(name = "text")
    @XmlSchemaType(name = "string")
    protected String text;

    @XmlElement(name = "ansver1")
    @XmlSchemaType(name = "string")
    protected String ansver1;
    @XmlElement(name = "weight1")
    @XmlSchemaType(name = "string")
    protected float weight1;

    @XmlElement(name = "ansver2")
    @XmlSchemaType(name = "string")
    protected String ansver2;
    @XmlElement(name = "weight2")
    @XmlSchemaType(name = "string")
    protected float weight2;

    @XmlElement(name = "ansver3", nillable = true)
    @XmlSchemaType(name = "string")
    protected String ansver3;
    @XmlElement(name = "weight3", nillable = true)
    @XmlSchemaType(name = "string")
    protected float weight3;

    @XmlElement(name = "ansver4", nillable = true)
    @XmlSchemaType(name = "string")
    protected String ansver4;
    @XmlElement(name = "weight4", nillable = true)
    @XmlSchemaType(name = "string")
    protected float weight4;

    @XmlElement(name = "weight_q")
    @XmlSchemaType(name = "integer")
    protected int weight_q;

    @XmlElement(name = "type_control")
    @XmlSchemaType(name = "boolean")
    protected boolean type_control;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnsver1() {
        return ansver1;
    }

    public void setAnsver1(String ansver1) {
        this.ansver1 = ansver1;
    }

    public float getWeight1() {
        return weight1;
    }

    public void setWeight1(float weight1) {
        this.weight1 = weight1;
    }

    public String getAnsver2() {
        return ansver2;
    }

    public void setAnsver2(String ansver2) {
        this.ansver2 = ansver2;
    }

    public float getWeight2() {
        return weight2;
    }

    public void setWeight2(float weight2) {
        this.weight2 = weight2;
    }

    public String getAnsver3() {
        return ansver3;
    }

    public void setAnsver3(String ansver3) {
        this.ansver3 = ansver3;
    }

    public float getWeight3() {
        return weight3;
    }

    public void setWeight3(int weight3) {
        this.weight3 = weight3;
    }

    public String getAnsver4() {
        return ansver4;
    }

    public void setAnsver4(String ansver4) {
        this.ansver4 = ansver4;
    }

    public float getWeight4() {
        return weight4;
    }

    public void setWeight4(float weight4) {
        this.weight4 = weight4;
    }

    public int getQuestionWeight() {
        return weight_q;
    }

    public void setQuestionWeight(int weight_q) {
        this.weight_q = weight_q;
    }

    public boolean getType() {
        return type_control;
    }

    public void setType(boolean type_control) {
        this.type_control = type_control;
    }

}
