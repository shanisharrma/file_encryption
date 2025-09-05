package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isExists(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select 1 from users where email = ?;");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static int saveUser(User user) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into users(name, email) values(?,?);");
        ps.setString(1, user.getName());
        ps.setString(2,user.getEmail());
        return ps.executeUpdate();
    }
}
