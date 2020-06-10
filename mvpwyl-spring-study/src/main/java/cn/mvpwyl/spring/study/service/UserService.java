package cn.mvpwyl.spring.study.service;

import cn.mvpwyl.spring.study.pojo.UserInfo;
import cn.mvpwyl.spring.study.util.DBhepler;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {


    /**
     * 获取用户信息
     *
     * @return
     */
    public static List<UserInfo> getAllByDb() {
        List<UserInfo> list = new ArrayList<UserInfo>();
        try {
            DBhepler db = new DBhepler();
            String sql = "select * from user_info";
            ResultSet rs = db.Search(sql, null);
            while (rs.next()) {

                int userId = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                String password = rs.getString("password");
                UserInfo userInfo = new UserInfo(userId, userName,
                        email, mobile, password);
                list.add(userInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询指定目录中电子表格中所有的数据
     *
     * @param file 文件完整路径
     * @return
     */
    public static List<UserInfo> getAllByExcel(String file) {
        List<UserInfo> list = new ArrayList<UserInfo>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            Sheet rs = rwb.getSheet("用户信息");// 或者rwb.getSheet(0)
            int clos = rs.getColumns();// 得到所有的列
            int rows = rs.getRows();// 得到所有的行
            System.out.println(clos + " rows:" + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    // 第一个是列数，第二个是行数
                    String userId = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列
                    if (userId == null || "".equals(userId)) {
                        userId = "0";
                    }
                    // 所以这里得j++
                    String userName = rs.getCell(j++, i).getContents();
                    String email = rs.getCell(j++, i).getContents();
                    String mobile = rs.getCell(j++, i).getContents();
                    String password = rs.getCell(j++, i).getContents();
                    list.add(new UserInfo(Integer.valueOf(userId), userName,
                            email, mobile, password));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过Id判断是否存在
     *
     * @param id
     * @return
     */

    public static boolean isExist(int id) {
        try {
            DBhepler db = new DBhepler();
            ResultSet rs = db.Search("select * from user_info where user_id=?",
                    new String[]{id + ""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        /*
         * List<StuEntitay> all=getAllByDb(); for (StuEntity stuEntity : all) {
         * System.out.println(stuEntity.toString()); }
         */
        System.out.println(isExist(1));
    }
}
