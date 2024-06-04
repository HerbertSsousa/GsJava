package org.example.integration;

import com.google.gson.Gson;
import org.example.Classes.Endereco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCEPService {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/";

    public static Endereco buscarEnderecoPorCEP(String cep) throws IOException {
        URL url = new URL(VIA_CEP_URL + cep + "/json");
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
            return gson.fromJson(response.toString(), Endereco.class);
        } else {
            throw new IOException("Erro ao buscar endereço. Código de resposta: " + responseCode);
        }
    }
}
