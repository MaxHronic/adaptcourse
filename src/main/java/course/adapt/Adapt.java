package course.adapt;

import config.Config;
import functions.PersonalProgress;
import functions.UserInfo;
import lists.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import sructures.*;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;


/**
 * Created by JR on 21.04.2016.
 */


@WebService(endpointInterface = "course.adapt.IAdapt")
public class Adapt implements IAdapt {
    private static final Logger LOG = LogManager.getLogger(Adapt.class);
    private static long Id;

    static {
        try {
            Class.forName("config.Config");
            ThreadContext.put("Initialize", String.valueOf(Id));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        LOG.info("AdaptCourse initialized!");
        Id = 0;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    //  ----------------------------------------------------------------------------------
    @Resource
    private WebServiceContext wsContext;

    /**
     * Method takes value of Calendar and returns value of XMLGregorianCalendar
     * This method need to get correct visualisation of Calendar and for work with xsd:dateTime
     */
    public static XMLGregorianCalendar dateTime(Calendar calendar) throws DatatypeConfigurationException {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(calendar.getTime());
        XMLGregorianCalendar dateF = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        dateF.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        dateF.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
        return dateF;
    }

    //-----------------------------------------------------------------------------------------------

    private static void rethrow(int errCode, String message) throws IOException {
        throw new IOException("AdaptCourse Error. Code " + Integer.toString(errCode) + ":  " + message + "; ");
    }

    /**
     * @return - возвращает Ip-адрес пользователя
     */
    private String getIp() {
        Map htmlHeaders = (Map) wsContext.getMessageContext().get(MessageContext.HTTP_REQUEST_HEADERS);
        Object ip_ob = htmlHeaders.get("X-Forwarded-For");
        String ip;
        if (ip_ob == null) {
            LOG.info("Client get forwarded IP error");
            HttpServletRequest request = (HttpServletRequest) wsContext.getMessageContext().get(MessageContext.SERVLET_REQUEST);
            ip = request.getRemoteAddr();
        } else {
            ip = ip_ob.toString();
            ip = ip.replace("[", "");
            ip = ip.replace("]", "");
        }

        return ip;
    }

    protected void counter() {
        Id++;
//        UUID Id = UUID.randomUUID();
        ThreadContext.put("UUID", String.valueOf(Id));
    }

    //TODO completed
    public Authorization Authorize(String login, String password) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START Authorize");
        Authorization result = new Authorization();
        String Ip = getIp();
        LOG.info("Income info: ip = " + Ip + "; login = " + login);
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            result = UserInfo.authorize(login, password, connect);
        } catch (AdaptError error) {
            int code = error.getErrorCode();
            String message = error.getMessage();
            LOG.info(" Authorization error: code = " + code + ": message = " + message);
            rethrow(code, message);
        } catch (Exception exception) {
            int code = Config.CODES.c1.id;
            String message = exception.getMessage();
            LOG.info(" Authorization error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("Authorization: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return result;
    }

    //TODO completed
    public long Register(String login, String password, boolean status, String name, String surname) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START Registration");
        long personId = 0;
        String Ip = getIp();
        LOG.info("Income info: ip = " + Ip + "; login = " + login);
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            personId = UserInfo.registrate(login, password, status, name, surname, connect);
        } catch (AdaptError error) {
            int code = error.getErrorCode();
            String message;
            if (error.getErrorCode() != 1) {
                message = error.getMessage();
            } else {
                message = "unable to connect DB";
            }
            LOG.info(" Registration error: code = " + code + ": message = " + message);
            rethrow(code, message);
        } catch (Exception exception) {
            int code = Config.CODES.c1.id;
            String message = "unable to connect DB";
            LOG.info(" Registration exception error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("Registration: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return personId;
    }

    //===========================================================================================

    //TODO completed
    public CourseList FindCourses(long personId, String request) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START FindCourses");
        String Ip = getIp();
        CourseList list = new CourseList();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("FindCourses: user = " + Long.toString(personId) + "; Ip=" + Ip + "; request = '" + request + "'");
            ArrayList<Course> courses = CourseList.getCourses(request, connect);
        } catch (AdaptError error) {
            int code = error.getErrorCode();
            String message = error.getMessage();
            LOG.info("FindCourses error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("FindCourses error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("FindCourses: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return list;
    }

    //TODO make CreateCourse
//    public long CreateCourse(long personId, long courseId) throws IOException {
//        return 0;
//    }

    //TODO make EditCourse
    public long EditCourse(long personId, long courseId) throws IOException {
        return 0;
    }

    //TODO completed
    public Course GetCourse(long personId, long courseId) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START GetCourse");
        String Ip = getIp();
        Course course = new Course();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("GetCourse: user = " + personId + "; ip = " + Ip + "; course =" + courseId);
            course = Course.getCourse(courseId, connect);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("GetCourse error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("GetCourse: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return course;
    }

    //TODO completed
    public CourseList GetMyCourses(long personId) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START getMyCourses");
        String Ip = getIp();
        CourseList list = new CourseList();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("getMyCourses: user = " + Long.toString(personId) + "; Ip=" + Ip + ";");
            ArrayList<Course> courses = CourseList.getMyCourses(personId, connect);
        } catch (AdaptError error) {
            int code = error.getErrorCode();
            String message = error.getMessage();
            LOG.info("FindCourses error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("FindCourses error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("FindCourses: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return list;
    }

    public String SubscribeCourse(long personId, long courseId, long topicId) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START SubscribeCourse");
        String Ip = getIp();
        CallableStatement stmt = null;
        String ans = "No posibility to subscribe";
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("GetCourse: user = " + personId + "; ip = " + Ip + "; course =" + courseId);
            if (topicId == 0) {
                stmt = connect.prepareCall(Queries.Subscribe(personId, courseId));
            } else {
                stmt = connect.prepareCall(Queries.Subscribe(personId, courseId, topicId));
            }
            stmt.execute();
            ans = "Successfully subscribed";
//            ResultSet rs = stmt.executeQuery();
//            firstTopicId = rs.getLong(1);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = "Impossible to subscribe";
            LOG.info("GetCourse error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("GetCourse: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return ans;
    }

    //===========================================================================================

    public TopicList GetCourseTopics(long personId, long courseId, boolean search) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START getCourseTopics");
        String Ip = getIp();
        TopicList list = new TopicList();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("getCourseTopics: user = " + Long.toString(personId) + "; Ip=" + Ip + ";");
            if (search == true) {
                ArrayList<Topic> courses = TopicList.getTopics(courseId, connect);
            } else {
                ArrayList<Topic> courses = TopicList.getMyTopics(personId, courseId, connect);
            }
        } catch (AdaptError error) {
            int code = error.getErrorCode();
            String message = error.getMessage();
            LOG.info("FindCourses error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("getCourseTopics error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("getCourseTopics: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return list;
    }

    //TODO change to GetTopicLectures
    public TopicPages GetTopicPages(long personId, long topicId) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START getTopicPages");
        String Ip = getIp();
        TopicPages list = new TopicPages();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("getTopicPages: user = " + Long.toString(personId) +
                    "; topic = " + topicId + "; Ip=" + Ip + ";");
            ArrayList<Page> pages = TopicPages.getTopicPages(topicId, connect);
        } catch (AdaptError error) {
            int code = error.getErrorCode();
            String message = error.getMessage();
            LOG.info("getTopicPages error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("getTopicPages error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("getTopicPages: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return list;
    }


    //TODO make and interface
    public long CreateLecture(long personId, long courseId) throws IOException {
        return 0;
    }

    //TODO make and interface
    public long EditLecture(long personId, long courseId) throws IOException {
        return 0;
    }

    //    TODO make and interface
    public long GetLecture(long personId, long courseId) throws IOException {
        return 0;
    }

    //    TODO make and interface
    public long GetLecturePages(long personId, long courseId) throws IOException {
        return 0;
    }


    //===========================================================================================


    public Teachers GetTeachRate() throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START GetTeachRate");
        String Ip = getIp();
        Teachers list = new Teachers();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("getting rates for ip=" + Ip);
            ArrayList<User> rate = Teachers.getTeachers(connect);
        } catch (AdaptError error) {
            int code = error.getErrorCode();
            String message = error.getMessage();
            LOG.info("GetTeachRate error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("GetTeachRate error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("GetTeachRate: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return list;
    }

    public void AddQuestion(long personId, long topicId, long in_topicId, String text,
                            String ans1, float w1, String ans2, float w2,
                            String ans3, float w3, String ans4, float w4,
                            float q_w, boolean ct) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START AddQuestion");
        CallableStatement stmt = null;
        String Ip = getIp();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("AddQuestion: user = " + personId + "; ip = " + Ip);
            stmt = connect.prepareCall(Queries.addQuestion(topicId, in_topicId, text,
                    ans1, w1, ans2, w2, ans3, w3, ans4, w4, q_w, ct));
            stmt.execute();
            Config.closeStmt(stmt);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("AddQuestion error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("AddQuestion: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
    }


    //TODO make GetTest
    public Questions GetTest(long personId, long courseId, long topicId, boolean status) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START GetTest");
        Questions topicTest = new Questions();
        String Ip = getIp();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("GetTest: user = " + personId + "; ip = " + Ip);
            ArrayList<Question> list = Questions.getQuestions(personId, courseId, topicId, status, connect);
        } catch (AdaptError error) {
            int code = error.getErrorCode();
            String message = error.getMessage();
            LOG.info("GetTest error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("GetTest error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("GetTest: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return topicTest;
    }

    //TODO make GetMyProgress
    public ProgressList GetMyProgress(long personId) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START GetMyProgress");
        ProgressList myProgress = new ProgressList();
        String Ip = getIp();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("GetMyProgress: user = " + personId + "; ip = " + Ip);
            ArrayList<PersonalProgress> myProg = ProgressList.getProgress(personId, connect);
        } catch (AdaptError error) {
            int code = error.getErrorCode();
            String message = error.getMessage();
            LOG.info("GetCourse error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("GetCourse error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("GetCourse: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return myProgress;
    }

    //TODO make GetCourseProgress
    public Course GetCourseProgress(long personId, long courseId) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START GetCourseProgress");
        Course cProgress = new Course();
        CallableStatement stmt = null;
        String Ip = getIp();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("GetCourseProgress: user = " + personId + "; ip = " + Ip);
            stmt = connect.prepareCall(Queries.getCourseRate(courseId));
            stmt.setLong(1, personId);
            stmt.setLong(2, personId);
            ResultSet rs = stmt.executeQuery();
            cProgress.setCourseId(rs.getLong(1));
            cProgress.setName(rs.getString(2));
            cProgress.setMark(rs.getInt(3));
            Config.closeStmt(stmt);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("GetCourseProgress error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("GetCourseProgress: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return cProgress;
    }

    //TODO make SetCourse
    public long SetCourse(long personId, String name, String about, TopicList topics) throws IOException {
        return 0;
    }

    //TODO make EditQuestion
    public void EditQuestion(long personId, long topicId, long in_topicId, String text,
                             String ans1, float w1, String ans2, float w2,
                             String ans3, float w3, String ans4, float w4,
                             float q_w, boolean ct) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START EditQuestion");
        CallableStatement stmt = null;
        String Ip = getIp();
        Connection connect = null;
        try {
            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
            LOG.info("EditQuestion: user = " + personId + "; ip = " + Ip);
            stmt = connect.prepareCall(Queries.addQuestion(topicId, in_topicId, text,
                    ans1, w1, ans2, w2, ans3, w3, ans4, w4, q_w, ct));
            stmt.execute();
            Config.closeStmt(stmt);
        } catch (Exception exception) {
            int code = Config.CODES.c909.id;
            String message = exception.getMessage();
            LOG.info("EditQuestion error: code = " + Integer.toString(code) + ": message = " + message);
            LOG.info(MessageFormat.format("{0}", exception.getStackTrace()));
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("EditQuestion: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
    }


    //TODO make GetMark
    public float GetMark(long personId, long courseId, long topicId, ArrayList<Answer> result) throws IOException {
        long startTime = System.currentTimeMillis();
        counter();
        LOG.info("START GetResult");
        float mark = 0;
        float points = 0;
        int weight = 1;
        String Ip = getIp();
        LOG.info("GetResult info: ip = " + Ip + "; personId = " + personId);
        Connection connect = null;
        try {
            for (Answer a : result) {
                points += a.getA_weight() * a.getQ_weight();
                weight += a.getQ_weight();
            }
            mark = points / weight * 100;

            LOG.info("start connect");
            connect = Config.connectToDB();
            LOG.info("CONNECTION OPENED");
        } catch (Exception exception) {
            int code = Config.CODES.c1.id;
            String message = exception.getMessage();
            LOG.info(" getMark error: code = " + Integer.toString(code) + ": message = " + message);
            rethrow(code, message);
        } finally {
            Config.closeCon(connect);
            LOG.info("CONNECTION CLOSED!");
            LOG.info("getMark: TOTAL TIME = " + (System.currentTimeMillis() - startTime) + " ms");
            ThreadContext.clearAll();
        }
        return mark;
    }


}
