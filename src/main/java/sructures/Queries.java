package sructures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Max on 04.03.2017.
 */
public class Queries {
    private static final Logger LOG = LogManager.getLogger(Queries.class);
    //==========================================================================================
    public static String authorize(String login, String pas) {
        return "select personId, status from mydb.user where user.login='" + login + "' and password='" + pas + "'";
    }

    public static String registrate(String login, String pas, Boolean status, String name, String surname) {
        int stat = 0;
        if (status) {
            stat = 1;
        }
        return "INSERT INTO mydb.user (login, password, name, surname, status, rate) VALUES ('"
                + login + "', '" + pas + "', '" + name + "', '" + surname + "', '" + status + "')";
    }

    //==========================================================================================
    public static String findCourses(String request) {
        String req = "select c.courseId, c.speciality, c.name, c.about, u.name, u.surname, c.course_mark " +
                "from mydb.course c, mydb.user u " +
                "where c.personId=u.personId and c.name like '%" + request + "%' ";
        LOG.info(req);
        return req;
    }

    public static String findMyCourses(long personId) {
        String req = "select * from mydb.course where personId='" + personId + "' ";
        LOG.info(req);
        return req;
    }

    public static String getCourse(long courseId) {
        String req = "select c.courseId, c.speciality, c.name, c.about, u.name, " +
                "u.surname, c.course_mark from mydb.course c, mydb.user u  where " +
                "c.personId=u.personId and c.courseId='" + courseId + "' LIMIT 0, 2\n ";
        LOG.info(req);
        return req;
    }

    public static String createCourse(String name, String about, long author, String speciality) {
        String req = "INSERT INTO mydb.course (name, about, personId, course_mark, speciality) " +
                "VALUES ('" + name + "', '" + about + "', '" + author + "', '0', '" + speciality + "');";
        LOG.info(req);
        return req;
    }

    public static String editCourse(long courseId, String name, String about, long author, String spec) {
        String req = "UPDATE mydb.course SET name='" + name + "', about='" + about + "', personId='" + author +
                "', speciality='" + spec + "' WHERE courseId='" + courseId + "'";
        LOG.info(req);
        return req;
    }

    public static String getCourseRate(long courseId) {
        return "select name, counter, topic_mark from mydb.topic where courseId='" + courseId + "'";
    }

    public static String getMyProgress(long personId) {
        return "select c.name, t.name,  p.complete_count, p.mark " +
                "from mydb.personalprogress p, mydb.course c, mydb.topic t  where p.personId ='" + personId + "' " +
                "and p.courseId = c.courseId and p.topicId=t.topicId " +
                "order by p.courseId";
    }

    public static String setCourseRate(long courseId, int rate) {
        return "UPDATE mydb.course SET course_mark='" + rate + "' WHERE courseId='" + courseId + "';";
    }

    public static String setMyProgress() {
        return "";
    }

    //==========================================================================================
    public static String Subscribe(long personId, long courseId) {
        String req = "INSERT INTO mydb.personalprogress " +
                "(personId, courseId, topicId, mark, progress, complete_count, pageId)" +
                "VALUES ('" + personId + "','" + courseId + "','1','0','0','0','0')";
        LOG.info(req);
        return req;
    }

    public static String Subscribe(long personId, long courseId, long topicId) {
        return "INSERT INTO mydb.personalprogress " +
                "(personId, courseid, topicId, mark, progress, complete_count)" +
                "VALUES ('" + personId + "','" + courseId + "','" + topicId + "','0','0','0')";
    }

    //==========================================================================================

    public static String getTopic(long courseId, int in_courseId) {
        String req = "select * from mydb.topic where courseId = '" + courseId + "' and in_courseId = '" + in_courseId + "'";
        LOG.info(req);
        return req;
    }

    public static String createTopic(long courseId, String name, String about, int in_courseId) {
        String req = "INSERT INTO mydb.topic (courseId, name, counter, topic_mark, about, in_courseId) " +
                "VALUES ('" + courseId + "', '" + name + "', '0', '0', '" + about + "', '" + in_courseId + "');\n";
        LOG.info(req);
        return req;
    }

    public static String editTopic(long topicId, String name, String about, int in_course) {
        String req = "UPDATE mydb.topic SET " +
                "name='" + name + "', about='" + about + "', in_courseId='" + in_course + "' " +
                "WHERE topicId='" + topicId + "';";
        LOG.info(req);
        return req;
    }

    public static String changeTopicRate(long topicId, int new_rate) {
        String req = "UPDATE mydb.topic SET " +
                "counter=counter+1, topic_mark='" + new_rate + "' " +
                "WHERE topicId='" + topicId + "';";
        LOG.info(req);
        return req;
    }

    public static String getCourseTopics(long courseId) {
        return "select topicId, in_courseId, name, about" +
                "from mydb.topic where courseId = '" + courseId + "'";
    }

    public static String getMyCourseTopics(long personId, long courseId) {
        return "select  t.topicId, t.in_courseId, t.name, t.about, p.access " +
                "from mydb.topic t, mydb.personalprogress p " +
                "where t.in_courseId=p.topicId  and t.courseId=p.courseId " +
                "and p.personId='" + personId + "' and p.courseId='" + courseId + "' order by in_courseId asc";
    }
    //==========================================================================================

    public static String getLection(long lectionId) {
        return "select * from mydb.lections where lectionId = '" + lectionId + "'";
    }

    public static String getTopicLections(long topicId) {
        return "select * from mydb.lections where topicId = '" + topicId + "'";
    }

    public static String getMyCourseTopicLections(long personId, long courseId) {
        return "select  t.topicId, t.in_courseId, t.name, t.about, p.access " +
                "from mydb.topic t, mydb.personalprogress p " +
                "where t.in_courseId=p.topicId  and t.courseId=p.courseId " +
                "and p.personId='" + personId + "' and p.courseId='" + courseId + "' order by in_courseId asc";
    }
    //==========================================================================================


    public static String AddPage(long topicId, String text, String ext_text, int in_topicId) {
        return "INSERT INTO mydb.pages (topicId, text, text_ext, in_topicId) " +
                "VALUES ('" + topicId + "', '" + text + "', '" + ext_text + "', '" + in_topicId + "')";
    }

    public static String EditPage(long topicId, long pageId, String text, String ext_text, int in_topicId) {
        return "UPDATE mydb.pages SET `text`='" + text + "', `text_ext`='" + ext_text +
                "', `in_topicId`='" + in_topicId +
                "' WHERE `pageId`='" + pageId + "' and`topicId`='" + topicId + "'";
    }


    //    TODO change to lections pages
    public static String getTopicPages(long topicId) {
        return "select * from mydb.pages where topicId = '" + topicId + "'";
    }

    public static String getLectionPages(long lecId) {
        return "select * from mydb.pages where lectionId = '" + lecId + "'";
    }

    //==========================================================================================

    public static String getTest(long courseId, long topicId, int in_topicId) {
        return "select * from mydb.question where courseId ='" + courseId
                + "' and in_topicId='" + topicId + "' and in_topicId='" + in_topicId + "'";
    }

    public static String getFinalTest() {
        return "";
    }

    public static String analize() {
        return "";
    }


    //==========================================================================================
    public static String addQuestion(long topicId, long in_topicId, String text,
                                     String ans1, float w1, String ans2, float w2,
                                     String ans3, float w3, String ans4, float w4,
                                     float q_w, boolean ct) {
        return "INSERT INTO mydb.question (" +
                "'topicId','in_topicId', 'text', 'answer1', 'weight1', 'answer2', 'weight2'," +
                " 'answer3', 'weight3', 'answer4', 'weight4', 'q_weight', 'control_type')" +
                " VALUES ('" + topicId + "','" + in_topicId + "','" + text + "','" +
                ans1 + "','" + w1 + "','" + ans2 + "','" + w2 + "','" +
                ans3 + "','" + w3 + "','" + ans4 + "','" + w4 + "','" + q_w + "','" + ct + "')";
    }

    public static String EditQuestion(long questionId, String text,
                                      String ans1, float w1, String ans2, float w2,
                                      String ans3, float w3, String ans4, float w4,
                                      float q_w, boolean ct) {
        return "UPDATE mydb.question SET text='" + text + "', answer1='" + ans1 + "', weight1='" + w1 + "'" +
                ", answer2='" + ans2 + "', weight2='" + w2 + "', answer3='" + ans3 + "', weight3='" + w3 + "', " +
                "answer4='" + ans4 + "', weight4='" + w4 + "', control_type='" + ct + "' " +
                "WHERE questionId='" + questionId + "';";
    }

    public static String getPageQuestions(long topicId, long in_topicId) {
        return "select * from mydb.question where topicId = '" + topicId
                + "' and in_topicId = '" + in_topicId + "'";
    }

    public static String getTopicQuestions(long topicId, int number) {
        return "select * from mydb.question where topicId='" + topicId + "' ORDER BY RAND() LIMIT " + number + "'";
    }


    //==========================================================================================

    public static String getTeachRate() {
        return "select personId, name, surname, status, rate from mydb.user " +
                "where status = 1 order by rate desc";
    }

}
