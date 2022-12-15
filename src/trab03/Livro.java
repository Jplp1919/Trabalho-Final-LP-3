package trab03;

public class Livro {

    private Integer id;
    private String titulo;
    private String genero;
    private String isbn;
    private Double preco;
    private int idEscritor;
    private int idEditora;

    public Livro(int id, String titulo, String genero, String isbn, Double preco, int idEscritor) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.isbn = isbn;
        this.preco = preco;
        this.idEscritor = idEscritor;
    }

        public Livro(int id, String titulo, String genero, String isbn, Double preco, int idEscritor, int idEditora) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.isbn = isbn;
        this.preco = preco;
        this.idEscritor = idEscritor;
        this.idEditora = idEditora;
    }

    
    public Livro(String titulo, String genero, String isbn, Double preco, int idEscritor) {
        this.titulo = titulo;
        this.genero = genero;
        this.isbn = isbn;
        this.preco = preco;
        this.idEscritor = idEscritor;
    }

    public Livro() {

    }

    public Livro(Integer id, String titulo, String genero, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.preco = preco;

    }

    public int getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(int idEditora) {
        this.idEditora = idEditora;
    }

    public int getIdEscritor() {
        return idEscritor;
    }

    public void setIdEscritor(int idEscritor) {
        this.idEscritor = idEscritor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s, %s, %f",
                this.id, this.titulo, this.genero, this.isbn, this.preco);
    }
}
