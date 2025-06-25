package apresentacao;

import java.sql.SQLException;

import negocio.Cliente;
import negocio.Pedido;
import persistencia.ClienteDAO;
import persistencia.PedidoDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        // ClienteDAO clienteDAO = new ClienteDAO();
        // Cliente betito = new Cliente();
        // betito.setNome("Betito");
        // betito.setEmail("betito@riogrande.ifrs.edu.br");
        // clienteDAO.inserir(betito);
        // System.out.println(betito.getId());
        // clienteDAO.deletar("igor.pereira@riogrande.ifrs.edu.br");
        // new PedidoDAO().inserir(new Pedido(new ClienteDAO().obter(1))); 
        
        new PedidoDAO().listar(1).forEach(p -> System.out.println(p));
    }
}