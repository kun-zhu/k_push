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
import javax.validation.constraints.NotNull;
import java.util.List;

public class PushoverResponse {

    @NotNull
    private Integer status;


    @NotBlank
    private String request;


    private List<String> errors;

    public PushoverResponse() {

    }

    public PushoverResponse( Integer status,String request) {
        this.status = status;
        this.request = request;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors( List<String> errors) {
        this.errors = errors;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRequest() {
        return request;
    }

    public void setStatus( Integer status) {
        this.status = status;
    }

    public void setRequest( String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "PushoverResponse{" +
                "status=" + status +
                ", request='" + request + '\'' +
                ", errors=" + errors +
                '}';
    }
}
