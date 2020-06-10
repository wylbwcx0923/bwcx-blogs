package cn.mvpwyl.spring.study.option;

import cn.mvpwyl.spring.study.pojo.UserInfo;
import cn.mvpwyl.spring.study.service.UserService;
import cn.mvpwyl.spring.study.util.DBhepler;

import java.util.List;

/**
 * @author wangyuliang
 * 导入数据到mysql
 */
public class ImportMysql {

    public static void main(String[] args) {
        //得到表格中所有的数据
        List<UserInfo> listExcel= UserService.getAllByExcel("/Users/wangyuliang/book.xls");
          /*//得到数据库表中所有的数据
          List<StuEntity> listDb=StuService.getAllByDb();*/
        DBhepler db=new DBhepler();
        for (UserInfo userInfo : listExcel) {
            int id=userInfo.getUserId();
            if (id == 0|| !UserService.isExist(id)) {
                //不存在就添加
                String sql="insert into user_info (user_name,email,mobile,password,create_time,update_time) values(?,?,?,?,now(),now())";
                String[] str=new String[]{userInfo.getUserName(),userInfo.getEmail(),userInfo.getMobile(),userInfo.getPassword()};
                db.AddU(sql, str);
            }else {
                //存在就更新
                String sql="update user_info set user_name=?,email=?,mobile=?,password=?,update_time=now() where user_id=?";
                String[] str=new String[]{userInfo.getUserName(),userInfo.getEmail(),userInfo.getMobile(),userInfo.getPassword(),id+""};
                db.AddU(sql, str);
            }
        }
    }
}
