package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.Cliente;
import negocio.Produto;

public class ProdutoDAO {
    // inserir Produto    
    public boolean inserir (Produto produto) throws SQLException{
        String sql = "INSERT INTO produto (nome, preco) values (?,?) RETURNING id;";
        ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection connection = conexaoPostgreSQL.getConexao();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setDouble(2, produto.getPreco());
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            produto.setId(rs.getInt("id"));
        }
        preparedStatement.close();
        connection.close();
        return produto.getId() != 0;
    }

    public Produto obter(int id) throws SQLException {
        Produto produto = new Produto();
        String sql = "SELECT * FROM produto where id = ?;";
        ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection connection = conexaoPostgreSQL.getConexao();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
        }
        preparedStatement.close();
        connection.close();
        return produto;
    }

}
