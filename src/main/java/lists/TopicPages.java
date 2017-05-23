package lists;

/**
 * Created by Max on 26.02.2017.
 */

import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sructures.AdaptError;
import sructures.Page;
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
@XmlType(name = "TopicPages", propOrder = {
        "topicPages"
})
public class TopicPages {

    private static final Logger LOG = LogManager.getLogger(TopicList.class);
    @XmlElement(name = "Page", nillable = true)
    protected static ArrayList<Page> topicPages;

    /**
     * @param connect - коннект к базе данных
     * @return - возвращает список досутпных продуктов
     * @throws sructures.AdaptError
     */
    public static ArrayList<Page> getTopicPages(long topicId, Connection connect) throws AdaptError {
        LOG.info("Getting TopicList");
        ArrayList<Page> topics;
        Page item;
        topics = new ArrayList<>();
        CallableStatement stmt = null;
        try {
            stmt = connect.prepareCall(Queries.getTopicPages(topicId));
            LOG.info("Query prepared to execute ");
            ResultSet rs = stmt.executeQuery();
            LOG.info("Query executed!");

            while (rs.next()) {
                item = new Page();
                item.setTopicId(rs.getLong(1));
                item.setPageId(rs.getLong(2));
                item.setText(rs.getString(3));
                item.setExtText(rs.getString(4));
                item.setIn_topicId(rs.getLong(5));
                topics.add(item);
            }
            topicPages = topics;
        } catch (SQLException e) {
            LOG.info(MessageFormat.format("STACKTRACE: {0}\n{1} {2}", e.getSQLState(), e.getErrorCode(), e.getMessage()));
            e.printStackTrace();
            throw new AdaptError(Config.CODES.c941.id, "Error connection to DB try later");
        } finally {
            Config.closeStmt(stmt);
        }
        LOG.info("Getting CourseList successful");
//        }
        return topicPages;
    }
}

