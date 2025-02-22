package locadora.controller;

import java.util.ArrayList;

import locadora.model.Pagamento;

public class PagamentoController {

    ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();

    public void addPagamento(double valor, int id, String metodo){
        Pagamento pagamento = new Pagamento(valor, id, metodo);
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

    public void listarPagamentos(){
        for (Pagamento pagamento : pagamentos) {
            System.out.println(pagamento.toString());
        }
    }

}
