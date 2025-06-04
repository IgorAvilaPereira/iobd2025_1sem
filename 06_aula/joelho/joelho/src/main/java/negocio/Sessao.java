package negocio;

import java.time.LocalDateTime;

public class Sessao {
    private int id;
    // se o desejo fosse navegabilidade bidirecional, teriamos uma colecao de sessao em paciente e outra colecao de sessao em fisioterapeuta
    private Fisioterapeuta fisioterapeuta;
    private Paciente paciente;
    private LocalDateTime dataHora;

    public Sessao(){
        this.dataHora = LocalDateTime.now();
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Fisioterapeuta getFisioterapeuta() {
        return fisioterapeuta;
    }
    public void setFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        this.fisioterapeuta = fisioterapeuta;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    
}
