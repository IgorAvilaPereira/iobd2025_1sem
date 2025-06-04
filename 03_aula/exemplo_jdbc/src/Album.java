public class Album {
    private int id;
    private String titulo;

    public Album(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Album() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Album [id=" + id + ", titulo=" + titulo + "]";
    }

    
    
}
