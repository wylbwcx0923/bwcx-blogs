package utils;

import java.sql.*;

public class JdbcUtil {

    private JdbcUtil() {
    }

    public static final String CLASS_NAME = "com.mysql.jdbc.Driver";

    public static final String URL = "jdbc:mysql://10.0.35.159:3309/study?autoReconnect=true";

    public static final String USER_NAME = "root";

    public static final String PASS_WORD = "111111";

    private static Connection connection;

    //注册驱动
    static {
        try {
            Class.forName(CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //创建连接
    public static Connection getConn() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
                return connection;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 关闭连接,释放资源
     *
     * @param conn
     * @param resultSet
     * @param statement
     */
    public static void closeConn(Connection conn, ResultSet resultSet, Statement statement) {
//        try {
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            if (statement != null) {
//                statement.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
