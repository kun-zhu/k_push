package org.kun.http;


import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoadHttpResource {

    private static final String DEFAULT_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
    private OkHttpClient httpClient;

    public LoadHttpResource() {
        this(10);
    }

    public LoadHttpResource(int timeoutInSec) {
        this((String) null, timeoutInSec);
    }

    public LoadHttpResource(String ipAndPort) {
        this(ipAndPort, 10);
    }

    public LoadHttpResource(String ipAndPort, int timeoutInSec) {
        if (StringUtils.isNotBlank(ipAndPort)) {
            String[] ipAndPortArray = StringUtils.split(ipAndPort, ":");
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ipAndPortArray[0], Integer.parseInt(ipAndPortArray[1])));
            this.httpClient = HttpClientSingleton.getHttpClient(proxy).newBuilder()
                    .connectTimeout(timeoutInSec, TimeUnit.SECONDS) // 设置连接超时时间为 x 秒
                    .readTimeout(timeoutInSec, TimeUnit.SECONDS)    // 设置读取超时时间为 x 秒
                    .writeTimeout(timeoutInSec, TimeUnit.SECONDS)// 设置写入超时时间为 x 秒
                    .callTimeout(timeoutInSec, TimeUnit.SECONDS)// 设置请求的超时时间为 x 秒
                    .build();
        } else {
            this.httpClient = HttpClientSingleton.getHttpClient().newBuilder()
                    .connectTimeout(timeoutInSec, TimeUnit.SECONDS) // 设置连接超时时间为 x 秒
                    .readTimeout(timeoutInSec, TimeUnit.SECONDS)    // 设置读取超时时间为 x 秒
                    .writeTimeout(timeoutInSec, TimeUnit.SECONDS)// 设置写入超时时间为 x 秒
                    .callTimeout(timeoutInSec, TimeUnit.SECONDS)// 设置请求的超时时间为 x 秒
                    .build();
        }
    }

    public String getBinResource(String url) throws Exception {
        return this.getBinResource(url, (Map) null);
    }

    public String getBinResource(String url, Map headers) throws Exception {
        if (!StringUtils.isAsciiPrintable(url)) {
            throw new Exception("A url must ONLY contain Ascii character!");
        } else {
            Request.Builder requestBuilder = new Request.Builder().url(url);
            this.setHeader(headers, requestBuilder);
            Request request = requestBuilder.build();

            Response response = httpClient.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        }
    }

    public String postBinResource(String url, String jsonBody) throws Exception {
        return this.postBinResource(url, (Map) null, jsonBody);
    }

    public String postBinResource(String url, Map headers, String jsonBody) throws Exception {
        if (!StringUtils.isAsciiPrintable(url)) {
            throw new Exception("A url must ONLY contain Ascii character!");
        } else {

            Request.Builder requestBuilder = new Request.Builder().url(url);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            requestBuilder.post(requestBody);
            this.setHeader(headers, requestBuilder);
            Request request = requestBuilder.build();

            Response response = httpClient.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        }
    }

    public String postBinResource(String url, Map<String, Object> postNvps) throws Exception {
        return this.postBinResource(url, (Map) null, postNvps);
    }

    public String postBinResource(String url, Map headers, Map<String, Object> postNvps) throws Exception {
        if (!StringUtils.isAsciiPrintable(url)) {
            throw new Exception("A url must ONLY contain Ascii character!");
        } else {

            Request.Builder requestBuilder = new Request.Builder().url(url);
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            // 将 Map 中的键值对添加到 FormBody 中
            for (Map.Entry<String, Object> entry : postNvps.entrySet()) {
                formBodyBuilder.add(entry.getKey(), entry.getValue().toString());
            }
            RequestBody requestBody = formBodyBuilder.build();
            requestBuilder.post(requestBody);
            this.setHeader(headers, requestBuilder);
            Request request = requestBuilder.build();
            Response response = httpClient.newCall(request).execute();
            return response.body().string();
        }
    }

    private void setHeader(Map headers, Request.Builder requestBuilder) {
        requestBuilder.addHeader("User-Agent", DEFAULT_UA);
        if (headers != null) {
            for (Object o : headers.keySet()) {
                String aKey = (String) o;
                requestBuilder.addHeader(aKey, (String) headers.get(aKey));
            }
        }
    }

}