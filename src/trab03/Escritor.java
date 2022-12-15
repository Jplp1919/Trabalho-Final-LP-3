package trab03;


public class Escritor {
    private int id;
    private String primeiroNome;
    private String sobreNome;

    public Escritor(String primeiroNome, String sobreNome) {
        this.primeiroNome = primeiroNome;
        this.sobreNome = sobreNome;
    }

    public Escritor() {
    }

    public Escritor(int id, String primeiroNome, String sobreNome) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.sobreNome = sobreNome;
    }
    
    

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
 @Override
    public String toString(){
       return String.format("Escritores: %d, %s, %s", 
               this.id, this.primeiroNome, this.sobreNome);
    }
}
