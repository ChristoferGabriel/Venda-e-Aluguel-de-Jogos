package model;

import java.time.LocalDate;

public class Aluguel {
 
    private LocalDate dataAlugado;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoEfetiva;
    private int id;
    private JogoAluguel jogo;
    private Usuario usuario;
    private boolean devolvido;

    public Aluguel(LocalDate dataAlugado, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoEfetiva, int id,
            JogoAluguel jogo, Usuario usuario, boolean devolvido) {
        this.dataAlugado = dataAlugado;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
        this.id = id;
        this.jogo = jogo;
        this.usuario = usuario;
        this.devolvido = devolvido;
    }

    public Aluguel(JogoAluguel jogo, Usuario usuario){
        this.usuario = usuario;
        this.jogo = jogo;
        dataAlugado = LocalDate.now();
        dataDevolucaoPrevista = dataAlugado.plusDays(jogo.getDias());
        devolvido = false;
    }

    public void devolverJogo(){
        dataDevolucaoEfetiva = LocalDate.now();
        devolvido = true;
    }

    public Aluguel(int id){
        this.id = id;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public int getId() {
        return id;
    }

    public JogoAluguel getJogo() {
        return jogo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataAlugado() {
        return dataAlugado;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }

    



}
