package dao;

import db.MyConnection;
import model.Data;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {
    public static List<Data> getAllFiles(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from data where email = ?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        List<Data> files = new ArrayList<>();
        while(rs.next()) {
            int id = rs.getInt(1);
            String fileName = rs.getString(2);
            String path = rs.getString(3);
            files.add(new Data(id, fileName, path));
        }
        return files;
    }

    public static int hideFiles(Data file) throws SQLException, IOException, SecurityException {
        Connection connection  = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into data(file_name, path, email, bin_data) values(?,?,?,?)");
        ps.setString(1, file.getFileName());
        ps.setString(2, file.getFilePath());
        ps.setString(3, file.getEmail());
        File f = new File(file.getFilePath());
        FileReader fr = new FileReader(f);
        ps.setCharacterStream(4, fr, f.length());
        int res = ps.executeUpdate();
        fr.close();
//        delete the file from the system;
        f.delete();
        return res;
    }

    public static void unhideFile(int id) throws SQLException, IOException, SecurityException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select path, bin_data from data where id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String path = rs.getString("path");
        Clob c = rs.getClob("bin_data");

        Reader r = c.getCharacterStream();
        FileWriter fw = new FileWriter(path);
        int i;
        while((i = r.read()) != -1) {
            fw.write((char) i);
        }
        fw.close();

        ps = connection.prepareStatement("delete from data where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Successfully Unhidden file");
    }
}
