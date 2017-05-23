

/**
 * Created by Max on 26.02.2017.
 */
package lists;

import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sructures.AdaptError;
import sructures.Queries;
import sructures.Topic;

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
@XmlType(name = "TopicList", propOrder = {
        "topicList"
})
public class TopicList {

    private static final Logger LOG = LogManager.getLogger(TopicList.class);
    @XmlElement(name = "Topic", nillable = true)
    protected static ArrayList<Topic> topicList;

    /**
     * @param courseId -
     * @param connect  - коннект к базе данных
     * @return - возвращает список досутпных продуктов
     * @throws sructures.AdaptError
     */
    public static ArrayList<Topic> getTopics(long courseId, Connection connect) throws AdaptError {
        LOG.info("Getting TopicList");
        ArrayList<Topic> topics;
        Topic item;
        topics = new ArrayList<Topic>();
        CallableStatement stmt = null;
        try {
            stmt = connect.prepareCall(Queries.getCourseTopics(courseId));
            LOG.info("Query prepared to execute ");
            ResultSet rs = stmt.executeQuery();
            LOG.info("Query executed!");

            while (rs.next()) {
                item = new Topic();
                item.setTopicId(rs.getLong(1));
                item.setIn_courseId(rs.getLong(2));
                item.setName(rs.getString(3));
                item.setAbout(rs.getString(4));
//                item.setAccess(rs.getBoolean(5));
                topics.add(item);
            }
            topicList = topics;
        } catch (SQLException e) {
            LOG.info(MessageFormat.format("STACKTRACE: {0}\n{1} {2}", e.getSQLState(), e.getErrorCode(), e.getMessage()));
            e.printStackTrace();
            throw new AdaptError(Config.CODES.c941.id, "Error connection to DB try later");
        } finally {
            Config.closeStmt(stmt);
        }
        LOG.info("Getting CourseList successful");
//        }
        return topicList;
    }

    public static ArrayList<Topic> getMyTopics(long personId, long courseId, Connection connect) throws AdaptError {
        LOG.info("Getting getMyTopics");
        ArrayList<Topic> topics;
        Topic item;
        topics = new ArrayList<Topic>();
        CallableStatement stmt = null;
        try {
            stmt = connect.prepareCall(Queries.getMyCourseTopics(personId, courseId));

            LOG.info("Query prepared to execute ");
            ResultSet rs = stmt.executeQuery();
            LOG.info("Query executed!");

            while (rs.next()) {
                item = new Topic();
                item.setTopicId(rs.getLong(1));
                item.setIn_courseId(rs.getLong(2));
                item.setName(rs.getString(3));
                item.setAbout(rs.getString(4));
                item.setAccess(rs.getBoolean(5));

                topics.add(item);
            }
            topicList = topics;
        } catch (SQLException e) {
            LOG.info(MessageFormat.format("STACKTRACE: {0}\n{1} {2}", e.getSQLState(), e.getErrorCode(), e.getMessage()));
            e.printStackTrace();
            throw new AdaptError(Config.CODES.c941.id, "Error connection to DB try later");
        } finally {
            Config.closeStmt(stmt);
        }
        LOG.info("Getting CourseList successful");
//        }
        return topicList;
    }


}
