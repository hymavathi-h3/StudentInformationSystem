package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.student;

public class studentservices {
	private Connection conn;

	public studentservices(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean addstudent(student st) {
		boolean f = false;
		try {
			String sql = "insert into student(pinnum,name,email,branch) values(?,?,?,?)";
			PreparedStatement pmst = conn.prepareStatement(sql);
			pmst.setString(1, st.getPinnum());
			pmst.setString(2, st.getName());
			pmst.setString(3, st.getEmail());
			pmst.setString(4, st.getBranch());
			int i = pmst.executeUpdate();
			if (i > 0) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public List<student> fetchall() {
		List<student> stu = new ArrayList<student>();
		student s = null;
		try {
			String sql = "select * from student";
			PreparedStatement pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while (rs.next()) {
				s = new student();
				s.setPinnum(rs.getString(1));
				s.setName(rs.getString(2));
				s.setEmail(rs.getString(3));
				s.setBranch(rs.getString(4));
				stu.add(s);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stu;
	}

	public boolean delete(String pinnum) {
		boolean f = false;
		try {
			String sql = "delete from student where pinnum=?";
			PreparedStatement pmst = conn.prepareStatement(sql);
			pmst.setString(1, pinnum);
			int i = pmst.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public boolean update(student std) {
		boolean f = false;
		try {

			String sql = "update student set name=? email=? branch=? where pinnum=?";
			PreparedStatement pmst = conn.prepareStatement(sql);
			pmst.setString(1, std.getName());
			pmst.setString(2, std.getEmail());
			pmst.setString(2, std.getBranch());
			pmst.setString(4, std.getPinnum());
			int i = pmst.executeUpdate();
			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

}
