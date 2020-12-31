package dao;

import utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TickDao {


    /**
     * 添加票
     *
     * @param addr
     * @param count
     * @return
     */
    public boolean addTick(String addr, int count) {
        Connection conn = JdbcUtil.getConn();
        String sql = "INSERT INTO tick(name,count) VALUES(?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "北京=>洛阳");
            statement.setInt(2, 100000);
            int rows = statement.executeUpdate();
            JdbcUtil.closeConn(conn, null, statement);
            return rows > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * 买票
     *
     * @param id
     * @param num
     * @return
     */
    public boolean sellTick(int id, int num, Integer version) {
        Connection conn = JdbcUtil.getConn();
        String sql = "UPDATE tick SET `count`=`count`-?,version=version+1 WHERE id=?";
        if (version != null) {
            sql += " AND version=?";
        }
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, num);
            statement.setInt(2, id);
            if (version != null) {
                statement.setInt(3, version);
            }
            int rows = statement.executeUpdate();
            JdbcUtil.closeConn(conn, null, statement);
            return rows > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * 通过id查询还剩多少票没有卖掉
     *
     * @param id
     * @return
     */
    public Map<String, Integer> getRemainTickById(int id) {
        String sql = "SELECT `count`,version FROM tick WHERE id=?";
        Connection conn = JdbcUtil.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Map<String, Integer> map = new HashMap<>();
            while (resultSet.next()) {
                map.put("total", resultSet.getInt("count"));
                map.put("version", resultSet.getInt("version"));
            }
            JdbcUtil.closeConn(conn, null, statement);
            return map;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


}
