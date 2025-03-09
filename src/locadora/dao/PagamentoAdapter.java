package locadora.dao;

import com.google.gson.*;

import locadora.model.Locacao;
import locadora.model.Pagamento;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class PagamentoAdapter implements JsonSerializer<Pagamento>, JsonDeserializer<Pagamento> {

    @Override
    public JsonElement serialize(Pagamento pagamento, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Serializa os campos básicos de Pagamento
        jsonObject.addProperty("valor", pagamento.getValor());
        jsonObject.addProperty("id", pagamento.getId());
        jsonObject.addProperty("metodo", pagamento.getMetodo());
        jsonObject.addProperty("dataPagamento", pagamento.getDataPagamento().toString());

        // Serializa o objeto Locacao (incluindo o Veiculo adaptado)
        JsonObject locacaoJson = context.serialize(pagamento.getLocacao()).getAsJsonObject();
        jsonObject.add("locacao", locacaoJson);

        return jsonObject;
    }

    @Override
    public Pagamento deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Recupera os campos básicos de Pagamento
        double valor = jsonObject.get("valor").getAsDouble();
        int id = jsonObject.get("id").getAsInt();
        String metodo = jsonObject.get("metodo").getAsString();
        LocalDate dataPagamento = LocalDate.parse(jsonObject.get("dataPagamento").getAsString());

        // Desserializa o objeto Locacao (incluindo o Veiculo com o adaptador específico)
        Locacao locacao = context.deserialize(jsonObject.get("locacao"), Locacao.class);

        return new Pagamento(valor, metodo, dataPagamento, locacao);
    }
}

