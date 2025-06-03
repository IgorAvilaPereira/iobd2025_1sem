package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import negocio.Funcionario;

public class FuncionarioDAO {

    public boolean adicionar(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionario (nome, email, salario) VALUES (?, ?, ?);";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, funcionario.getNome());
        preparedStatement.setString(2, funcionario.getEmail());
        preparedStatement.setDouble(3, funcionario.getSalario());
        int linhas = preparedStatement.executeUpdate();
        preparedStatement.close();
        conexao.close();
        return linhas == 1;
    }

}
