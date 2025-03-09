package locadora.view;

import locadora.dao.*;
import locadora.model.*;
import locadora.relatorios.*;

import java.util.ArrayList;

import locadora.controller.*;

/* 
import locadora.model.*;
import java.util.ArrayList;
import java.util.Scanner;
*/

public class App {
    public static void main(String[] args) {

        RelatorioVeiculosPDF pdf1 = new RelatorioVeiculosPDF();
        VeiculoController veiculos = new VeiculoController();
        VeiculoDAO veiculoDAO = new VeiculoDAO();

        veiculos.setListaVeiculos(veiculoDAO.carregarLista());

        pdf1.gerarRelatorio(veiculos.getListaVeiculos());
















        /* 
        PagamentoDAO pagamentoDAO = new PagamentoDAO();
        RelatorioLocacaoPDF pdf = new RelatorioLocacaoPDF();
        LocacaoDAO locacaoDAO = new LocacaoDAO();
        LocacaoController locacaoController = new LocacaoController();
        RelatorioPagamentosPDF pdf2 = new RelatorioPagamentosPDF();
        ArrayList<Pagamento> pagamentos = pagamentoDAO.carregarLista();

        locacaoController.setListaLocacoes(locacaoDAO.carregarLista());

        locacaoController.toString();
        
        pdf2.gerarRelatorio(pagamentos);
        */
        //pdf.gerarRelatorio(locacaoController.getlistaLocacoes());
        
    /* 
        Scanner scanner = new Scanner(System.in);

        // Criando a instância do controller
        VeiculoController controller = new VeiculoController();
        
        // Carregar a lista de veículos do arquivo
        controller.carregarLista();

        // Exibir a lista de veículos carregados
        System.out.println("Lista de veículos carregada:");
        System.out.println(controller.toString());

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nO que deseja fazer?");
            System.out.println("1 - Adicionar um novo veículo");
            System.out.println("2 - Salvar a lista de veículos e sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (escolha) {
                case 1:
                    // Adicionar um novo veículo
                    System.out.println("Novo veículo adicionado!");
                    break;

                case 2:
                    // Salvar a lista de veículos
                    controller.salvarLista();
                    System.out.println("Lista salva com sucesso!");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();



        */
    }
}
    
    // Método para criar um novo veículo (aqui você pode personalizar para diferentes tipos de veículos)

