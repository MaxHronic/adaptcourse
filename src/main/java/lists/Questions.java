package lists;

import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sructures.AdaptError;
import sructures.Queries;
import sructures.Question;

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

/**
 * Created by Max on 26.02.2017.
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuestionList", propOrder = {
        "questionList"
})
public class Questions {

    private static final Logger LOG = LogManager.getLogger(Questions.class);
    @XmlElement(name = "Question", nillable = true)
    protected static ArrayList<Question> questionList;

    /**
     * @param personId -
     * @param courseId -
     * @param connect  - коннект к базе данных
     * @return - возвращает список досутпных продуктов
     * @throws sructures.AdaptError
     */
    public static ArrayList<Question> getQuestions(long personId, long courseId, long topicId, boolean total, Connection connect) throws AdaptError {
        LOG.info("Getting QuestionList");
        ArrayList<Question> questions;
        Question item;
        questions = new ArrayList<Question>();
        CallableStatement stmt = null;
        try {
//            if (!total)
            //TODO
//                stmt = connect.prepareCall(Queries.getTest);
//            else
            //TODO
//                stmt = connect.prepareCall(Queries.getFinalTest);
//            stmt.setLong(1, personId);
//            stmt.setLong(2, courseId);
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
            questionList = questions;
        } catch (SQLException e) {
            LOG.info(MessageFormat.format("STACKTRACE: {0}\n{1} {2}", e.getSQLState(), e.getErrorCode(), e.getMessage()));
            e.printStackTrace();
            throw new AdaptError(Config.CODES.c941.id, "Error connection to DB try later");
        } finally {
            Config.closeStmt(stmt);
        }
        LOG.info("Getting CourseList successful");
//        }
        return questionList;
    }

    public static boolean addQuestion(long topicId, long in_topicId, String text,
                                      String ans1, float w1,
                                      String ans2, float w2,
                                      String ans3, float w3,
                                      String ans4, float w4,
                                      int q_w, boolean ct, Connection connect) throws SQLException, AdaptError {
        boolean result = false;
        CallableStatement stmt = null;
        try {
            LOG.info("Start setQuestion");
            stmt = connect.prepareCall(
                    Queries.addQuestion(topicId, in_topicId, text,
                            ans1, w1, ans2, w2,
                            ans3, w3, ans4, w4,
                            q_w, ct));
//            stmt.setLong(1, questionId);
//            stmt.setLong(2, topicId);
//            stmt.setString(3, text);
//            stmt.setString(4, ansver1);
//            stmt.setFloat(5, weight1);
//            stmt.setString(6, ansver2);
//            stmt.setFloat(7, weight2);
//            stmt.setString(8, ansver3);
//            stmt.setFloat(9, weight3);
//            stmt.setString(10, ansver4);
//            stmt.setFloat(11, weight4);
//            stmt.setInt(12, weight_q);
//            stmt.setBoolean(13, type_control);
            LOG.info("Executing setQuestion query");
            stmt.execute();
            result = true;
        } catch (SQLException e) {
            LOG.info(MessageFormat.format("STACKTRACE: {0}\n{1} {2}", e.getSQLState(), e.getErrorCode(), e.getMessage()));
            e.printStackTrace();
            throw new AdaptError(Config.CODES.c941.id, "Error connection to DB try later");
        } finally {
            Config.closeStmt(stmt);
        }
        return result;
    }
}
