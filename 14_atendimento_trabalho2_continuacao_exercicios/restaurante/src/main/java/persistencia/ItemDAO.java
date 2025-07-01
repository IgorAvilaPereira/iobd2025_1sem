package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.Item;

public class ItemDAO {

    public void inserir(Item item) throws SQLException {
        String sql = "INSERT INTO item_pedido (pedido_id, produto_id, quantidade) values (?, ?, ?) RETURNING id;";
        ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection connection = conexaoPostgreSQL.getConexao();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, item.getPedido().getId());
        preparedStatement.setInt(2, item.getProduto().getId());
        preparedStatement.setInt(3, item.getQuantidade());
        ResultSet rs = preparedStatement.executeQuery();
         if (rs.next()) {
            item.setId(rs.getInt("id"));
        }
        preparedStatement.close();
        connection.close();
    }

}
