package functions;

import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sructures.AdaptError;
import sructures.Authorization;
import sructures.Queries;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by JR on 21.04.2016.
 */

public class UserInfo {

    private static final Logger LOG = LogManager.getLogger(UserInfo.class);
    private long company;
    private long id;
    private boolean loaded;
    private String terminalName;

    /**
     * @param login    - логин
     * @param password - пароль
     * @param connect  - коннект к базе данных
     * @return - возвращает идентификатор сессии в виде текстовой строки
     * @throws sructures.AdaptError
     */
    public static Authorization authorize(String login, String password, Connection connect) throws AdaptError {
        CallableStatement stmt = null;
        Authorization result;
        try {
            stmt = connect.prepareCall(Queries.authorize(login, password));
            int logn, passwd, ip;
            if (password.length() < 6) {
                LOG.info(" Incorrect length of password = " + password.length());
                throw new AdaptError(69, "Password is too short!");
            }
            LOG.info("Executing authorization query");
            ResultSet rs = stmt.executeQuery();
            LOG.info("Executed");
            Authorization auto = new Authorization();
//            try {
            while (rs.next()) {
                auto.setPersonId(rs.getLong(1));
                LOG.info("personId = " + auto.getPersonId());
                auto.setStatus(rs.getBoolean(2));
                LOG.info("status = " + auto.getStatus());
            }
//            } catch (Exception error) {
//                throw new AdaptError(1, error.getMessage());
//            }
            Config.closeStmt(stmt);
            result = auto;
        } catch (AdaptError error) {
            throw new AdaptError(error.getErrorCode(), error.getMessage());
        } catch (SQLException e) {
            LOG.info(" Error while trying to authorize user: code = " + e.getErrorCode() + " message: " + e.getMessage());
            throw new AdaptError(Config.CODES.c2001.id, Config.CODES.c2001.message);
        }
        LOG.info(" User authorized with ID = " + result);
        return result;
    }


    public static long registrate(String login, String password, Boolean status, String name, String surname, Connection connect) throws AdaptError {
        CallableStatement stmt = null;
        long result = 0;
        try {
            if (password.length() < 6) {
                LOG.info(" Incorrect length of password = " + password.length());
                throw new AdaptError(69, "Password is too short!");
            } else {
                stmt = connect.prepareCall(Queries.registrate(login, password, status, name, surname));
                stmt.execute();
                stmt = connect.prepareCall(Queries.authorize(login, password));
                int logn, passwd, ip;

                LOG.info("Executing registration query");
                ResultSet rs = stmt.executeQuery();
                LOG.info("Executed");
                long numId = 0;
                while (rs.next()) {
                    numId = rs.getLong(1);
                    LOG.info("personId = " + numId);
                }

                Config.closeStmt(stmt);
                result = numId;
            }
        } catch (AdaptError error) {
            throw new AdaptError(error.getErrorCode(), error.getMessage());
        } catch (SQLException e) {
            LOG.info(" Error while trying to register user: code = " + e.getErrorCode() + " message: " + e.getMessage());
            throw new AdaptError(Config.CODES.c2001.id, "Error in registration! Try later.");
        }
        return result;
    }

    public static PersonalProgress getMyProgress(long personId, Connection connect) throws AdaptError {
        CallableStatement stmt = null;
        PersonalProgress myProgr = new PersonalProgress();
        try {
            stmt = connect.prepareCall(Queries.getMyProgress(personId));
            LOG.info("Executing getMyProgress query");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                myProgr.setCourseName(rs.getString(1));
                myProgr.setTopicName(rs.getString(2));
                myProgr.setCount(rs.getInt(3));
                myProgr.setMark(rs.getInt(4));
            }

            Config.closeStmt(stmt);
        } catch (SQLException e) {
            LOG.info(" Error while trying to authorize user: code = " + e.getErrorCode() + " message: " + e.getMessage());
            throw new AdaptError(3, "Неможливо отримати результат");
        }
        return myProgr;
    }

}
