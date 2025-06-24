package apresentacao;

import java.sql.SQLException;

import negocio.Cliente;
import persistencia.ClienteDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.deletar("igor.pereira@riogrande.ifrs.edu.br");

        // Cliente betito = new Cliente();
        // betito.setNome("Betito");
        // betito.setEmail("betito@riogrande.ifrs.edu.br");
        // clienteDAO.inserir(betito);
        // System.out.println(betito.getId());
        
    }
}