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


import org.kun.pushover.bean.Message;
import org.kun.pushover.bean.PushoverResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface PushoverApi {
    String MESSAGES = "/1/messages.json";

    default PushoverResponse sendMessage(@NotBlank String token,
                                         @NotBlank String user,
                                         @NotBlank @Valid Message message) {
        return sendMessage(token,
                user,
                message.getMessage(),
                message.getUrl(),
                message.getUrlTitle(),
                message.getTitle(),
                message.getPriority() != null ? message.getPriority().getValue() : null,
                message.getRetry(),
                message.getExpire(),
                message.getSound() != null ? message.getSound().toString() : null);
    }


    PushoverResponse sendMessage(@NotBlank String token,
                                 @NotBlank String user,
                                 @NotBlank String message,
                                 String url,
                                 @SuppressWarnings("checkstyle:ParameterName") String url_title,
                                 String title,
                                 Integer priority,
                                 Integer retry,
                                 Integer expire,
                                 String sound);
}
