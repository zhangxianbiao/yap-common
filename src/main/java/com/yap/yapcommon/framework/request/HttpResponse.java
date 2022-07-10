package com.yap.yapcommon.framework.request;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class HttpResponse {

    private final int statusCode;

    private final Map<String, String> httpHeaders;

    private final byte[] payload;

    private final String responseBody;

    private final String host;

    private final String path;

    private final String query;

    private final String serviceUrl;

    private final long rt;

}
