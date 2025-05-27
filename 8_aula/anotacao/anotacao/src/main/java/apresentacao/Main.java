package apresentacao;

import java.sql.SQLException;

import org.json.JSONObject;

import negocio.Anotacao;
import persistencia.AnotacaoDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Anotacao anotacao = new Anotacao();
        // JSONObject conteudo = new JSONObject();
        // conteudo.put("titulo", "agora temos palestras");
        // conteudo.put("texto", "texto");
        // conteudo.put("cor", "red");
        // anotacao.setConteudo(conteudo);
        Anotacao a = new AnotacaoDAO().obter(3);
        JSONObject aJSON = new JSONObject(a);
        System.out.println(aJSON);
        // System.out.println(new AnotacaoDAO().inserir(anotacao));
    }
}