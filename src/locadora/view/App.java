package locadora.view;

import java.util.ArrayList;
import locadora.dao.*;
import locadora.model.*;
import locadora.controller.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        PagamentoDAO salvarPagamentoDAO = new PagamentoDAO();

        ArrayList<Pagamento> pagamentos = salvarPagamentoDAO.carregarLista();
    
        pagamentos.toString();

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

