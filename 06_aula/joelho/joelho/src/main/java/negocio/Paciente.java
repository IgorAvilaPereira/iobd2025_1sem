package negocio;

public class Paciente {
    private int id;
    private String nome;
    private String cpf;
    private String localDor;
    private int nivel;
    private boolean ativo;

    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getLocalDor() {
        return localDor;
    }
    public void setLocalDor(String localDor) {
        this.localDor = localDor;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }


    

}
