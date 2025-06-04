import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        AlbumDAO albumDAO = new AlbumDAO();
        Scanner entrada = new Scanner(System.in);

        System.out.println("1) para inserir 2) deletar 3) listar 4) sair");
        int menu = entrada.nextInt();

        while (menu != 4) {
            if (menu == 1) {
                // inserindo novo album
                System.out.println("Digite o titulo do album");
                String titulo = entrada.nextLine();
                Album albumNovo = new Album();
                albumNovo.setTitulo(titulo);
                albumDAO.inserir(albumNovo);
            } else if (menu == 2) {
                System.out.println("Digite o id:");
                int id = entrada.nextInt();
                System.out.println("Deu certo?"+((albumDAO.deletar(id) == true) ? "ok" : "deu xabum!"));
            } else if (menu == 3) {
                // listando todos os albuns da base de dados
                List<Album> vetAlbum = albumDAO.listar();
                for (Album album : vetAlbum) {
                    System.out.println(album);
                }
            }

            System.out.println("1) para inserir 2) deletar 3) listar 4) sair");
            menu = entrada.nextInt();

        }

        entrada.close();

    }
}
