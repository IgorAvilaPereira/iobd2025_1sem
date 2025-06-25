package negocio;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private LocalDateTime dataHora;
    private Cliente cliente;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }
    
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
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", dataHora=" + dataHora + ", cliente=" + cliente + "]";
    }

    

}
