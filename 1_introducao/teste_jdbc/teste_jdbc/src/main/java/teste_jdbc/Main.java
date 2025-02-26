package teste_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String host = "localhost";
        String port = "5432";
        String username = "postgres";
        String password = "postgres";
        String dbname = "chuva";
        String url = "jdbc:postgresql://"+host+":"+port+"/"+dbname;        
        Connection conexao = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM pessoa";
        ResultSet rs = conexao.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("nome"));
        }
        conexao.close();
    }
}