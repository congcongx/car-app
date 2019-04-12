package com.car.controller;

import com.car.commons.config.DataSourceConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DataSourceConfigure dataSourceConfigure;

   /* @RequestMapping("/test")
    public Object test() throws SQLException {
        DataSource st = dataSourceConfigure.jh();
        Connection connection = st.getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from t_user");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            System.out.println(user);
            
        }
        System.out.println();
        return "success";
    }*/

  /*  @RequestMapping("/test1")
    public Object test1(String db) throws SQLException {
        DataSourceContextHolder.setDataSourceKey(db);
        DataSource st = dataSourceConfigure.dynamicDataSource();
        Connection connection = st.getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from t_user");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            System.out.println(user);

        }
        System.out.println();
        return "success";
    }

*/

}
