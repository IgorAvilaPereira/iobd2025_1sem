package apresentacao;

import java.sql.SQLException;

import negocio.Cliente;
import negocio.Item;
import negocio.Pedido;
import negocio.Produto;
import persistencia.ClienteDAO;
import persistencia.PedidoDAO;
import persistencia.ProdutoDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Produto guitarra = new Produto();
        // guitarra.setNome("Stratocaster Fender");
        // guitarra.setPreco(10000);
        // boolean resultado = new ProdutoDAO().inserir(guitarra);

        Cliente cliente0107 = new ClienteDAO().obter(1);
        Pedido pedido0107 = new Pedido(cliente0107);

        Produto produto0107 = new ProdutoDAO().obter(1);
        pedido0107.getItens().add(new Item(pedido0107, produto0107, 10));

        Produto produto0107_2 = new ProdutoDAO().obter(2);
        pedido0107.getItens().add(new Item(pedido0107, produto0107_2, 10));
        
        new PedidoDAO().inserir(pedido0107);
        System.out.println(pedido0107.getId() != 0);
        // ClienteDAO clienteDAO = new ClienteDAO();
        // Cliente betito = new Cliente();
        // betito.setNome("Betito");
        // betito.setEmail("betito@riogrande.ifrs.edu.br");
        // clienteDAO.inserir(betito);
        // System.out.println(betito.getId());
        // clienteDAO.deletar("igor.pereira@riogrande.ifrs.edu.br");
        // new PedidoDAO().inserir(new Pedido(new ClienteDAO().obter(1))); 
        
        // new PedidoDAO().listar(1).forEach(p -> System.out.println(p));
    }
}