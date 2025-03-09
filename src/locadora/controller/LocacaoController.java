package locadora.controller;

import java.util.ArrayList;

import locadora.model.Locacao;

public class LocacaoController {

    private ArrayList<Locacao> listaLocacoes = new ArrayList<Locacao>();
    private int id = 1;

    public void adicionarLocacao(Locacao locacao) {
        locacao.setId(id);
        listaLocacoes.add(locacao);
        id++;
    }

    public void removerLocacao(Locacao locacao) {
        this.listaLocacoes.remove(locacao);
    }

    public ArrayList<Locacao> getlistaLocacoes() {
        return this.listaLocacoes;
    }

    public Locacao buscarLocacaoPorId(int id) {
        for (Locacao locacao : this.listaLocacoes) {
            if (locacao.getId() == id) {
                return locacao;
            }
        }
        return null;
    }

    public ArrayList<Locacao> getListaLocacoes(){
        return this.listaLocacoes;
    }

    public void setListaLocacoes(ArrayList<Locacao> listaLocacoes){
        if (!listaLocacoes.isEmpty()) {
            this.id = listaLocacoes.stream().mapToInt(Locacao::getId).max().orElse(0) + 1;
        }
        this.listaLocacoes = listaLocacoes;
    }

    public String toString(){

        String str = "";

        if(listaLocacoes.isEmpty()){
            return "A lista está vazia";
        }

        for(Locacao L : listaLocacoes){
            str += L.toString();
        }
        return str;
    }
}
