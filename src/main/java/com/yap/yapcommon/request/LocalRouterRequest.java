package com.yap.yapcommon.request;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yap.yapcommon.framework.request.AbstractHttpRequest;
import com.yap.yapcommon.framework.request.AsyncYapHttpClient;
import com.yap.yapcommon.framework.request.HttpRequest;
import com.yap.yapcommon.framework.util.JsonUtil;
import lombok.Getter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.desktop.OpenFilesEvent;
import java.util.HashMap;
import java.util.Optional;

@Component
public class LocalRouterRequest extends AbstractHttpRequest<LocalDO, Object, LocalRouterUpstreamProperties> {
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
    public LocalDO covertBodyToOutput(String body) {
        final LocalDO localDO = Optional.ofNullable(body)
                .map(data -> JsonUtil.fromJsonWithoutEx(body, new TypeReference<LocalDO>() {}))
                .orElse(null);
        return localDO;
    }
}
