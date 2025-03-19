import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// dao => data, acess object => objeto de acesso a dados
public class AlbumDAO {

    public boolean deletar(int id) throws SQLException{
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        String sql = "DELETE FROM album where id = " + id;
        int linhasAfetadas = conexao.prepareStatement(sql).executeUpdate();
        conexao.close();
        return linhasAfetadas == 1;
    }

    public boolean inserir (Album album) throws SQLException{
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        String sql = "INSERT INTO album (titulo) VALUES ('"+album.getTitulo()+"');";
        int linhasAfetadas = conexao.prepareStatement(sql).executeUpdate();
        conexao.close();
        return linhasAfetadas == 1;
    }

    public List<Album> listar() throws SQLException {
        List<Album> vetAlbum = new ArrayList<>();
        Connection conexao = new ConexaoPostgreSQL().getConexao();
        String sql = "SELECT id, titulo from album";
        ResultSet rs = conexao.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            Album album = new Album(rs.getInt("id"), rs.getString("titulo"));
            vetAlbum.add(album);
        }
        conexao.close();
        return vetAlbum;
    }
}
