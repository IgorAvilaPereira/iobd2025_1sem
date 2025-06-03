package apresentacao;

import java.sql.SQLException;

import negocio.Funcionario;
import persistencia.FuncionarioDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        Funcionario func = new Funcionario();
        func.setNome("Func");
        func.setEmail("func@riogrande.com");
        func.setSalario(100000);
        new FuncionarioDAO().adicionar(func);
        
    }
}