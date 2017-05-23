package sructures;

/**
 * Created by Max on 26.02.2017.
 */

import config.Config;
import lists.CourseList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Course", propOrder = {
        "courseId",
        "speciality",
        "name",
        "about",
        "author",
        "mark"
})
public class Course {
    private static final Logger LOG = LogManager.getLogger(CourseList.class);
    @XmlElement(name = "courseId")
    private long courseId;
    @XmlElement(name = "speciality")
    private String speciality;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "about")
    private String about;
    @XmlElement(name = "author")
    private String author;
    //    @XmlElement(name = "topics")
//    private int topics;
    @XmlElement(name = "mark")
    private int mark;

    public static Course getCourse(long courseId, Connection connect) throws AdaptError {
        LOG.info("Getting Course");
        Course course = new Course();
        Course item;
        CallableStatement stmt = null;
        try {
            stmt = connect.prepareCall(Queries.getCourse(courseId));
            LOG.info("Query prepared to execute ");
            ResultSet rs = stmt.executeQuery();
            LOG.info("Query executed!");

            while (rs.next()) {
                item = new Course();
                item.setCourseId(rs.getLong(1));
                item.setSpeciality(rs.getString(2));
                item.setName(rs.getString(3));
                item.setAbout(rs.getString(4));
                item.setAuthor(rs.getString(5) + " " + rs.getString(6));
                item.setMark(rs.getInt(7));

                course = item;
            }
        } catch (SQLException e) {
            LOG.info(MessageFormat.format("STACKTRACE: {0}\n{1} {2}", e.getSQLState(), e.getErrorCode(), e.getMessage()));
            e.printStackTrace();
            throw new AdaptError(Config.CODES.c941.id, "Error connection to DB try later");
        } finally {
            Config.closeStmt(stmt);
        }
        LOG.info("Getting CourseList successful");
//        }
        return course;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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

//    public int getTopics() {
//        return topics;
//    }
//
//    public void setTopics(int topics) {
//        this.topics = topics;
//    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}

