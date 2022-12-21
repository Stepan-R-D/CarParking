package com.example.myparking;

import okhttp3.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

enum HttpMethods {
    GET,
    DELETE,
    POST,
    PUT
}

class HttpsRequests {
    private String appBaseUrl = "fe80::765d:2d84:f63d:9bcc%21:8081";
    private OkHttpClient client = new OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private Request formRequest(String option, FormBody data, HttpMethods method) {
        switch (method) {
            case POST: return new Request.Builder().url(appBaseUrl + option).post(data).build();
            case PUT: return new Request.Builder().url(appBaseUrl + option).put(data).build();
            case DELETE: return new Request.Builder().url(appBaseUrl + option).delete(data).build();
            default: return new Request.Builder().url(appBaseUrl + option).get().build();
        }
    }

    // Sends request with given params
    String sendAsyncRequest(String option, HashMap<String, String> body, HttpMethods method) throws IOException {
        String result;

        FormBody.Builder postBody = new FormBody.Builder();
        for (Map.Entry<String, String> entry: body.entrySet()) {
            postBody.add(entry.getKey(), entry.getValue());
        }
        FormBody postData = postBody.build();

        Request request = formRequest(option, postData, method);

        Response resp = client.newCall(request).execute();
        return (resp.isSuccessful()) ? resp.body().string() : "";
    }
}