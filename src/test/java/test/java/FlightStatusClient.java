package test.java;

import org.json.JSONException;
import org.json.JSONObject;

import steps.java.Flight;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlightStatusClient {
    private final String BASE_URL = "http://localhost:8080/rest/flights";

    public Flight findByFlightNumber(String flightNumber) throws JSONException {
        String json = findByFlightNumberInJson(flightNumber);
        if (json == null) return null;
        JSONObject obj = new JSONObject(json);
        String number = obj.optString("number", null);
        String from = obj.optString("from", null);
        String to = obj.optString("to", null);
        return Flight.number(number).from(from).to(to);
    }

    public String findByFlightNumberInJson(String flightNumber) {
        try {
            String urlStr = BASE_URL.endsWith("/") ? BASE_URL + flightNumber : BASE_URL + "/" + flightNumber;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    status >= 200 && status < 400 ? conn.getInputStream() : conn.getErrorStream()
            ));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            conn.disconnect();
            return sb.toString();
        } catch (Exception e) {
            // Em contexto de testes, retornar null ou lançar runtime conforme necessidade
            return null;
        }
    }
}
