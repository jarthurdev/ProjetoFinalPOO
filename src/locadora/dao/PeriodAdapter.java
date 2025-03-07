package locadora.dao;

import java.lang.reflect.Type;
import java.time.Period;
import com.google.gson.*;

public class PeriodAdapter implements JsonSerializer<Period>, JsonDeserializer<Period>{
    
    public JsonElement serialize(Period period, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("years", period.getYears());
            jsonObject.addProperty("months", period.getMonths());
            jsonObject.addProperty("days", period.getDays());
            return jsonObject; // Converte Period para um JSON { "years": 2, "months": 3, "days": 10 }
        }

    public Period deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            int years = jsonObject.get("years").getAsInt();
            int months = jsonObject.get("months").getAsInt();
            int days = jsonObject.get("days").getAsInt();
            return Period.of(years, months, days); // Converte o JSON de volta para Period
    }

}
