package com.finalproject.api.employee;

import com.finalproject.api.login.LoginAPI;
import com.finalproject.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hildan.fxgson.FxGson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class EmployeeAPI {

    private static String baseUrl = "http://localhost:8080/employee";

    public static List<Task> getAllTasks() {
        try {
            String endpoint = baseUrl + "/" + LoginAPI.email;
            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("content-Type", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed: Http error code" + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            Gson gson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            List<Task> tasks = gson.fromJson(response.toString(), new TypeToken<List<Task>>(){}.getType());

            return tasks;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void advanceTask(int taskId) {
        try {
            String endpoint = baseUrl + "/advance/" + taskId;
            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-Type", "application/json");

            String body = "";
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed: Http error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
