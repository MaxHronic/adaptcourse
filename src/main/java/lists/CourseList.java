

/**
 * Created by Max on 26.02.2017.
 */
package lists;

import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sructures.AdaptError;
import sructures.Course;
import sructures.Queries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CourseList", propOrder = {
        "course"
})
public class CourseList {

    private static final Logger LOG = LogManager.getLogger(CourseList.class);
    @XmlElement(name = "Course", nillable = true)
    protected static ArrayList<Course> course;


    public static ArrayList<Course> getCourses(String request, Connection connect) throws AdaptError {
        LOG.info("Getting CourseList");
        ArrayList<Course> courses;
        Course item;
        courses = new ArrayList<Course>();
        CallableStatement stmt = null;
        try {
            stmt = connect.prepareCall(Queries.findCourses(request));
            LOG.info("Query prepared to execute ");
            ResultSet rs = stmt.executeQuery();
            LOG.info("Query executed!");

            while (rs.next()) {
                item = new Course();
                item.setCourseId(rs.getLong(1));//changed +1
                item.setSpeciality(rs.getString(2));//changed +1
                item.setName(rs.getString(3));//changed +1
                item.setAbout(rs.getString(4));
                item.setAuthor(rs.getString(5) + " " + rs.getString(6));
                item.setMark(rs.getInt(7));
                courses.add(item);
            }
            course = courses;
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


    public static ArrayList<Course> getMyCourses(long personId, Connection connect) throws AdaptError {
        LOG.info("Getting CourseList");
        ArrayList<Course> courses;
        Course item;
        courses = new ArrayList<Course>();
        CallableStatement stmt = null;
        try {
            stmt = connect.prepareCall(Queries.findMyCourses(personId));
            LOG.info("Query prepared to execute ");
            ResultSet rs = stmt.executeQuery();
            LOG.info("Query executed!");

            while (rs.next()) {
                item = new Course();
                item.setCourseId(rs.getLong(1));
                item.setName(rs.getString(2));
                item.setAbout(rs.getString(3));
//                item.setAuthor(rs.getString(5));
                item.setMark(rs.getInt(5));
                item.setSpeciality(rs.getString(6));
                courses.add(item);
            }
            course = courses;
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


}
