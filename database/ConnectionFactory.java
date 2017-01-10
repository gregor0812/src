///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package database;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.apache.commons.dbcp.DriverManagerConnectionFactory;
//import org.apache.commons.dbcp.PoolableConnection;
//import org.apache.commons.dbcp.PoolableConnectionFactory;
//import org.apache.commons.dbcp.PoolingDataSource;
//import org.apache.commons.pool.impl.GenericObjectPool;
//
///**
// *
// * @author Gregor
// */
//public class ConnectionFactory {
// private static interface Singleton {
//  final ConnectionFactory INSTANCE = new ConnectionFactory();
// }
//
// private final DataSource dataSource;
//
// private ConnectionFactory() {
//  Properties properties = new Properties();
//  properties.setProperty("user", "root");
//  properties.setProperty("password", "tiger");
//
//        GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
//        DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
//    "jdbc:mysql://127.0.0.1/logging", properties);
//  new PoolableConnectionFactory(connectionFactory, pool, null,
//    "SELECT 1",3, false, false,
//    Connection.TRANSACTION_READ_COMMITTED
//  );
//
//  this.dataSource = new PoolingDataSource(pool);
// }
//
// public static Connection getDatabaseConnection() throws SQLException {
//  return Singleton.INSTANCE.dataSource.getConnection();
// }
//}