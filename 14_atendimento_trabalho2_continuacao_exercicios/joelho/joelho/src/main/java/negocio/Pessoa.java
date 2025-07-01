package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Pessoa {
    protected byte[] foto;

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public void caminhoDiretorioParaFotoBytes(String diretorio) throws FileNotFoundException, IOException {        
        File f = new File(diretorio);
        try (FileInputStream fileInputStream = new FileInputStream(f)) {
            this.foto = fileInputStream.readAllBytes();
        }
    }
    
}
