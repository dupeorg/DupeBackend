package dupeorg.dupebackend.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import dupeorg.dupebackend.items.ItemService;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

public class MainServer {
    private final ItemService itemService;
    public MainServer(ItemService itemService) {
        this.itemService = itemService;
    }

    public void createAndStartServer(){
        // Create an HTTP server on port 8080
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Define API endpoints and handlers
        server.createContext("/get_items", new GetItemsHandler(itemService));
        server.createContext("/create_item", new CreateItemHandler());

        // Start the server
        server.start();
    }

    static class GetItemsHandler implements HttpHandler {
        private final ItemService itemService;
        public GetItemsHandler(ItemService itemService) {
            this.itemService = itemService;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Implement logic to handle GET /get_items
            // Retrieve items and send the response
            String response = null;
            try {
                response = itemService.convertItemsToJson(itemService.getAllItems());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sendResponse(exchange, response);
        }
    }

    static class CreateItemHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Implement logic to handle POST /create_item
            // Create an item and send the response
            String response = "POST /create_item endpoint called";
            sendResponse(exchange, response);
        }
    }

    private static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
