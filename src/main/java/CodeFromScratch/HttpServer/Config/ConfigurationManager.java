package CodeFromScratch.HttpServer.Config;

import CodeFromScratch.HttpServer.Tools.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    public static ConfigurationManager myConfigurationManager;
    public static Configuration myCurrentConfiguration;

    private ConfigurationManager ConfigurationManager() {
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    /**
     * Used to load a configuration file by the path provided
     */
    public static void loadConfigurationFile(String filePath)  {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        //char i;
        try {
            while (fileReader.read() != -1) {
                char readChar = (char) fileReader.read();
                sb.append(readChar);
        }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Erreur parsing the Configuration file", e);
        }
        try {
            myCurrentConfiguration =Json.fromJson(conf, Configuration .class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Erreur parsing the Configuration file. Internal", e);
        }
    }

    /**
     * Returns the Current loaded Configuration
     */
    public Configuration getCurrentConfiguration(){
        if(myCurrentConfiguration == null){
           throw new HttpConfigurationException("No Current configuration set.");
        }
        return myCurrentConfiguration;

    }
}
