/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kun.pushover.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.kun.pushover.bean.PushoverResponse;
import org.kun.http.LoadHttpResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManualPushoverHttpClient implements PushoverApi {

    private static Logger logger = LoggerFactory.getLogger(ManualPushoverHttpClient.class);
    public static final String KEY_TOKEN = "token";
    public static final String KEY_USER = "user";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_URL = "url";
    public static final String KEY_URL_TITLE = "url_title";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PRIORITY = "priority";
    public static final String KEY_SOUND = "sound";
    public static final String HOST_LIVE = "https://api.pushover.net/1/messages.json";
    private final LoadHttpResource loadHttpResource;

    public ManualPushoverHttpClient() {
        this.loadHttpResource = new LoadHttpResource();
    }


    private PushoverResponse sendMessage(ListOrderedMap<String, Object> body) {
        try {
            String result = loadHttpResource.postBinResource(HOST_LIVE, null, body);
            JSONObject resp = JSON.parseObject(result);
            return new PushoverResponse(resp.getInteger("status"), resp.getString("request"));
        } catch (Exception e) {
            logger.info("", e);
        }
        return null;
    }

    @Override
    public PushoverResponse sendMessage(String token, String user, String message, String url, @SuppressWarnings("checkstyle:ParameterName") String url_title, String title, Integer priority, String sound) {
        ListOrderedMap<String, Object> body = new ListOrderedMap<>();
        body.put(KEY_TOKEN, token);
        body.put(KEY_USER, user);
        body.put(KEY_MESSAGE, message);
        if (url != null) {
            body.put(KEY_URL, url);
        }
        if (url_title != null) {
            body.put(KEY_URL_TITLE, url_title);
        }
        if (title != null) {
            body.put(KEY_TITLE, title);
        }
        if (priority != null) {
            body.put(KEY_PRIORITY, priority);
        }
        if (sound != null) {
            body.put(KEY_SOUND, sound);
        }
        return sendMessage(body);
    }

}
