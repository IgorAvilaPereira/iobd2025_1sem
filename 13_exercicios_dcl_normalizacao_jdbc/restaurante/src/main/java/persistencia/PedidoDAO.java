package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.Pedido;

public class PedidoDAO {

    public void inserir(Pedido pedido) throws SQLException{
        String sql = "INSERT INTO pedido (cliente_id) values (?) RETURNING id;";
        ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection connection = conexaoPostgreSQL.getConexao();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pedido.getCliente().getId());
        ResultSet rs = preparedStatement.executeQuery();
         if (rs.next()) {
            pedido.setId(rs.getInt("id"));
        }
        preparedStatement.close();
        connection.close();
    }

}
