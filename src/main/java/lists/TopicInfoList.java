//
//
///**
// * Created by Max on 26.02.2017.
// */
//package lists;
//
//import config.Config;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import sructures.AdaptError;
//import sructures.Queries;
//
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlType;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.MessageFormat;
//import java.util.ArrayList;
//
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "TopicInfoList", propOrder = {
//        "infoList"
//})
//public class TopicInfoList {
//
//    private static final Logger LOG = LogManager.getLogger(TopicInfoList.class);
//    @XmlElement(name = "Topic", nillable = true)
//    protected static ArrayList<TopicPages> infoList;
//
//    /**
//     * @param personId -
//     * @param courseId  -
//     * @param connect  - коннект к базе данных
//     * @return - возвращает список досутпных продуктов
//     * @throws sructures.AdaptError
//     */
//    public static ArrayList<TopicPages> getTopics(long personId, long courseId, Connection connect) throws AdaptError {
//        LOG.info("Getting TopicList");
//        ArrayList<TopicPages> topics;
//        TopicPages item;
//        topics = new ArrayList<TopicPages>();
//        CallableStatement stmt = null;
//        try {
//            //TODO set as info request
//            stmt = connect.prepareCall(Queries.getCourseTopics(courseId));
//            stmt.setLong(1,personId);
//            stmt.setLong(2,courseId);
//            LOG.info("Query prepared to execute ");
//            ResultSet rs = stmt.executeQuery();
//            LOG.info("Query executed!");
//
//            while (rs.next()) {
//                item = new TopicPages();
//                item.setTopicId(rs.getLong(1));
//                item.setName(rs.getString(2));//changed +1
//                item.setCounter(rs.getLong(3));//changed +1
//                item.setMark(rs.getInt(4));
//                topics.add(item);
//            }
//            infoList = topics;
//        } catch (SQLException e) {
//            LOG.info(MessageFormat.format("STACKTRACE: {0}\n{1} {2}", e.getSQLState(), e.getErrorCode(), e.getMessage()));
//            e.printStackTrace();
//            throw new AdaptError(Config.CODES.c941.id, "Error connection to DB try later");
//        } finally {
//            Config.closeStmt(stmt);
//        }
//        LOG.info("Getting CourseList successful");
////        }
//        return infoList;
//    }
//
//
//}
