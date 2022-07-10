package com.yap.yapcommon.framework.config;

import com.yap.yapcommon.framework.request.AsyncYapHttpClient;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.proxy.ProxyServerSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * bean JavaConfig 配置文件
 */
@Configuration
public class AsyncYapHttpClientConfig {

    @Bean
    public AsyncHttpClientConfig asyncHttpClientConfig() {
        final AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
                .setUseProxyProperties(false)
                .setProxyServerSelector(ProxyServerSelector.NO_PROXY_SELECTOR)
                .build();
        return config;
    }

    @Bean
    public AsyncHttpClient asyncHttpClient(@Autowired AsyncHttpClientConfig asyncHttpClientConfig) {
        return new DefaultAsyncHttpClient(asyncHttpClientConfig);
    }

    @Bean
    public AsyncYapHttpClient asyncWebClient(@Autowired AsyncHttpClient asyncHttpClient) {
        return new AsyncYapHttpClient(asyncHttpClient);
    }

}
