package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.MemberDto;
import com.util.Dbman;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao itc = new MemberDao();
	public static MemberDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MemberDto getMember(String userid) {
		MemberDto mdto = null;
		String sql = "select * from member where userid=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mdto = new MemberDto();
				mdto.setEmail(rs.getString("email"));
				mdto.setImg(rs.getString("img"));
				mdto.setIndate(rs.getDate("indate"));
				mdto.setIntroduce(rs.getString("introduce"));
				mdto.setName(rs.getString("name"));
				mdto.setPassword(rs.getString("password"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setUseyn(rs.getString("useyn"));
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return mdto;
	}
	
	public ArrayList<MemberDto> getAllMembers() {
		ArrayList<MemberDto> list = null;
		String sql = "select * from member";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<MemberDto>();
					count++;
				}
				MemberDto mdto = new MemberDto();
				mdto.setEmail(rs.getString("email"));
				mdto.setImg(rs.getString("img"));
				mdto.setIndate(rs.getDate("indate"));
				mdto.setIntroduce(rs.getString("introduce"));
				mdto.setName(rs.getString("name"));
				mdto.setPassword(rs.getString("password"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setUseyn(rs.getString("useyn"));
				list.add(mdto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public ArrayList<MemberDto> getMembers(String searchWord) {
		ArrayList<MemberDto> list = null;
		String sql = "select * from member where userid like ? or name like ? or email like ? or phone like ?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchWord + "%");
			pstmt.setString(2, "%" + searchWord + "%");
			pstmt.setString(3, "%" + searchWord + "%");
			pstmt.setString(4, "%" + searchWord + "%");
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<MemberDto>();
					count++;
				}
				MemberDto mdto = new MemberDto();
				mdto.setEmail(rs.getString("email"));
				mdto.setImg(rs.getString("img"));
				mdto.setIndate(rs.getDate("indate"));
				mdto.setIntroduce(rs.getString("introduce"));
				mdto.setName(rs.getString("name"));
				mdto.setPassword(rs.getString("password"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setUseyn(rs.getString("useyn"));
				list.add(mdto);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public int insertMember(MemberDto mdto) {
		String sql = "insert into member (userid, password, name, phone, email) values(?,?,?,?,?);";
		con = Dbman.getConnection();
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(mdto.getUserid());
			pstmt.setString(mdto.getPassword());
			pstmt.setString(mdto.getName());
			pstmt.setString(mdto.getPhone());
			pdtmt.setString(mdto.getEmail());
			result = pstmt.executeQuery();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}

		return result;
	}
}
