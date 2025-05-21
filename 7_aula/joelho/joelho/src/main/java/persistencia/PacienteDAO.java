package persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import negocio.Paciente;
import negocio.Paciente;

public class PacienteDAO {

    public boolean inserir(Paciente paciente) throws SQLException, FileNotFoundException, IOException{
        String sql = "INSERT INTO paciente (nome, cpf, local_dor, nivel, foto) values (?, ?, ?, ?, ?) RETURNING id;";
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement(sql);
        preparedStatement.setString(1, paciente.getNome());
        preparedStatement.setString(2, paciente.getCpf());
        preparedStatement.setString(3, paciente.getLocalDor());
        preparedStatement.setInt(4, paciente.getNivel());
        
        // paciente.caminhoFotoParaFoto(caminhoFoto);  // chamando o método setArquivo(String dir);
        preparedStatement.setBytes(5, paciente.getFoto());    

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            paciente.setId(rs.getInt("id"));
        }
        preparedStatement.close();
        conexao.close();
        return paciente.getId() != 0;
    }


     public boolean atualizar(Paciente paciente, boolean fotoAlterada) throws SQLException {
        if (fotoAlterada) {
            System.out.println(paciente);
            String sql = "UPDATE paciente SET nome = ?, cpf = ?, ativo = ?, local_dor = ?, nivel = ?,  foto = ? where id = ?;";
            Connection conexao = new ConexaoPostgreSQL().getConexao();
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getCpf());
            preparedStatement.setBoolean(3, paciente.isAtivo());
            preparedStatement.setString(4, paciente.getLocalDor());
            preparedStatement.setInt(5, paciente.getNivel());
            preparedStatement.setBytes(6, paciente.getFoto());
            preparedStatement.setInt(7, paciente.getId());
            int linhasAfetadas = preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            return linhasAfetadas == 1;
        } else {
            String sql = "UPDATE paciente SET nome = ?, cpf = ?, ativo = ?, local_dor = ?, nivel = ? where id = ?;";
            Connection conexao = new ConexaoPostgreSQL().getConexao();
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getCpf());
            preparedStatement.setBoolean(3, paciente.isAtivo());
            preparedStatement.setString(4, paciente.getLocalDor());
            preparedStatement.setInt(5, paciente.getNivel());
            preparedStatement.setInt(6, paciente.getId());
            int linhasAfetadas = preparedStatement.executeUpdate();
            preparedStatement.close();
            conexao.close();
            return linhasAfetadas == 1;
        }
    }

    // CR*UD 
    public Paciente obter(String cpf) throws SQLException {
        Paciente paciente = new Paciente();
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM paciente where cpf = ? and ativo is true;");
        preparedStatement.setString(1, cpf);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            paciente.setId(rs.getInt("id"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setLocalDor(rs.getString("local_dor"));
            paciente.setNivel(rs.getInt("nivel"));
            paciente.setAtivo(rs.getBoolean("ativo"));
            paciente.setFoto(rs.getBytes("foto"));
        } else {
            System.out.println("xabum!");
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

      public ArrayList<Paciente> listarAtivos() throws SQLException {
        ArrayList<Paciente> vetPaciente = new ArrayList<>();
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM paciente WHERE ativo is TRUE;");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Paciente paciente = new Paciente();
           paciente.setId(rs.getInt("id"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setLocalDor(rs.getString("local_dor"));
            paciente.setNivel(rs.getInt("nivel"));
            paciente.setAtivo(rs.getBoolean("ativo"));
            paciente.setFoto(rs.getBytes("foto"));
            vetPaciente.add(paciente);
        }
        preparedStatement.close();
        conexao.close();
        return vetPaciente;
    }

}
