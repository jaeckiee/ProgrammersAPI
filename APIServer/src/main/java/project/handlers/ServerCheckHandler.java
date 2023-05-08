package project.handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.json.simple.*;

import java.io.IOException;
import java.io.OutputStream;

public class ServerCheckHandler implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "server check");
        String response = jsonObject.toString();
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
