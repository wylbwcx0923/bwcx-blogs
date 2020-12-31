package com.bwcx;

import com.alibaba.fastjson.JSON;
import com.bwcx.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class ReadUserSoftware {

    public static void main(String[] args) throws SQLException {
        final Connection connection = JDBCUtils.getConnection();
        String sql = "SELECT id,user_id,cluster_id,purpose,ORGANIZATION,CONTACTS,APPLY_RESOURCE FROM ac_rs_apply";
        assert connection != null;
        final ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
        while (resultSet.next()) {
            final long id = resultSet.getLong(1);
            final long userId = resultSet.getLong(2);
            final long clusterId = resultSet.getLong(3);
            final String purpose = resultSet.getString(4);
            final String organization = resultSet.getString(5);
            final String contacts = resultSet.getString(6);
            final String applyResource = resultSet.getString(7);


            if (null!=applyResource&&applyResource.length()>0){
                final String software = JSON.parseObject(applyResource).getString("SOFTWARE_REQUIRE").replaceAll("\n","");

                if (software.length()>0){
                    if (!organization.toLowerCase(Locale.ROOT).contains("sugon")&&!organization.contains("曙光")){
                        System.out.printf("%s$%s$%s$%s$%s$%s$%s\n", id, userId, contacts, organization, clusterId, purpose.replaceAll("\n",""), software);
                    }
                }
            }

        }
        resultSet.close();
        connection.close();

    }
}
