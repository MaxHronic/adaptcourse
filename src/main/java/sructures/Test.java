package sructures;

import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 26.02.2017.
 */
public class Test {


    private static final Logger LOG = LogManager.getLogger(Test.class);
    @XmlElement(name = "Test", nillable = true)
    protected static ArrayList<Question> test;

    /**
     * @param personId -
     * @param courseId -
     * @param connect  - коннект к базе данных
     * @return - возвращает список досутпных продуктов
     * @throws AdaptError
     */
    public static List<Question> getTest(long personId, long courseId, long topicId, long in_topicId, boolean fTest, Connection connect) throws AdaptError {
        LOG.info("Getting test");
        ArrayList<Question> questions;
        Question item;
        questions = new ArrayList<Question>();
        CallableStatement stmt = null;
        try {
//            if (fTest)    stmt = connect.prepareCall(Queries.getTest(courseId, topicId,in_topicId));
//            else stmt = connect.prepareCall(Queries.getFinalTest(courseId));

            LOG.info("Query prepared to execute ");
            ResultSet rs = stmt.executeQuery();
            LOG.info("Query executed!");

            while (rs.next()) {
                item = new Question();
                item.setQuestionId(rs.getLong(1));
                item.setTopicId(rs.getLong(2));
                item.setText(rs.getString(3));
                item.setAnsver1(rs.getString(4));
                item.setWeight1(rs.getInt(5));
                item.setAnsver2(rs.getString(6));
                item.setWeight2(rs.getInt(7));
                item.setAnsver3(rs.getString(8));
                item.setWeight3(rs.getInt(9));
                item.setAnsver4(rs.getString(10));
                item.setWeight4(rs.getInt(11));
                item.setQuestionWeight(rs.getInt(12));
                item.setType(rs.getBoolean(13));
                questions.add(item);
            }
            test = questions;
        } catch (SQLException e) {
            LOG.info(MessageFormat.format("STACKTRACE: {0}\n{1} {2}", e.getSQLState(), e.getErrorCode(), e.getMessage()));
            e.printStackTrace();
            throw new AdaptError(Config.CODES.c941.id, "Error connection to DB try later");
        } finally {
            Config.closeStmt(stmt);
        }
        LOG.info("Getting CourseList successful");
//        }
        return test;
    }

}
