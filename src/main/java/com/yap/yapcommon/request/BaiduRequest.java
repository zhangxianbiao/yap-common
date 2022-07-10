package com.yap.yapcommon.request;

import com.yap.yapcommon.framework.request.AbstractHttpRequest;
import com.yap.yapcommon.framework.request.AsyncYapHttpClient;
import com.yap.yapcommon.framework.request.HttpRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
//@EnableConfigurationProperties(BaiduUpstreamProperties.class)
public class BaiduRequest extends AbstractHttpRequest<String, String, BaiduUpstreamProperties> {

    @Getter
    @Autowired
    BaiduUpstreamProperties upstreamProperties;

    @Getter
    @Autowired
    AsyncYapHttpClient asyncYapHttpClient;

    public HttpRequest doBuildRequest(HttpRequest.HttpRequestBuilder httpRequestBuilder) {
        return httpRequestBuilder.requestParams(new HashMap<>())
                .build();
    }
    @Override
    public String covertBodyToOutput(String body) {
        return body;
    }

}
