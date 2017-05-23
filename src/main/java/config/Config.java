package config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by JR on 25.04.2016.
 */
public class Config {
    //    private static final String oraclePrefix = "jdbc:oracle:thin:@";
    private static String connectionUrl;
    private static String userLogin;
    private static String userPassword;
    private static String hostIp;
    private static int[] hostIpArrayForm;
    private static int hostPort;
//    private static Timer configUpdater = new Timer(1000 * ConfigFile.monitorInterval, new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            refreshConfigs();
//        }
//    });

//    static {
//        refreshConfigs();
//        configUpdater.start();
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("exception in Config.java ------------------------------");
//            e.printStackTrace();
//        }
//    }

//    private static void refreshConfigs() {
//        ConfigFile configFile = new ConfigFile();
//        connectionUrl = configFile.getConnectionUrl();
//        userLogin = configFile.getUserLogin();
//        userPassword = configFile.getUserPassword();
//        hostIp = configFile.getHostIp();
//        hostIpArrayForm = parseHostIp(configFile.getHostIp());
//        hostPort = Integer.parseInt(configFile.getHostPort());
//        appLocaleSet(configFile.getLocale());
//    }

//    private static int[] parseHostIp(String stringIp) {
//        String[] parts = stringIp.split("[.]+");
//        if (parts.length != 4) return new int[]{0, 0, 0, 0};
//        int[] ip = new int[4];
//        for (int i = 0; i < 4; i++)
//            ip[i] = Integer.parseInt(parts[i]);
//        return ip;
//    }

//    private static void appLocaleSet(String locale) {
//        locale = locale.toLowerCase();
//        switch (locale) {
//            case "english":
//                Locale.setDefault(Locale.ENGLISH);
//                break;
//            default:
//                Locale.setDefault(Locale.getDefault());
//        }
//    }

    /**
     * Connection string for DB:  jdbc:oracle:thin:(login)/(password)@(host):(port):XE
     * <p/>
     * If connecting via (url, login, pass) method:  ("jdbc:oracle:thin@(host):(port):XE", login, pass)
     *
     * @return connection to preconfigured db
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     */
    public static Connection connectToDB() throws SQLException, NamingException {

        InitialContext initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/my_db");
        Connection conn = ds.getConnection();
        return conn;

//        return DriverManager.getConnection(oraclePrefix + connectionUrl, userLogin, userPassword);
    }

    public static void closeStmt(Statement stmt) {
        try {
//            if (connection != null) connection.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param connection - коннект к базе данных
     *                   Освобождает коннект к базе данных
     */
    public static void closeCon(Connection connection) {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static String getHostIp() {
//        return hostIp;
//    }
//
//    public static int[] getHostIpArrayForm() {
//        return hostIpArrayForm;
//    }
//
//    public static int getHostPort() {
//        return hostPort;
//    }

    /**
     * Коды ошибок
     */
    public enum CODES {
        c1(1, "Authentication fail"),
        c7(7, ""),
        c909(0x38d, "DB access error"),
        c923(0x39b, ""),
        c941(0x3ad, "Operator temporary unavailable"),
        c2001(0x7d1, "Authentication fail"),
        c2002(0x7d2, "Action denied"),
        c4020(0xfb4, ""),
        c4060(0xfdc, ""),
        c5002(0x138a, ""),
        c5011(0x1393, ""),
        c5099(0x13eb, ""),
        c20000LowerLimit(20000, ""),
        c29999UpperLimit(29999, "");

        public final int id;

        public final String message;

        CODES(int ID, String aMessage) {
            id = ID;
            message = aMessage;
        }
    }
}
