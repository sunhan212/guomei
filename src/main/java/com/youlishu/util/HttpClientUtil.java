package com.youlishu.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClientUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public final static String GET = "GET";
    public final static String POST = "POST";
    public final static String PUT = "PUT";

    public static String get(String url, Map<String, String> headers) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            if (headers != null) {
                for (String key : headers.keySet()) {
                    connection.setRequestProperty(key, headers.get(key));
                }
            }
            connection.connect();
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String post(String method, String url, Map<String, String> headers, JSONObject json) {
        String result;
        HttpPost post = null;
        HttpPut put = null;
        StringEntity strEntity = null;
        if (json != null) {
            strEntity = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        }
        if (method.equals(POST)) {
            post = new HttpPost(url);
            if (headers != null) {
                for (String key : headers.keySet()) {
                    post.setHeader(key, headers.get(key));
                }
            }
            post.setEntity(strEntity);
        } else if (method.equals(PUT)) {
            put = new HttpPut(url);
            if (headers != null) {
                for (String key : headers.keySet()) {
                    put.setHeader(key, headers.get(key));
                }
            }
            put.setEntity(strEntity);
        }
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = null;
            if (method.equals(POST)) {
                response = httpClient.execute(post);
            } else if (method.equals(PUT)) {
                response = httpClient.execute(put);
            }
            InputStream in = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                str.append(line + '\n');
            }
            br.close();
            in.close();
            result = str.toString();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String post(String method, String url, Map<String, String> headers, JSONArray json) {
        String result;
        HttpPost post = null;
        HttpPut put = null;
        StringEntity strEntity = null;
        if (json != null) {
            strEntity = new StringEntity(json.toString(), StandardCharsets.UTF_8);
        }
        if (method.equals(POST)) {
            post = new HttpPost(url);
            if (headers != null) {
                for (String key : headers.keySet()) {
                    post.setHeader(key, headers.get(key));
                }
            }
            post.setEntity(strEntity);
        } else if (method.equals(PUT)) {
            put = new HttpPut(url);
            if (headers != null) {
                for (String key : headers.keySet()) {
                    put.setHeader(key, headers.get(key));
                }
            }
            put.setEntity(strEntity);
        }
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = null;
            if (method.equals(POST)) {
                response = httpClient.execute(post);
            } else if (method.equals(PUT)) {
                response = httpClient.execute(put);
            }
            InputStream in = response.getEntity().getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder str = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                str.append(line + '\n');
            }
            br.close();
            in.close();
            result = str.toString();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> String objToString(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject strToJson(String str) {
        if (str == null) {
            return null;
        }
        return JSONObject.parseObject(str);
    }

}
