package org.kun.telegram;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import okhttp3.*;
import org.kun.http.HttpClientSingleton;
import org.kun.pushover.core.PushoverTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


public class TgPush {
    private static final Logger log = LoggerFactory.getLogger(PushoverTool.class);
    public static final String API_URL = "https://api.telegram.org/";
    private static final String METHOD = "sendmessage";
    private static final MediaType mediaType = MediaType.parse("application/json");


    public static String send(String botToken, String userId, String text) throws Exception {
        return send(botToken, userId, text, "markdown");
    }

    public static String send(String botToken, String userChatId, String text, String parseMode) throws Exception {
        OkHttpClient client = HttpClientSingleton.getHttpClient();
        //请求地址
        String url = API_URL + botToken + "/" + METHOD;

        SendMessage message = new SendMessage();
        message.setChatId(userChatId);
        message.setText(text);
        message.setParseMode(parseMode);

        RequestBody requestBody = RequestBody.create(mediaType, toStringBySnakeCase(message));

        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            log.info("服务器端错误: " + response);
        }
        return response.body().string();
    }

    public static String toStringBySnakeCase(BotApiMethod method) {
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        return JSON.toJSONString(method, config);
    }


}
