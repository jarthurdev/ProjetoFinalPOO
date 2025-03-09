package locadora.dao;
import java.util.ArrayList;

public interface Persistencia<T> {

   
ArrayList<T> carregarLista();


void salvarLista(ArrayList<T> lista);


void verificarECriarArquivo();


} 