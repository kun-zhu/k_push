package org.kun.discord;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import okhttp3.*;
import org.kun.http.HttpClientSingleton;
import org.kun.pushover.core.PushoverTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DiscordPush {
    private static final Logger log = LoggerFactory.getLogger(PushoverTool.class);

    public String sendMessage(String webhook, String message) {
        try {
            OkHttpClient client = HttpClientSingleton.getHttpClient();
            String jsonInputString = "{\"content\": \"" + message + "\"}";
            RequestBody body = RequestBody.create(jsonInputString, MediaType.get("application/json; charset=utf-8"));

            Request request = new Request.Builder()
                    .url(webhook)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return getResponseBodyAsString(response.body());
            } else {
                log.info("Unexpected code " + response);
                throw new RuntimeException("Unexpected code " + response);
            }
        } catch (Exception e) {
            log.info("send mess error: ", e);
            throw new RuntimeException("send mess error: ", e);
        }
    }

    private static String getResponseBodyAsString(ResponseBody body) throws IOException {
        if (null != body) {
            return body.string();
        } else {
            return "";
        }
    }


}
