package com.yap.yapcommon.request;

import com.yap.yapcommon.framework.request.AbstractHttpRequest;
import com.yap.yapcommon.framework.request.AsyncYapHttpClient;
import com.yap.yapcommon.framework.request.HttpRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class LocalRouterRequest extends AbstractHttpRequest<String, Object, LocalRouterUpstreamProperties> {
    @Getter
    @Autowired
    LocalRouterUpstreamProperties upstreamProperties;

    @Getter
    @Autowired
    AsyncYapHttpClient asyncYapHttpClient;


    public HttpRequest doBuildRequest(HttpRequest.HttpRequestBuilder httpRequestBuilder) {
        return httpRequestBuilder.requestParams(new HashMap<>() {{put("type", "2");}})
                .build();
    }
    @Override
    public String covertBodyToOutput(String body) {

        // todo  写DO 并进行转换 string -> json
        return body;
    }
}
