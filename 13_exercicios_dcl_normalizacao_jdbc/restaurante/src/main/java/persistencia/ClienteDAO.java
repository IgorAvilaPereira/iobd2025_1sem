package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.Cliente;

public class ClienteDAO {
    
    public boolean deletar(String email) throws SQLException {
        String sql = "DELETE FROM cliente WHERE email = trim(?);";
        ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection connection = conexaoPostgreSQL.getConexao();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        int linhas = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return linhas == 1;
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?;";
        ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection connection = conexaoPostgreSQL.getConexao();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int linhas = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return linhas == 1;
    }

    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (email, nome) values (?,?) RETURNING id;";
        ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection connection = conexaoPostgreSQL.getConexao();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, cliente.getEmail());
        preparedStatement.setString(2, cliente.getNome());
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            cliente.setId(rs.getInt("id"));
        }
        preparedStatement.close();
        connection.close();

    }

}
