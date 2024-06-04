package org.example.integration;

import com.google.gson.Gson;
import org.example.Classes.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONPlaceholderService {

    private static final String JSON_PLACEHOLDER_URL = "https://jsonplaceholder.typicode.com/users/";

    public static User getUserById(int userId) throws IOException {
        URL url = new URL(JSON_PLACEHOLDER_URL + userId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            return gson.fromJson(response.toString(), User.class);
        } else {
            throw new IOException("Erro ao buscar usuário. Código de resposta: " + responseCode);
        }
    }
}