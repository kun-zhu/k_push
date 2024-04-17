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
package org.kun.pushover.core;

import org.kun.pushover.bean.Message;
import org.kun.pushover.bean.PushoverResponse;
import org.kun.pushover.bean.PushoverUser;
import org.kun.pushover.client.PushoverApi;
import org.kun.pushover.conf.PushoverApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PushoverTool {
    private static final Logger LOG = LoggerFactory.getLogger(PushoverTool.class);
    private final PushoverApplicationConfiguration applicationConfiguration;
    private final PushoverApi api;


    public PushoverTool(PushoverApplicationConfiguration applicationConfiguration,
                        PushoverApi api) {
        this.applicationConfiguration = applicationConfiguration;
        this.api = api;
    }


    public PushoverResponse send(@NotNull PushoverUser user,
                                 @NotNull @Valid Message message) {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Sending notification to user {} from App {} with contents {}", user.getName(), applicationConfiguration.getName(), message);
        }
        return api.sendMessage(applicationConfiguration.getToken(), user.getKey(), message);
    }
}
