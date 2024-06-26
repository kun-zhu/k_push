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
package org.kun.pushover.conf;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PushoverApplicationConfigurationProperties implements PushoverApplicationConfiguration {
    @NotBlank
    @Size(max = 30, min = 30)
    @Pattern(regexp = "^[A-Za-z0-9]{30,30}$")
    private String token;

    private final String name;

    public PushoverApplicationConfigurationProperties( String name) {
        this.name = name;
    }

    public PushoverApplicationConfigurationProperties(String token, String name) {
        this.token = token;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setToken( String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }
}
