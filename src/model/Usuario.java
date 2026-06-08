package model;

public class Usuario {
    private int id;
    private String nome, email;
    private Boolean aluguelAtivo;

    public Usuario(int id, String nome, String email, Boolean aluguelAtivo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.aluguelAtivo = aluguelAtivo;

    }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAluguelAtivo() {
        return aluguelAtivo;
    }
    public void setAluguelAtivo(Boolean aluguelAtivo) {
        this.aluguelAtivo = aluguelAtivo;
    }
    public boolean isAluguelAtivo() {
        return aluguelAtivo;    
    }


    
}
