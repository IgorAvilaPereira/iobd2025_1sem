package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Cliente;
import negocio.Item;
import negocio.Pedido;

public class PedidoDAO {

    // consulta pedidos por cliente => ok
    public ArrayList<Pedido> listar(int cliente_id) throws SQLException {
        ArrayList<Pedido> vetPedido = new ArrayList<Pedido>();
        String sql = "SELECT pedido.id as pedido_id, data_hora, nome, email, cliente_id FROM pedido inner join cliente on (cliente.id = pedido.cliente_id) WHERE cliente_id = ?";
        ConexaoPostgreSQL conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection connection = conexaoPostgreSQL.getConexao();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cliente_id);
        ResultSet rs = preparedStatement.executeQuery();
        Cliente cliente = null;
        while (rs.next()) {
            if (cliente == null) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("cliente_id"));
                cliente.setEmail(rs.getString("email"));
                cliente.setNome(rs.getString("nome"));
            }
            Pedido pedido = new Pedido(cliente);
            pedido.setId(rs.getInt("pedido_id"));
            pedido.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
            vetPedido.add(pedido);
        }
        preparedStatement.close();
        connection.close();
        return vetPedido;
    }

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

        for (Item item : pedido.getItens()) {
            new ItemDAO().inserir(item);
        }      

    }

}
