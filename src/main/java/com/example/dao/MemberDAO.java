package com.example.dao;

import com.example.bean.BoardVO;
import com.example.bean.MemberVO;
import com.example.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    private final String M_INSERT = "insert into MEMBER" + " (userid, password, username, email, blogurl, photo, detail)" + " values (?, sha1(?),?,?,?,?,?)";
    private final String M_UPDATE = "update MEMBER set userid=?, password=?, username=?, email=?, blogurl=?, photo=?, detail=? where sid=?";
    private final String M_DELETE = "delete from MEMBER where sid=?";
    private final String M_SELECT = "select * from MEMBER where sid=?";
    private final String M_LIST = "select * from MEMBER order by regdate desc";


    // create data

    public int insertMember(MemberVO data) {
        int result = 0;
        conn = JDBCUtil.getConnection();
        try {
            stmt = conn.prepareStatement(M_INSERT);
            stmt.setString(1, data.getUserid());
            stmt.setString(2, data.getPassword());
            stmt.setString(3, data.getUsername());
            stmt.setString(4, data.getEmail());
            stmt.setString(5, data.getBlogurl());
            stmt.setString(6, data.getPhoto());
            stmt.setString(7, data.getDetail());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // read Data (single data)

    public MemberVO getOne(int sid) {
        MemberVO one = null;
        conn = JDBCUtil.getConnection();
        try {
            stmt = conn.prepareStatement(M_SELECT);
            stmt.setInt(1, sid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                one = new MemberVO();
                one.setSid(rs.getInt("sid"));
                one.setUserid(rs.getString("userid"));
                one.setUsername(rs.getString("username"));
                one.setEmail(rs.getString("email"));
                one.setBlogurl(rs.getString("blogurl"));
                one.setPhoto(rs.getString("photo"));
                one.setDetail(rs.getString("detail"));
                one.setRegdate(rs.getDate("regdate"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return one;
    }

    // read Data (list data)

    public ArrayList<MemberVO> getList() {
        ArrayList<MemberVO> list = null;
        conn = JDBCUtil.getConnection();
        try {
            stmt = conn.prepareStatement(M_LIST);
            rs = stmt.executeQuery();
            list = new ArrayList<MemberVO>();
            MemberVO one = new MemberVO();
            while(rs.next()) {
                one = new MemberVO();
                one.setSid(rs.getInt("sid"));
                one.setUserid(rs.getString("userid"));
                one.setUsername(rs.getString("username"));
                one.setEmail(rs.getString("email"));
                one.setBlogurl(rs.getString("blogurl"));
                one.setPhoto(rs.getString("photo"));
                one.setDetail(rs.getString("detail"));
                one.setRegdate(rs.getDate("regdate"));
                list.add(one);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // update Data
    public int updateMember(MemberVO data) {
        System.out.println("===> JDBC로 updateMember() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(M_UPDATE);

            stmt.setString(1, data.getUserid());
            stmt.setString(2, data.getPassword());
            stmt.setString(3, data.getUsername());
            stmt.setString(4, data.getEmail());
            stmt.setString(5, data.getBlogurl());
            stmt.setString(6, data.getPhoto());
            stmt.setString(7, data.getDetail());
            stmt.setInt(8, data.getSid());


            System.out.println(data.getUserid() + "-" + data.getPassword() + "-" + data.getUsername() + "-" + data.getEmail() + "-" + data.getBlogurl() + "-" + data.getPhoto() + "-" + data.getDetail() + "-" + data.getSid());
            stmt.executeUpdate();
            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // delete data

    public void deleteMember(MemberVO data) {
        System.out.println("===> JDBC로 deleteMember() 기능 처리");
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(M_DELETE);
            stmt.setInt(1, data.getSid());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPhotoFilename(int sid) {
        String filename = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(M_SELECT);
            stmt.setInt(1, sid);
            rs = stmt.executeQuery();
            if (rs.next()) {
                filename = rs.getString("photo");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("===> JDBC로 getPhotoFilename() 기능 처리");
        return filename;
    }
}
