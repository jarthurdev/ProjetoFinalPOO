package locadora.controller;

import java.util.ArrayList;

import locadora.model.Pagamento;

public class PagamentoController {

    ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
    private int id = 1;

    public void addPagamento(Pagamento pagamento){
        pagamento.setId(id);
        id++;
        pagamentos.add(pagamento);
    }

    public void removePagamento(int id){
        for (Pagamento pagamento : pagamentos) {
            if(pagamento.getId() == id){
                pagamentos.remove(pagamento);
                break;
            }
        }
    }

    public String toString(){

        String str = "";
        if(pagamentos.isEmpty()){
            return "A lista está vazia";
        }
        for(Pagamento L : pagamentos){
            str += L.toString();
        }
        return str;
    }

    public void setListaLocacoes(ArrayList<Pagamento> listaPagamentos){
        if (!listaPagamentos.isEmpty()) {
            this.id = listaPagamentos.stream().mapToInt(Pagamento::getId).max().orElse(0) + 1;
        }
        this.pagamentos = listaPagamentos;
    }

    public ArrayList<Pagamento> getListaLocacoes(){
        return this.pagamentos;
    }

    public int getId() {
        return id;
    }
}
