package com.omega.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.Statement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class JdbcUtilsByDruid
 *
 * @author KennySo
 * @date 2024/3/26
 */
public class JdbcUtilsByDruid {

    private static final DataSource ds;

    static {
        Properties properties = new Properties();
        try {
            properties.load(JdbcUtilsByDruid.class.getClassLoader().getResourceAsStream("druid-config.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        // 由动态绑定可知, 此处调用的close()是连接池对象的close()
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
