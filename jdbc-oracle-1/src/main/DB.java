package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class DB {

    private DB() {
    };

    private static Connection connect = null;

    public static Connection open() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                // 1. 드라이버 로딩
                Class.forName("oracle.jdbc.driver.OracleDriver");
                // 2. jdbc 주소
                String url = "jdbc:oracle:thin:@localhost:1521:XE";
                // 3. 연결
                connect = DriverManager.getConnection(url, "system", "dita1234");
            } catch (Exception e) {
                System.out.println("DB연결에러발생");
                e.printStackTrace();
            }
        }
        return connect;
    }

    public static void close(Connection connect, PreparedStatement pstmt) {
        try {
            pstmt.close();
            connect.close();
        } catch (Exception e) {
            // 에러 무시
        }
    }

    public static void close(Connection connect, PreparedStatement pstmt, ResultSet rs) {
        try {
            rs.close();
            pstmt.close();
            connect.close();
        } catch (Exception e) {
            // 에러 무시
        }
    }

}