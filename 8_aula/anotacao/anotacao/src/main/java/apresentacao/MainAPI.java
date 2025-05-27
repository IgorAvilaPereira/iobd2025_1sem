package apresentacao;

import io.javalin.Javalin;
import persistencia.AnotacaoDAO;

public class MainAPI {
    public static void main(String[] args) {
        var app = Javalin.create(/*config*/)
            .get("/", ctx -> ctx.result(new AnotacaoDAO().obter(3).convertToJSON()))
            .start(7070);
    }
}
