package com.yap.yapcommon.framework.request;

import com.yap.yapcommon.framework.request.baseinterface.Request;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.Optional;

public abstract class AbstractHttpRequest<Output, Input, Prop extends UpstreamProperties> implements Request<Output, Input> {

    public abstract AsyncYapHttpClient getAsyncYapHttpClient();

    public abstract Prop getUpstreamProperties();

    public abstract HttpRequest doBuildRequest(HttpRequest.HttpRequestBuilder httpRequestBuilder);

    public abstract Output covertBodyToOutput(String body);

    /**
     *
     * @return
     */
    private HttpRequest buildRequest() {
        Prop prop = getUpstreamProperties();

        HttpRequest.HttpRequestBuilder builder = HttpRequest.builder()
                .httpMethod(prop.getMethod())
                .url(buildUrl(prop))
                .requestTimeout(prop.getRequestTimeout() > 0 ? prop.getRequestTimeout() : 1000);

        return doBuildRequest(builder);
    }

    /**
     *
     * @param prop
     * @return
     */
    public String buildUrl(Prop prop) {
        StringBuilder sb = new StringBuilder(prop.getProtocol().name())
                .append("://")
                .append(prop.getHost())
                .append(":")
                .append(prop.getPort())
                .append(prop.getPath());

        return sb.toString();
    }

    /**
     * 发出请求
     * @return
     */
    public Mono<Optional<Output>> request() {
        HttpRequest httpRequest = buildRequest();

        Mono<Optional<Output>> outputMono = getAsyncYapHttpClient().request(httpRequest)
                .map(response -> readResponseBody(response))
                //.then()
                .doOnNext(x -> System.out.println("on next:"))
                .doOnSuccess(bodyStringOptional -> {
                    //
                    System.out.println("add success debug info");
                })
                .doOnError(error -> {
                    System.out.println("add error debug info" + error.getStackTrace().toString());
                })
                .map(body -> getOutputBody(body));
        return outputMono;
    }

    /**
     * 获取请求的body体 底层只有一个转字符串的方法
     * @param
     * @return
     */
    public Optional<String> readResponseBody(HttpResponse r) {
        return readResponseBodyAsString(r, getUpstreamProperties().getResponseCharset());
    }

    /**
     * 获取请求的body体
     * @param r
     * @param charset
     * @return
     */
    private Optional<String> readResponseBodyAsString(HttpResponse r, Charset charset) {
        if (r.getPayload() != null || r.getPayload().length > 0) {
            String body = new String(r.getPayload(), charset);
            return Optional.of(body);
        }
        return Optional.empty();
    }

    /**
     * 将body体转换为Output类型，子类重写covertBodyToOutput方法实现
     * @param optionalString
     * @return
     */
    protected Optional<Output> getOutputBody(Optional<String> optionalString) {
        return optionalString.map(body -> covertBodyToOutput(body));
    }

}
