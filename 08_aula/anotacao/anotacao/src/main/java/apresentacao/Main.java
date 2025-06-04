package apresentacao;

import java.sql.SQLException;

import org.json.JSONObject;

import negocio.Anotacao;
import persistencia.AnotacaoDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        // new AnotacaoDAO().deletar(3);
        // Anotacao anotacao = new Anotacao();
        // JSONObject conteudo = new JSONObject();
        // conteudo.put("titulo", "agora temos palestras");
        // conteudo.put("texto", "texto");
        // conteudo.put("cor", "red");
        // anotacao.setConteudo(conteudo);
        Anotacao a = new AnotacaoDAO().obter(4);
        a.getConteudo().put("cor", "gray");
        new AnotacaoDAO().atualizar(a);
        // JSONObject aJSON = new JSONObject(a);
        // System.out.println(aJSON);
        // System.out.println(new AnotacaoDAO().inserir(anotacao));
    }
}