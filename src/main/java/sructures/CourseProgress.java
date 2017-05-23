package sructures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Max on 26.02.2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonalProgress", propOrder = {
        "personId",
        "courseId",
        "topicId",
        "about",
        "count",
        "mark"
})
public class CourseProgress {
    @XmlElement(name = "personId")
    private long personId;
    @XmlElement(name = "courseId")
    private long courseId;
    @XmlElement(name = "topicId")
    private long topicId;
    @XmlElement(name = "about")
    private String about;
    @XmlElement(name = "count")
    private long count;
    @XmlElement(name = "mark")
    private int mark;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

}