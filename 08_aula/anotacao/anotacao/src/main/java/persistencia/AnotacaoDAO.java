package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.json.JSONObject;

import negocio.Anotacao;

public class AnotacaoDAO {

      public boolean deletar(int id) throws SQLException{
        String sql = "DELETE FROM anotacao WHERE id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConnection();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int linhasAfetadas = preparedStatement.executeUpdate();
        preparedStatement.close();
        conexao.close();
        return linhasAfetadas == 1;
    }


     public boolean atualizar(Anotacao anotacao) throws SQLException{
        String sql = " update anotacao set data_hora = ?, conteudo = cast(? as jsonb) where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConnection();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setTimestamp(1, Timestamp.valueOf(anotacao.getDataHora()));
        preparedStatement.setString(2, anotacao.getConteudo().toString());
        preparedStatement.setInt(3, anotacao.getId());
        int linhasAfetadas = preparedStatement.executeUpdate();
        preparedStatement.close();
        conexao.close();
        return linhasAfetadas == 1;
    }

    public boolean inserir(Anotacao anotacao) throws SQLException{
        String sql = "INSERT INTO anotacao (conteudo) VALUES ('{\"titulo\": \""+anotacao.getConteudo().getString("titulo")+"\", \"texto\": \""+anotacao.getConteudo().getString("texto")+"\", \"cor\": \""+anotacao.getConteudo().getString("cor")+"\"}');";
        Connection conexao = new ConexaoPostgreSQL().getConnection();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        int linhasAfetadas = preparedStatement.executeUpdate();
        preparedStatement.close();
        conexao.close();
        return linhasAfetadas == 1;
    }

    public Anotacao obter(int id) throws SQLException{
        Anotacao anotacao = new Anotacao();
        Connection conexao = new ConexaoPostgreSQL().getConnection();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM anotacao WHERE id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            anotacao.setId(rs.getInt("id"));
            anotacao.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
            JSONObject conteudo = new JSONObject(rs.getString("conteudo"));
            anotacao.setConteudo(conteudo);
        }
        preparedStatement.close();
        conexao.close();
        return anotacao;

    }

}
