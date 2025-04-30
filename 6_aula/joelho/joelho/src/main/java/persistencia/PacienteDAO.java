package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.Paciente;

public class PacienteDAO {

     public boolean atualizar(Paciente paciente) throws SQLException{
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, ativo = ? where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, paciente.getNome());
        preparedStatement.setString(2, paciente.getCpf());
        preparedStatement.setBoolean(3, paciente.isAtivo());
        preparedStatement.setInt(4, paciente.getId());
        int linhasAfetadas = preparedStatement.executeUpdate();
        preparedStatement.close();
        conexao.close();
        return linhasAfetadas == 1;
    }

    public Paciente obter(String cpf) throws SQLException {
        Paciente paciente = new Paciente();
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM paciente where cpf = ? and ativo is true;");
        preparedStatement.setString(1, cpf);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            paciente.setId(rs.getInt("id"));
            paciente.setNome(rs.getString("nome"));
            paciente.setLocalDor(rs.getString("local_dor"));
            paciente.setAtivo(rs.getBoolean("ativo"));
        }
        preparedStatement.close();
        conexao.close();
        return paciente;
    }

    public boolean deletar(String cpf) throws SQLException {
        String sql = "UPDATE paciente SET ativo = FALSE where cpf = ?";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, cpf);
        int linhasAfetadas = preparedStatement.executeUpdate();
        preparedStatement.close();
        conexao.close();
        return linhasAfetadas == 1;
    } 

}
