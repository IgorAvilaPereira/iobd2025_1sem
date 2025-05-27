package negocio;

import java.time.LocalDateTime;

import org.json.JSONObject;

public class Anotacao {
    private int id;
    private LocalDateTime dataHora;
    private JSONObject conteudo;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public JSONObject getConteudo() {
        return conteudo;
    }
    public void setConteudo(JSONObject conteudo) {
        this.conteudo = conteudo;
    }

    public String convertToJSON(){
        JSONObject aJSON = new JSONObject(this);
        return aJSON.toString();
    }

    

}
