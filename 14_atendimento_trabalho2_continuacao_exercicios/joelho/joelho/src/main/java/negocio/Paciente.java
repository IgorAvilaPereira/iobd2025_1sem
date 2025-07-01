package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Paciente extends Pessoa {
    private int id;
    private String nome;
    private String cpf;
    private String localDor;
    private int nivel;
    private boolean ativo;
    // private byte[] foto;
    
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
    // public byte[] getFoto() {
    //     return foto;
    // }
    // public void setFoto(byte[] foto) {
    //     this.foto = foto;
    // }

 
    @Override
    public String toString() {
        return "Paciente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", localDor=" + localDor + ", nivel=" + nivel
                + ", ativo=" + ativo + ", foto=" + Arrays.toString(foto) + "]";
    }

    
    

}
