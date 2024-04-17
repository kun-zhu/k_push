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
package org.kun.pushover.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Message {
    @NotBlank
    private final String message;

    /**
     * A title for your supplementary URL, otherwise just the URL is shown.
     */
    @Size(max = 100)
    private String urlTitle;

    /**
     * A supplementary URL to show with your message.
     */
    @Size(max = 512)
    private String url;

    /**
     * your message's title, otherwise your app's name is used.
     */
    @Size(max = 250)
    private String title;

    /**
     * User's device name to send the message directly to that device, rather than all of the user's devices (multiple devices may be separated by a comma).
     */
    @Size(max = 25)
    @Pattern(regexp = "^[A-Za-z0-9]{0,25}$")
    private String device;

    private Priority priority;

    /**
     * the name of one of the sounds supported by device clients to override the user's default sound choice.
     */
    private Sound sound;

    /**
     * A Unix timestamp of your message's date and time to display to the user, rather than the time your message is received by our API.
     */
    private Integer timestamp;

    public Message(String message) {
        this.message = message;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getMessage() {
        return message;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", urlTitle='" + urlTitle + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", device='" + device + '\'' +
                ", priority=" + (priority != null ? priority.getValue() : "null") +
                ", sound=" + (sound != null ? sound.toString() : "null") +
                ", timestamp=" + timestamp +
                '}';
    }

    public static Builder builder(String message) {
        return new Builder(message);
    }

    public static class Builder {
        Message message;

        Builder(String message) {
            this.message = new Message(message);
        }

        public Builder timestamp(Integer timestamp) {
            message.setTimestamp(timestamp);
            return this;
        }

        public Builder sound(Sound sound) {
            message.setSound(sound);
            return this;
        }

        public Builder priority(Priority priority) {
            message.setPriority(priority);
            return this;
        }

        public Builder title(String title) {
            message.setTitle(title);
            return this;
        }

        public Builder device(String device) {
            message.setDevice(device);
            return this;
        }

        public Builder url(Url url) {
            message.setUrl(url.getUrl());
            message.setUrlTitle(url.getTitle());
            return this;
        }

        public Message build() {
            return message;
        }
    }
}

