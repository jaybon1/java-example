package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqlTest {

    // 테스트용 DB 연결정보
    private static Connection connect = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    // DB 연결
    public static Connection open() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                String url = "jdbc:oracle:thin:@localhost:1521:XE";
                connect = DriverManager.getConnection(url, "system", "dita1234");
            } catch (SQLException e) {
                System.out.println("DB연결에러발생");
                e.printStackTrace();
            }
        }
        // AutoCommit을 false로 설정
        connect.setAutoCommit(false);
        return connect;
    }

    // DB 연결 종료
    public static void close() {
        try {
            // 테스트 종료후 롤백
            connect.rollback();
            // 객체 닫기
            rs.close();
            pstmt.close();
            connect.close();
        } catch (Exception e) {
        }
    }

    @Before
    public void doBefore() throws SQLException {
        open();
    }

    @After
    public void doAfter() {
        close();
    }

    @Test
    public void insertTest() throws SQLException {

        // given
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO HR.REGIONS");
        sb.append(" VALUES (5, 'Moon')");

        // when
        pstmt = connect.prepareStatement(sb.toString());
        int result = pstmt.executeUpdate();

        // then
        assertEquals(result, 1);
    }

}
