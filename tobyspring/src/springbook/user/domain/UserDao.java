package springbook.user.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	public void add(User user) throws ClassNotFoundException, SQLException{
		Connection c = getConnection();
		String sql = "insert into users(id,name,password) values(?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		ps.executeUpdate();
		ps.close();
		c.close();
	}
	public User get(String id) throws ClassNotFoundException, SQLException{
		Connection c = getConnection();
		String sql = "select * from users where id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		rs.close();
		ps.close();
		c.close();
		return user;
	}
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/Springbook?useUnicode=yes&characterEncoding=UTF-8", "root", "Rx78nt10");
		return c;
	}
}
