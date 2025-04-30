package negocio;

public class Fisioterapeuta {
    private int id;
    private String nome;
    private String crefito;
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
    public String getCrefito() {
        return crefito;
    }
    public void setCrefito(String crefito) {
        this.crefito = crefito;
    }
    @Override
    public String toString() {
        return "Fisioterapeuta [id=" + id + ", nome=" + nome + ", crefito=" + crefito + "]";
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    
    
}
