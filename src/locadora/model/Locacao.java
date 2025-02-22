package locadora.model;

import java.time.LocalDate;
import java.time.Period;

public class Locacao {

    LocalDate dataLocacao;
    LocalDate dataDevolucao;
    int valorLocacao;

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(int valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public int returnPeriodo(){
        return Period.between(dataLocacao, dataDevolucao).getDays();
    }
}
