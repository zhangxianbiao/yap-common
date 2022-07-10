package com.yap.yapcommon.framework.request;

import com.yap.yapcommon.framework.request.httpenum.HttpMethod;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
public class HttpRequest {
    private final HttpMethod httpMethod;

    private final String url;

    private final int requestTimeout;


    //TODO zxb Singular

    @Singular
    private final Map<String, String> requestParams;

    @Singular
    private final Map<String, String> httpHeaders;

}
