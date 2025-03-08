package locadora.controller;

import java.util.ArrayList;

import locadora.model.Locacao;

public class LocacaoController {
    private ArrayList<Locacao> listaLocacoes = new ArrayList<Locacao>();
    private int id = 0;

    public void adicionarLocacao(Locacao locacao) {
        locacao.setId(id);
        listaLocacoes.add(locacao);
        id++;
    }

    public void removerLocacao(Locacao locacao) {
        this.listaLocacoes.remove(locacao);
    }

    public ArrayList<Locacao> listarLocacoes() {
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

    public void carregarListaLocacoes(ArrayList<Locacao> lista){
        this.listaLocacoes = lista;

        if (!lista.isEmpty()) {
            this.id = lista.stream().mapToInt(Locacao::getId).max().orElse(0) + 1;
        }
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
