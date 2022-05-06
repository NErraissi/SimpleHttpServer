package CodeFromScratch.HttpServer.Tools;

import CodeFromScratch.HttpServer.Config.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    private static final myObjectMapper myObjectMapper = defaultObjectMapper();
    private static myObjectMapper defaultObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }
    public static JsonNode parse(String jsonSrc) throws IOException {
        return myObjectMapper.readTree(jsonSrc);
    }
    public static <A> A fromJson(JsonNode node, class <A> class Class<Configuration> configurationClass) throws JsonProcessingException{
        return myObjectMapper.treeToValue(node, class);
    }
    public static JsonNode toJson(Object ob){
        return myObjectMapper.valueToTree(obj);
    }
    public static String stringify(JsonNode node) throws JsonProcessingException{
        return generateJson(node, false);
    }
    public static String stringifyPretty(JsonNode node) throws JsonProcessingException{
        return generateJson(node, true);
    }
    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException{
        ObjectWriter objectwriter = myObjectMapper.writer();
        if(pretty){
            objectwriter = objectwriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectwriter.writeValuesAsArray(o);
    }
}
