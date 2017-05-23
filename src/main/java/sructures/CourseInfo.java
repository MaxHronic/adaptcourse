package sructures;

import lists.TopicPages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

/**
 * Created by Max on 05.03.2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CourseInfo", propOrder = {
        "courseId",
        "name",
        "topics",
        "mark",
})
public class CourseInfo {
    @XmlElement(name = "courseId", nillable = true)
    private long courseId;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "about")
    private String about;
    @XmlElement(name = "topics")
    private ArrayList<TopicPages> topics;
    @XmlElement(name = "mark")
    private int mark;

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public ArrayList<TopicPages> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<TopicPages> topics) {
        this.topics = topics;
    }

    public long getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

}

