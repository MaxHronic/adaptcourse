

/**
 * Created by Max on 26.02.2017.
 */
package lists;

import config.Config;
import functions.PersonalProgress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sructures.AdaptError;
import sructures.Queries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgressList", propOrder = {
        "progress"
})
public class ProgressList {

    private static final Logger LOG = LogManager.getLogger(ProgressList.class);
    @XmlElement(name = "PersonalProgress", nillable = true)
    protected static ArrayList<PersonalProgress> progress;


    public static ArrayList<PersonalProgress> getProgress(long personId, Connection connect) throws AdaptError {
        ArrayList<PersonalProgress> progr;
        PersonalProgress item;
        progr = new ArrayList<>();
        CallableStatement stmt = null;
        try {
            stmt = connect.prepareCall(Queries.getMyProgress(personId));
            LOG.info("Executing getProgress query");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                item = new PersonalProgress();
                item.setCourseName(rs.getString(1));
                item.setTopicName(rs.getString(2));
                item.setCount(rs.getInt(3));
                item.setMark(rs.getInt(4));
                progr.add(item);
            }
            progress = progr;
//            Config.closeStmt(stmt);
        } catch (SQLException e) {
            LOG.info(" Error while trying to authorize user: code = " + e.getErrorCode() + " message: " + e.getMessage());
            throw new AdaptError(3, "Error!!!!");
        } finally {
            Config.closeStmt(stmt);
        }
        LOG.info("Getting ProgressList successful");
        return progress;
    }


}
