package locadora.view;

import locadora.model.Carro;
import locadora.model.Veiculo;

public class App {
    public static void main(String[] args) throws Exception {

        Veiculo carro = new Carro("ABC1234", "Fusca", 1970);

        if(carro.isStatus()){
            System.out.println("Veículo disponível para locação!");
        } else {
            System.out.println("Veículo indisponível para locação!");
        }


        }
}
