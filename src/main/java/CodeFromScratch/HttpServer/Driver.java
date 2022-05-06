package CodeFromScratch.HttpServer;

import CodeFromScratch.HttpServer.Config.ConfigurationManager;

// Driver class for an HTTP Server
public class Driver {
    public static void main(String[] args) {
        System.out.println("Server Starting ...");
        ConfigurationManager.loadConfigurationFile("src/main/resources/http.json");
    }
}
