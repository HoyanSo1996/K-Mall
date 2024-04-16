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

    private static ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<>();


    static {
        Properties properties = new Properties();
        try {
            properties.load(JdbcUtilsByDruid.class.getClassLoader().getResourceAsStream("druid-config.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // public static Connection getConnection() {
    //     try {
    //         return ds.getConnection();
    //     } catch (SQLException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

    /**
     * 从 ThreadLocal 中获取 connection
     * @return connection
     */
    public static Connection getConnection() {
        Connection connection = threadLocalConnection.get();
        if (connection == null) {
            try {
                // 如果不存在connection, 则从线程池中获取新的
                connection = ds.getConnection();
                connection.setAutoCommit(false);  // 设置手动提交事务
                threadLocalConnection.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    /**
     * 提交事务
     */
    public static void commit() {
        Connection connection = threadLocalConnection.get();
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                // 关闭连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        // Important !!!
        // 需要把 connection 从 threadLocalConnection 中移除掉
        // 不然会造成 threadLocalConnection 长时间持有该线程, 会影响效率和造成未知影响. (因为Tomcat底层也是用的线程池技术)
        threadLocalConnection.remove();
    }

    /**
     * 回滚事务
     */
    public static void rollback() {
        Connection connection = threadLocalConnection.get();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                // 关闭连接
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        threadLocalConnection.remove();
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
