package org.kun.http;

import okhttp3.OkHttpClient;

import java.net.Proxy;

public class HttpClientSingleton {
    private static OkHttpClient httpClient = null;

    private HttpClientSingleton() {
    }

    private static void createHttpClient(Proxy proxy) {
        if (proxy == null) {
            httpClient = new OkHttpClient();
        } else {
            httpClient = new OkHttpClient.Builder().proxy(proxy).build();
        }
    }

    private static void verifyHttpClient(Proxy proxy) {
        Proxy prevProxy = httpClient.proxy();

        if ((proxy != null && !proxy.equals(prevProxy)) || (proxy == null && prevProxy != null)) {
            createHttpClient(proxy);
        }
    }

    public static OkHttpClient getHttpClient(Proxy proxy) {
        if (httpClient == null) {
            createHttpClient(proxy);
        } else {
            verifyHttpClient(proxy);
        }
        return httpClient;
    }

    public static OkHttpClient getHttpClient() {
        if (httpClient == null) {
            createHttpClient(null);
        }
        return httpClient;
    }

}
