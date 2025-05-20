package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import negocio.Sessao;

public class SessaoDAO {

    public void adicionar(Sessao novaSessao) throws SQLException {
        if (novaSessao.getFisioterapeuta().getId() != 0 && novaSessao.getPaciente().getId() != 0) {
            Connection conexao = new ConexaoPostgreSQL().getConexao();
            String sql = "INSERT INTO sessao (fisioterapeuta_id, paciente_id, data_hora) VALUES (?,?,?);";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, novaSessao.getFisioterapeuta().getId());
            preparedStatement.setInt(2, novaSessao.getPaciente().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(novaSessao.getDataHora()));
            preparedStatement.execute();
            preparedStatement.close();
            conexao.close();
        } else if (novaSessao.getFisioterapeuta().getId() == 0) {
            throw new IllegalArgumentException("Fisioterapeuta inexistente ou inativo");
        } else if (novaSessao.getPaciente().getId() == 0) {
            throw new IllegalArgumentException("Paciente inexistente ou inativo");
        }
    }

}
