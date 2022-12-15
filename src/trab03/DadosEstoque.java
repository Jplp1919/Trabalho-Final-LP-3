package trab03;


public class DadosEstoque {
    String titulo;
    Double preco;
    int idescritor;
    int ideditora;

    public DadosEstoque(String titulo, Double preco, int idescritor, int ideditora) {
        this.titulo = titulo;
        this.preco = preco;
        this.idescritor = idescritor;
        this.ideditora = ideditora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getIdescritor() {
        return idescritor;
    }

    public void setIdescritor(int idescritor) {
        this.idescritor = idescritor;
    }

    public int getIdeditora() {
        return ideditora;
    }

    public void setIdeditora(int ideditora) {
        this.ideditora = ideditora;
    }
    
    
}
