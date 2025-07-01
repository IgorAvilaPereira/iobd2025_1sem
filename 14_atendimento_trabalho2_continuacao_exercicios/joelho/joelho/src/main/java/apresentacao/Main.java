package apresentacao;

import java.awt.LayoutManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import negocio.Fisioterapeuta;
import negocio.Paciente;
import negocio.Relatorio;
import negocio.Sessao;
import persistencia.FisioterapeutaDAO;
import persistencia.PacienteDAO;
import persistencia.SessaoDAO;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        // criando uma sessao
        // System.out.println(new SessaoDAO().obter(4));
        // Paciente i = new PacienteDAO().obter("11111111111");
        // Fisioterapeuta p = new FisioterapeutaDAO().obter("11111");

        // Sessao n = new Sessao();
        // n.setFisioterapeuta(p);
        // n.setPaciente(i);
        // n.setDataHora(LocalDateTime.now());

        // new SessaoDAO().adicionar(n);
        
        // Paciente julie = new Paciente();
        // julie.setNome("Julie");
        // julie.setCpf("22222222222");
        // julie.setLocalDor("pulso");
        // julie.setNivel(10);
        // julie.caminhoFotoParaFoto("/home/iapereira/foto.png");
        // new PacienteDAO().inserir(julie);
        // menuSessao();
        // menuFisioterapeuta();


        // Paciente igor = new PacienteDAO().obter("11111111111");
        // igor.setNome("igor pereira");
        // igor.caminhoFotoParaFoto("/home/iapereira/foto_nova.png");
        // // System.out.println(igor.getCpf());
        // new PacienteDAO().atualizar(igor, true);

        // Paciente jAlterada = new PacienteDAO().obter("11111111111");
        // exibirFoto(jAlterada.getNome(), jAlterada.getFoto());

        // new PacienteDAO().listarAtivos().forEach(p -> System.out.println(p.getId()));

        Paciente paciente0107 = new PacienteDAO().obter("11111111111");
        exibirFoto(paciente0107.getNome(), paciente0107.getFoto());


       
    }

    private static void exibirFoto(String descricao, byte[] foto){
        JFrame frame = new JFrame("Joelho Novo Operações LTDA = Onde sua dor, é nossa dor!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new LayoutManager());
        ImageIcon icon = new ImageIcon(foto); // Replace with your image path
        JLabel label = new JLabel(descricao, icon, JLabel.CENTER);
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }

    private static void menuSessao() throws SQLException {
        String menu = "1) adicionar 0) sair";
        Scanner entrada = new Scanner(System.in);
        System.out.println(menu);
        int opcao = entrada.nextInt();
        entrada.nextLine();
        while (opcao != 0) {
            if (opcao == 1) {
                Sessao novaSessao = new Sessao();
                System.out.println("CPF paciente");
                String cpf_paciente = entrada.nextLine();
                Paciente paciente = new PacienteDAO().obter(cpf_paciente);
                novaSessao.setPaciente(paciente);
                System.out.println("crefito do fisioterapeuta");
                String crefito = entrada.nextLine();
                Fisioterapeuta fisioterapeuta = new FisioterapeutaDAO().obter(crefito);
                novaSessao.setFisioterapeuta(fisioterapeuta);
                new SessaoDAO().adicionar(novaSessao);
                System.out.println("deu ok?");
            }
            System.out.println(menu);
            opcao = entrada.nextInt();
            entrada.nextLine();            
        }
        entrada.close();
    }

    private static void menuFisioterapeuta() throws SQLException {
        String menu = "1) relatorio 2) adicionar 3) listar 4) deletar 5) atualizar 0) sair";
        Scanner entrada = new Scanner(System.in);
        System.out.println(menu);
        int opcao = entrada.nextInt();
        entrada.nextLine();
        while (opcao != 0) {
            if (opcao == 1) {
                new Relatorio().relatorioFisioterapeutas();
            }
            if (opcao == 2) {
                System.out.println("Nome:");
                String novo_nome = entrada.nextLine();
                // entrada.nextLine();
                System.out.println("Crefito:");
                String novo_crefito = entrada.nextLine();
                // String caminhoFoto = entrada.nextLine();
                // entrada.nextLine();
                Fisioterapeuta novoFisioterapeuta = new Fisioterapeuta();
                novoFisioterapeuta.setNome(novo_nome);
                novoFisioterapeuta.setCrefito(novo_crefito);
                new FisioterapeutaDAO().adicionar(novoFisioterapeuta);
                System.out.println(novoFisioterapeuta);
            }
            if (opcao == 3){
                new FisioterapeutaDAO().listarAtivos().forEach(p -> System.out.println(p));
            }
            if (opcao == 4){
                System.out.println("Crefito a ser deletado:");
                String crefito_deletar = entrada.nextLine();
                // entrada.nextLine();
                boolean resultado = new FisioterapeutaDAO().deletar(crefito_deletar);
                System.out.println("Deu bom a deleção?"+((resultado == true) ? "Sim" : "Deu xabum!"));
            }
            if (opcao == 5) {
                System.out.println("Crefito a ser atualizado:");
                String crefito_atualizar = entrada.nextLine();
                // entrada.nextLine();
                Fisioterapeuta fisioterapeutaAtualizar = new FisioterapeutaDAO().obter(crefito_atualizar);
                System.out.println("Novo nome:");
                String novo_nome_atualizar = entrada.nextLine();
                // entrada.nextLine();
                fisioterapeutaAtualizar.setNome(novo_nome_atualizar);
                // System.out.println("Novo crefito");
                // String novo_nome_atualizar = entrada.nextLine();
                
                // TODO pedir foto...
                new FisioterapeutaDAO().atualizar(fisioterapeutaAtualizar, false);
            }
            System.out.println(menu);
            opcao = entrada.nextInt();
            entrada.nextLine();
        }
        System.out.println("saindo!!");
        entrada.close();
    }
}