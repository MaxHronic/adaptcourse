package sructures;

/**
 * Created by Max on 04.03.2017.
 */
public class Queries {
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
                + login + "', '" + pas + "', '" + name + "', '" + surname + "', '0')";
    }

    //==========================================================================================
    public static String findCourses(String request) {
        String req = "select c.courseId, c.speciality, c.name, c.about, u.name, u.surname, c.course_mark " +
                "from mydb.course c, mydb.user u " +
                "where c.personId=u.personId and c.name like '%" + request + "%' ";
        return req;
    }

    public static String getMyCourses(long personId) {
        String req = "select * from mydb.course where personId='" + personId + "' ";
        return req;
    }

    public static String getCourse(long courseId) {
        String req = "select c.courseId, c.speciality, c.name, c.about, u.name, " +
                "u.surname, c.course_mark from mydb.course c, mydb.user u  where " +
                "c.personId=u.personId and c.courseId='" + courseId + "' LIMIT 0, 2\n ";
        return req;
    }


    //==========================================================================================
    public static String Subscribe(long personId, long courseId) {
        return "INSERT INTO mydb.personalprogress " +
                "(personId, courseId, topicId, mark, progress, complete_count, pageId)" +
                "VALUES ('" + personId + "','" + courseId + "','1','0','0','0','0')";
    }

    public static String Subscribe(long personId, long courseId, long topicId) {
        return "INSERT INTO mydb.personalprogress " +
                "(personId, courseid, topicId, mark, progress, complete_count)" +
                "VALUES ('" + personId + "','" + courseId + "','" + topicId + "','0','0','0')";
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

    public static String getTopicPages(long topicId) {
        return "select * from mydb.pages where topicId = '" + topicId + "'";
    }

    //==========================================================================================

    public static String setCourse() {
        return "";
    }

    public static String editCourse(long courseId, String name, String about, long author, String spec) {
        return "UPDATE mydb.course SET name='" + name + "', about='" + about + "', personId='" + author +
                "', speciality='" + spec + "' WHERE courseId='" + courseId + "'";
    }

    public static String getCourseProgress(long courseId) {
        return "select name, counter, topic_mark from mydb.topic where courseId='" + courseId + "'";
    }

    public static String getMyProgress(long personId) {
        return "select c.name, t.name,  p.complete_count, p.mark " +
                "from mydb.personalprogress p, mydb.course c, mydb.topic t  where p.personId ='" + personId + "' " +
                "and p.courseId = c.courseId and p.topicId=t.topicId " +
                "order by p.courseId";
    }

    public static String setCourseProgress() {
        return "";
    }

    public static String setMyProgress() {
        return "";
    }

    //==========================================================================================

    public static String getTopic(long courseId, int in_courseId) {
        return "select * from mydb.topic where courseId = '" + courseId +
                "' and in_courseId = '" + in_courseId + "'";
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

    public static String getTopicQuestions(long topicId) {
        return "select * from mydb.question where topicId='" + topicId + "' ORDER BY RAND() LIMIT 30'";
    }


    //==========================================================================================

    public static String getTeachRate() {
        return "select personId, name, surname, status, rate from mydb.user " +
                "where status = 1 order by rate desc";
    }

}
