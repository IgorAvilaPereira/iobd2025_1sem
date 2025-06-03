package negocio;

import java.time.LocalDate;

public class Cliente extends Pessoa {
    private LocalDate dataCadastro;

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    


}
