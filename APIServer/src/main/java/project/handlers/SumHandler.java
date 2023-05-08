package project.handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.json.simple.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import java.io.IOException;

import java.io.*;

public class SumHandler implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException{
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "application/json");
        JSONParser parser = new JSONParser();

        int sum = 0;

        try (FileReader reader = new FileReader("data/input/user.json"))
        {
            //Read JSON file
            Object obj = parser.parse(reader);
 
            JSONArray userList = (JSONArray) obj;
            for(int i = 0; i < userList.size(); i++){
                JSONObject user = (JSONObject) userList.get(i);
                sum += Integer.valueOf(user.get("post_count").toString());
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sum", sum);
        String response = jsonObject.toString();
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
