package memberAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public MemberDAO() { //db 생성자? DB커넥션을 얻어내는 로직 
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/OracleDB");
			con = ds.getConnection();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean insertMember(MemberBean mb) throws SQLException {
		
		String sql = null;
		
		try {
			sql = " insert into member values "
				+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mb.getMEMBER_ID());
			pstmt.setString(2, mb.getMEMBER_PW());
			pstmt.setString(3, mb.getMEMBER_NAME());
			pstmt.setInt(4, mb.getMEMBER_JUMIN1());
			pstmt.setInt(5, mb.getMEMBER_JUMIN2());
			pstmt.setString(6, mb.getMEMBER_EMAIL());
			pstmt.setString(7, mb.getMEMBER_EMAIL_GET());
			pstmt.setString(8, mb.getMEMBER_MOBILE());
			pstmt.setString(9, mb.getMEMBER_PHONE());
			pstmt.setString(10, mb.getMEMBER_ZIPCODE());
			pstmt.setString(11, mb.getMEMBER_ADDR1());
			pstmt.setString(12, mb.getMEMBER_ADDR2());
			pstmt.setInt(13, mb.getMEMBER_ADMIN());
			pstmt.setTimestamp(14, mb.getMEMBER_JOIN_DATE());
			
			pstmt.executeUpdate();
			
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		return false;
	}
	
	
	
	public int userCheck(String id, String pw) throws SQLException {
		
		String sql = null;
		int x = -1;
		
		try {
			
			sql = " select member_pw from member where member_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String memberpw = rs.getString("member_pw");
				
				if(memberpw.equals(pw)) {
					x = 1;
				} else {
					 x = 0;
				}
			}else {
				x = -1;
			}
			return x;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){ pstmt.close(); }
			if(rs != null){ rs.close(); }
		}
		return -1;	
	}
	
	
	
	public int confirmId(String id) throws SQLException {
		
		String sql = null;
		int x = -1;
		
		try {
			
			sql = " Select member_id From member Where member_id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = 1;
			} else {
				x = -1;
			}
			return x;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){ pstmt.close(); }
			if(rs != null){ rs.close(); }
		}
		
		return -1;
	}
	
	
	public MemberBean getMember(String id) throws SQLException {
		
		MemberBean member = null;
		String sql = null;
		
		try {
			
			sql = " Select * From member Where member_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberBean();
				
				member.setMEMBER_ID(rs.getString("member_id"));
				member.setMEMBER_NAME(rs.getString("member_name"));
				member.setMEMBER_JUMIN1(rs.getInt("member_jumin1"));
				member.setMEMBER_JUMIN2(rs.getInt("member_jumin2"));
				member.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
				member.setMEMBER_EMAIL_GET(rs.getString("MEMBER_EMAIL_GET"));
				member.setMEMBER_MOBILE(rs.getString("MEMBER_MOBILE"));
				member.setMEMBER_PHONE(rs.getString("MEMBER_PHONE"));
				member.setMEMBER_ZIPCODE(rs.getString("MEMBER_ZIPCODE"));
				member.setMEMBER_ADDR1(rs.getString("MEMBER_ADDR1"));
				member.setMEMBER_ADDR2(rs.getString("MEMBER_ADDR2"));
				
				return member;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){ pstmt.close(); }
			if(rs != null){ rs.close(); }
		}
		
		return null;
	}
	
	
	
	public void updateMember(MemberBean mb) throws SQLException {
		
		String sql = null;
		
		try {
			
			sql = " Update member Set member member_pw=?, member_name=?, "
				+ " member_email=?, member_email_get=?, member_mobile=?, "
				+ " member_phone=?, member_zipcode=?, member_addr1=?, "
				+ " member_addr2=? Where member_id=? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMEMBER_PW());
			pstmt.setString(2, mb.getMEMBER_NAME());
			pstmt.setString(3, mb.getMEMBER_EMAIL());
			pstmt.setString(4, mb.getMEMBER_EMAIL_GET());
			pstmt.setString(5, mb.getMEMBER_MOBILE());
			pstmt.setString(6, mb.getMEMBER_PHONE());
			pstmt.setString(7, mb.getMEMBER_ZIPCODE());
			pstmt.setString(8, mb.getMEMBER_ADDR1());
			pstmt.setString(9, mb.getMEMBER_ADDR2());
			pstmt.setString(10, mb.getMEMBER_ID());
			
			pstmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
	}
	
	
	
	
	public int deleteMember(String id, String pw) throws SQLException {
		
		String sql = null;
		int x = -1;
		
		try {
			
			sql = " Selete member_pw From member Where member_id = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String memberpw = rs.getString("member_pw");
				if(memberpw.equals(pw)) {
					sql = " Delete From member Where member_id = ? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					x = 1;
				}else {
					x = 0;
				}
				return x;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
		return -1;
	}
	
	
	
	public MemberBean findId(String name, String jumin1, String jumin2)
			throws SQLException {
		
		String sql = null;
		MemberBean member = new MemberBean();
		
		try {
			
			sql = " Select member_id, member_pw, member_jumin1, member_jumin2 "
				+ " From member Where member_name = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String memberjumin1 = rs.getString("member_jumin1");
				String memberjumin2 = rs.getString("member_jumin2");
				
				if(memberjumin1.equals(jumin1) && memberjumin2.equals(jumin2)) {
					member.setMEMBER_ID(rs.getString("member_id"));
					member.setMEMBER_PW(rs.getString("member_pw"));
					return member;
				}
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){ pstmt.close(); }
			if(rs != null){ rs.close(); }
		}
				return null;
		
	}
	
	
	
	
	public boolean isAdmin(String id) {
		
		String sql = " Select member_admin From member Where member_id = ? ";
		int member_admin = 0;
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			
			member_admin = rs.getInt("member_admin");
			
			if(member_admin == 1) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	
	public List searchZipcode(String searchdong) {
		
		String sql = " Select * From zipcode Where dong like ? ";
		List zipcodeList = new ArrayList();
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchdong+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String sido=rs.getString("sido");
				String gugun=rs.getString("gugun");
				String dong=rs.getString("dong");  
				String ri=rs.getString("ri"); 
				String bunji=rs.getString("bunji");
				
				if(ri == null) ri = "";
				if(bunji == null) bunji = "";
				
				String zipcode = rs.getString("zipcode");
				String addr=sido+ " "+gugun+ " "+dong+ " "+ri+ " "+bunji;
				
				zipcodeList.add(zipcode+","+addr);
				
			}
			return zipcodeList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	

}
