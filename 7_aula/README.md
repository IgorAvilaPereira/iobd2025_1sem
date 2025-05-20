
**1) Adicionar ao projeto **joelho** imagens através dos BLOB's:**

### Blob's - Bytea e OID (Arquivos)

#### bytea

```sql
CREATE TABLE publicidade.banner (
    id SERIAL PRIMARY KEY,
    arquivo bytea, 
    legenda text, 
    altura integer,
    largura integer,
    link text, -- http://www.g1.com
    tipo text CHECK (tipo = 'SUPERIOR' OR tipo = 'INFERIOR') DEFAULT 'SUPERIOR',
    qtde_cliques INTEGER DEFAULT 0
);

1) INSERT INTO publicidade.banner (arquivo, legenda, link, tipo) VALUES
(pg_read_binary_file('/tmp/globo.png'), 'Clique Aqui', 'http://www.g1.com','SUPERIOR');

2) INSERT INTO publicidade.banner (arquivo, legenda, link, tipo) VALUES
(pg_read_file('/tmp/globo.png')::bytea, 'Clique Aqui', 'http://www.g1.com','SUPERIOR');

```

No JAVA:

```java

-- classe de modelo
public class Banner {
    private int id;  
    private String legenda;
    private int largura;
    private int altura;
    private String link;
    private String tipo;
    private int qtdeCliques;
    private byte[] arquivo;

    // getters and setters...

    public void setArquivo(String diretorio) throws FileNotFoundException, IOException {        
        File f = new File(diretorio);
        FileInputStream fileInputStream = new FileInputStream(f);
        this.arquivo = fileInputStream.readAllBytes();
    }
}

-- classe de persistência
public class BannerDAO {
  private ConexaoPostgreSQL conexaoPostgreSQL;

  public Banner obter(int id) throws SQLException{
        Banner b = new Banner();
        this.conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection conn = this.conexaoPostgreSQL.getConexao();
        String sql = "SELECT * FROM publicidade.banner WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            b.setId(rs.getInt("id"));
            b.setAltura(rs.getInt("altura"));
            b.setLargura(rs.getInt("largura"));
            b.setLegenda(rs.getString("legenda"));
            b.setLink(rs.getString("link"));
            b.setQtdeCliques(rs.getInt("qtde_cliques"));
            b.setTipo(rs.getString("tipo"));
            b.setArquivo(rs.getBytes("arquivo"));
        }
        conn.close();
        return b;
    }

    public void adicionar(Banner banner, String dir) throws SQLException, FileNotFoundException {
        this.conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection conn = this.conexaoPostgreSQL.getConexao();
        String sql = "INSERT INTO publicidade.banner "
                + " (arquivo, legenda, link, tipo) VALUES " +
                        "(?,?,?,?);";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
       
        // opcao 1
        banner.setArquivo(dir);  // chamando o método setArquivo(String dir);
        preparedStatement.setBytes(1, banner.getArquivo());    
        
        // opcao 2
        // File file = new File(dir);
        // FileInputStream fis = new FileInputStream(file);
        // preparedStatement.setBinaryStream(1, fis, file.length());

        preparedStatement.setString(2, banner.getLegenda());
        preparedStatement.setString(3, banner.getLink());
        preparedStatement.setString(4, banner.getTipo());
        preparedStatement.executeUpdate();
        conn.close();
    }

  public ArrayList<Banner> listar() throws SQLException {        
        ArrayList<Banner> vetBanner = new ArrayList<Banner>();
        this.conexaoPostgreSQL = new ConexaoPostgreSQL();
        Connection conn = this.conexaoPostgreSQL.getConexao();
        String sql = "SELECT * FROM publicidade.banner";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            Banner b = new Banner();
            b.setId(rs.getInt("id"));
            b.setAltura(rs.getInt("altura"));
            b.setLargura(rs.getInt("largura"));
            b.setLegenda(rs.getString("legenda"));
            b.setLink(rs.getString("link"));
            b.setQtdeCliques(rs.getInt("qtde_cliques"));
            b.setTipo(rs.getString("tipo"));
            b.setArquivo(rs.getBytes("arquivo"));
            vetBanner.add(b);
        }
        conn.close();
        return vetBanner;
    }
   // ...
}

// Main
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, FileNotFoundException {

       // escrita
       Banner bannerVetorial = new Banner();
       bannerVetorial.setLink("http://vetorial.net");
       bannerVetorial.setLegenda("clique aqui e contrate sua banda larga");
       bannerVetorial.setTipo("SUPERIOR");
       new BannerDAO().adicionar(bannerVetorial, "/home/iapereira/vetorial.png");

        // leitura 
        BannerDAO bannerDAO = new BannerDAO();
        Banner bannerGlobo = bannerDAO.obter(id);
        ImageIcon imageIcon = new ImageIcon(bannerGlobo.getArquivo());
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500, 500);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jFrame.add(jLabel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }   

```

#### oid (Arquivos Grandes)

```sql
CREATE TABLE largeObjects_Devmedia
 (
   cod_imagem INTEGER,
   nome_imagem VARCHAR(30),
   local_imagem oid,
   CONSTRAINT pk_cod_imagem PRIMARY KEY(cod_imagem)
 );

INSERT INTO public.largeobjects_devmedia(cod_imagem, nome_imagem, local_imagem)
 VALUES (1, 'naruto_shippuden', lo_import('D:/imagens/naruto_shippuden.jpg'));
```

Perceba que no momento de inserção dos dados na tabela utilizamos a função específica _lo_import()_, que é utilizada para carregar imagens para a tabela de sistema pg_largeobjects.

Obs: Caso os dados não sejam inseridos na tabela, é necessário atribuir as devidas permissões no banco de dados, usando a seguinte instrução:

```sql
GRANT SELECT, INSERT, UPDATE ON pg_largeobject TO PUBLIC;
```

De forma similar a importação da imagem para a base de dados, podemos também exportá-la para a nossa máquina utilizando a função lo_export() com as informações de OID e o local no qual será armazenada a imagem como parâmetros , de acordo com a seguinte instrução:

```sql
SELECT lo_export(32784, 'D:/imagens/naruto_shippuden.jpg');
```

Temos também a função lo_unlink(), que é utilizada para realizar a remoção do objeto, como podemos observar na instrução a seguir:

```sql
SELECT lo_unlink(32784);
```

**Links Complementares:**

* :fire: https://www.postgresql.org/docs/7.4/jdbc-binary-data.html

* https://www.cybertec-postgresql.com/en/binary-data-performance-in-postgresql/

* http://postgresqlbr.blogspot.com/2013/04/trate-com-blobs-e-clobs-diretamente-no.html

* https://www.devmedia.com.br/trabalhando-com-large-objects-no-postgresql/34167

<!-- https://github.com/IgorAvilaPereira/iobd2022_2sem/blob/main/PortalDeNoticiasBanners -->

**2) Integrar com a aplicação Java/JDBC**

&nbsp;
