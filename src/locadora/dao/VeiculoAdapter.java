package locadora.dao;

import com.google.gson.*;
import locadora.model.*;

import java.lang.reflect.Type;

public class VeiculoAdapter implements JsonSerializer<Veiculo>, JsonDeserializer<Veiculo> {

    @Override
    public JsonElement serialize(Veiculo veiculo, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(veiculo).getAsJsonObject();
        jsonObject.addProperty("tipo", veiculo.getClass().getSimpleName()); // Adiciona o tipo da subclasse
        return jsonObject;
    }

    @Override
    public Veiculo deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        
        // Verifica o tipo do veículo no JSON
        String tipo = jsonObject.get("tipo").getAsString();
        
        switch (tipo) {
            case "Carro":
                return context.deserialize(jsonObject, Carro.class);
            case "Moto":
                return context.deserialize(jsonObject, Moto.class);
            case "Caminhao":
                return context.deserialize(jsonObject, Caminhao.class);
            default:
                throw new JsonParseException("Tipo de veículo desconhecido: " + tipo);
        }
    }
}
