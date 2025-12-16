package shop.DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import shop.DTO.PersistenceLogins;

/**
 * - username 으로 조회
 * - token 으로 조회
 * - 등록
 * - 수정
 * - 삭제
 * 
 */
public class PersistenceLoginsDAO extends JDBConnection {
	
	/**
	 * username 으로 조회
	 * @param username
	 * @return
	 */
	public PersistenceLogins selectByUseranme(String username) {
		String sql = " SELECT * "
				   + " FROM persistence_logins "
				   + " WHERE username = ? ";
		PersistenceLogins persistenceLogins = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, username);
			rs = psmt.executeQuery();
			if( rs.next() ) {
				persistenceLogins = new PersistenceLogins();
				persistenceLogins.setNo( rs.getInt("no") );
				persistenceLogins.setId( rs.getString("id") );
				persistenceLogins.setUsername( rs.getString("username") );
				persistenceLogins.setToken( rs.getString("token") );
				persistenceLogins.setExpiryDate( rs.getTimestamp("expiry_date") );
				persistenceLogins.setCreatedAt( rs.getTimestamp("created_at") );
				persistenceLogins.setUpdatedAt( rs.getTimestamp("updated_at") );
			}
		} catch (SQLException e) {
			System.err.println("회원 아이디로 조회 시 예외 발생");
			e.printStackTrace();
		}
		return persistenceLogins;
	}
	
	
	/**
	 * token 으로 조회
	 * @param token
	 * @return
	 */
	public PersistenceLogins selectByToken(String token) {
		String sql = " SELECT * "
				   + " FROM persistence_logins "
				   + " WHERE token = ? ";
		PersistenceLogins persistenceLogins = null;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, token);
			rs = psmt.executeQuery();
			if( rs.next() ) {
				persistenceLogins = new PersistenceLogins();
				persistenceLogins.setNo( rs.getInt("no") );
				persistenceLogins.setId( rs.getString("id") );
				persistenceLogins.setUsername( rs.getString("username") );
				persistenceLogins.setToken( rs.getString("token") );
				persistenceLogins.setExpiryDate( rs.getTimestamp("expiry_date") );
				persistenceLogins.setCreatedAt( rs.getTimestamp("created_at") );
				persistenceLogins.setUpdatedAt( rs.getTimestamp("updated_at") );
			}
		} catch (SQLException e) {
			System.err.println("토큰으로 조회 시 예외 발생");
			e.printStackTrace();
		}
		return persistenceLogins;
	}
	
	
	public int insert(PersistenceLogins persistenceLogins) {
		String sql = " INSERT INTO persistence_logins ( id, username, token, expiry_date ) "
				   + " VALUES ( ?, ?, ?, ? ) ";
		
		int result = 0;
		try {
			// AUTO_INCREMENT 되는 no 컬럼의 자동생성된 값을 가져온다.
			psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, persistenceLogins.getId());
			psmt.setString(2, persistenceLogins.getUsername());
			psmt.setString(3, persistenceLogins.getToken());
			Date expiryDate = persistenceLogins.getExpiryDate();
			psmt.setTimestamp(4, new java.sql.Timestamp( expiryDate.getTime() ));
			
			result = psmt.executeUpdate();
			rs = psmt.getGeneratedKeys();
			if( rs.next() ) {
				int no = rs.getInt(1);
				persistenceLogins.setNo(no);
			}
			
		} catch (Exception e) {
			System.err.println("자동 로그인 데이터 등록 시, 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int update(PersistenceLogins persistenceLogins) {
		String sql = " UPDATE persistence_logins "
				   + " SET token = ? "
				   + "    ,username = ? "
				   + "    ,expiry_date = ?"
				   + "    ,updated_at = ? "
				   + " WHERE no = ? ";
		int result = 0;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, persistenceLogins.getToken());
			psmt.setString(2, persistenceLogins.getUsername());
			Date expiryDate = persistenceLogins.getExpiryDate();
			psmt.setTimestamp(3, new Timestamp(expiryDate.getTime()) );
			psmt.setTimestamp(4, new Timestamp( new Date().getTime() ) );
			psmt.setInt(5, persistenceLogins.getNo());
			
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("자동 로그인 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int delete(String username) {
		String sql = " DELETE FROM persistence_logins "
				   + " WHERE username = ? ";
		
		int result = 0;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, username);
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.err.println("자동 로그인 삭제 중 예외 발생");
		}
		return result;
	}

}