package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Fisioterapeuta;

// DAO: data acess object 
public class FisioterapeutaDAO {

    public boolean adicionar(Fisioterapeuta novoFisioterapeuta) throws SQLException{
        String sql = "INSERT INTO fisioterapeuta (crefito, nome) VALUES (?, ?) RETURNING id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, novoFisioterapeuta.getCrefito());
        preparedStatement.setString(2, novoFisioterapeuta.getNome());
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            novoFisioterapeuta.setId(rs.getInt("id"));
        }
        preparedStatement.close();
        conexao.close();
        return novoFisioterapeuta.getId() != 0;
    }


    public Fisioterapeuta obter(String crefito) throws SQLException {
        Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM fisioterapeuta where crefito = ? AND ativo is TRUE;");
        preparedStatement.setString(1, crefito);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            fisioterapeuta.setId(rs.getInt("id"));
            fisioterapeuta.setNome(rs.getString("nome"));
            fisioterapeuta.setCrefito(rs.getString("crefito"));
            fisioterapeuta.setAtivo(rs.getBoolean("ativo"));
        }
        preparedStatement.close();
        conexao.close();
        return fisioterapeuta;
    }

    public ArrayList<Fisioterapeuta> listarAtivos() throws SQLException {
        ArrayList<Fisioterapeuta> vetFisioterapeuta = new ArrayList<>();
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM fisioterapeuta WHERE ativo is TRUE;");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Fisioterapeuta fisioterapeuta = new Fisioterapeuta();
            fisioterapeuta.setId(rs.getInt("id"));
            fisioterapeuta.setNome(rs.getString("nome"));
            fisioterapeuta.setCrefito(rs.getString("crefito"));
            fisioterapeuta.setAtivo(rs.getBoolean("ativo"));
            vetFisioterapeuta.add(fisioterapeuta);
        }
        preparedStatement.close();
        conexao.close();
        return vetFisioterapeuta;
    }

    public boolean deletar(String crefito) throws SQLException {
        String sql = "UPDATE fisioterapeuta SET ativo = FALSE where crefito = ?";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, crefito);
        int linhasAfetadas = preparedStatement.executeUpdate();
        preparedStatement.close();
        conexao.close();
        return linhasAfetadas == 1;
    } 

    // poderia acrescentar o atributo ativo aqui tb!
    public boolean atualizar(Fisioterapeuta fisioterapeuta) throws SQLException{
        String sql = "UPDATE fisioterapeuta SET nome = ?, crefito = ?, ativo = ? where id = ?;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, fisioterapeuta.getNome());
        preparedStatement.setString(2, fisioterapeuta.getCrefito());
        preparedStatement.setBoolean(3, fisioterapeuta.isAtivo());
        preparedStatement.setInt(4, fisioterapeuta.getId());
        int linhasAfetadas = preparedStatement.executeUpdate();
        preparedStatement.close();
        conexao.close();
        return linhasAfetadas == 1;
    }
    
}
