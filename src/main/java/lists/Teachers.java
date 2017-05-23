package lists;

import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sructures.AdaptError;
import sructures.Queries;
import sructures.User;

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
 * Created by Max on 06.05.2017.
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Teachers", propOrder = {
        "teachers"
})
public class Teachers {
    private static final Logger LOG = LogManager.getLogger(lists.Teachers.class);
    @XmlElement(name = "Teachers")
    protected static ArrayList<User> teachers;

    /**
     * @param connect - коннект к базе данных
     * @return - возвращает список досутпных продуктов
     * @throws sructures.AdaptError
     */
    public static ArrayList<User> getTeachers(Connection connect) throws AdaptError {
        LOG.info("Getting getTeachers");
        ArrayList<User> rates;
        User item;
        teachers = new ArrayList<User>();
        CallableStatement stmt = null;
        try {
            stmt = connect.prepareCall(Queries.getTeachRate());

            LOG.info("Query prepared to execute ");
            ResultSet rs = stmt.executeQuery();
            LOG.info("Query executed!");

            while (rs.next()) {
                item = new User();
                item.setPersonId(rs.getLong(1));
                item.setName(rs.getString(2));
                item.setSurname(rs.getString(3));
                item.setStatus(rs.getInt(4));
                item.setRate(rs.getInt(5));

                teachers.add(item);
            }
            rates = teachers;
        } catch (SQLException e) {
            LOG.info(MessageFormat.format("STACKTRACE: {0}\n{1} {2}", e.getSQLState(), e.getErrorCode(), e.getMessage()));
            e.printStackTrace();
            throw new AdaptError(Config.CODES.c941.id, "Error connection to DB try later");
        } finally {
            Config.closeStmt(stmt);
        }
        LOG.info("Getting CourseList successful");
//        }
        return rates;
    }


}
