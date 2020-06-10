package cn.mvpwyl.spring.study.option;

import cn.mvpwyl.spring.study.pojo.UserInfo;
import cn.mvpwyl.spring.study.service.UserService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.List;

/**
 * @author wangyuliang
 * 导出为excel
 */
public class ExportExcel {

    public static void main(String[] args) {
        try {
            WritableWorkbook wwb = null;
            // 创建可写入的Excel工作簿
            String fileName = "/Users/wangyuliang/book.xls";
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);
            // 创建工作表
            WritableSheet ws = wwb.createSheet("用户信息", 0);
            //查询数据库中所有的数据
            List<UserInfo> list= UserService.getAllByDb();
            //要插入到的Excel表格的行号，默认从0开始
            Label labelId= new Label(0, 0, "编号(userId)");//表示第
            Label labelName= new Label(1, 0, "姓名(userName)");
            Label labelSex= new Label(2, 0, "email(email)");
            Label labelNum= new Label(3, 0, "电话(mobile)");
            Label labelPassword= new Label(4, 0, "密码(password)");
            ws.addCell(labelId);
            ws.addCell(labelName);
            ws.addCell(labelSex);
            ws.addCell(labelNum);
            ws.addCell(labelPassword);
            for (int i = 0; i < list.size(); i++) {
                Label labelId_i= new Label(0, i+1, list.get(i).getUserId()+"");
                Label labelName_i= new Label(1, i+1, list.get(i).getUserName());
                Label labelSex_i= new Label(2, i+1, list.get(i).getEmail());
                Label labelNum_i= new Label(3, i+1, list.get(i).getMobile()+"");
                Label labelPassword_i= new Label(4, i+1, list.get(i).getPassword()+"");
                ws.addCell(labelId_i);
                ws.addCell(labelName_i);
                ws.addCell(labelSex_i);
                ws.addCell(labelNum_i);
                ws.addCell(labelPassword_i);
            }
            //写进文档
            wwb.write();
            // 关闭Excel工作簿对象
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
