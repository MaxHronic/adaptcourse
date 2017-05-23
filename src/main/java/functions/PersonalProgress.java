package functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Max on 26.02.2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonalProgress", propOrder = {
        "courseName",
        "topicName",
        "count",
        "mark"
})
public class PersonalProgress {
    @XmlElement(name = "courseName")
    private String courseName;
    @XmlElement(name = "topicName")
    private String topicName;
    @XmlElement(name = "count")
    private int count;
    @XmlElement(name = "mark")
    private int mark;


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

}