package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import negocio.Fisioterapeuta;
import negocio.Sessao;

public class SessaoDAO {

    public void adicionar(Sessao sessao) throws SQLException {
        if (sessao.getFisioterapeuta().getId() != 0 && sessao.getPaciente().getId() != 0) {
            Connection conexao = new ConexaoPostgreSQL().getConexao();
            String sql = "INSERT INTO sessao (fisioterapeuta_id, paciente_id, data_hora) VALUES (?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, sessao.getFisioterapeuta().getId());
            preparedStatement.setInt(2, sessao.getPaciente().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(sessao.getDataHora()));
            preparedStatement.execute();
            preparedStatement.close();
            conexao.close();
        } else if (sessao.getFisioterapeuta().getId() == 0) {
            throw new IllegalArgumentException("Fisioterapeuta inexistente ou inativo");
        } else if (sessao.getPaciente().getId() == 0) {
            throw new IllegalArgumentException("Paciente inexistente ou inativo");
        }
    }

    public Sessao obter(int id) throws SQLException {
        Sessao sessao = new Sessao();
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT sessao.id, sessao.data_hora, paciente.cpf, fisioterapeuta.crefito FROM sessao INNER JOIN paciente on (paciente.id = sessao.paciente_id) INNER JOIN fisioterapeuta ON (fisioterapeuta.id = sessao.fisioterapeuta_id) where sessao.id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            sessao.setId(rs.getInt("id")); 
            String paciente_cpf = rs.getString("cpf");
            String fisioterapeuta_crefito = rs.getString("crefito");
            preparedStatement.close();
            conexao.close();
            sessao.setPaciente(new PacienteDAO().obter(paciente_cpf));
            sessao.setFisioterapeuta(new FisioterapeutaDAO().obter(fisioterapeuta_crefito));        
            return sessao;
        }
        return null;
   }
}
