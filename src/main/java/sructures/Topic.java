package sructures;

/**
 * Created by Max on 26.02.2017.
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Topic", propOrder = {
//        "courseId",
//        "personId",
        "in_courseId",
        "topicId",
        "name",
        "about",
//        "extText",
        "access"
})
public class Topic {
    private static final Logger LOG = LogManager.getLogger(Topic.class);
    //    @XmlElement(name = "courseId")
//    private long courseId;
//    @XmlElement(name = "personId")
//    private long personId;
    @XmlElement(name = "in_courseId")
    private long in_courseId;
    @XmlElement(name = "topicId")
    private long topicId;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "about")
    private String about;
    //    @XmlElement(name = "extText")
//    private String extText;
    @XmlElement(name = "access")
    private boolean access;


//    public long getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(long courseId) {
//        this.courseId = courseId;
//    }
//
//
//    public long getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(long personId) {
//        this.personId = personId;
//    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getIn_courseId() {
        return in_courseId;
    }

    public void setIn_courseId(long in_courseId) {
        this.in_courseId = in_courseId;
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

    public boolean getAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}

