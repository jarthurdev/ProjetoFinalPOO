package locadora.exceptions;

public class LocacaoIdInexistenteException extends Exception{

    public LocacaoIdInexistenteException(){
        super("ID de locação não existe!");
    }


}
