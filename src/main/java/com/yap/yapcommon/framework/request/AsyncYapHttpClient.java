package com.yap.yapcommon.framework.request;

import com.yap.yapcommon.framework.Consts;
import com.yap.yapcommon.framework.request.baseinterface.HttpClient;
import com.yap.yapcommon.framework.request.httpenum.HttpMethod;
import com.yap.yapcommon.framework.util.UrlUtils;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.asynchttpclient.*;
import org.asynchttpclient.Request;
import org.asynchttpclient.netty.request.NettyRequest;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Slf4j
public class AsyncYapHttpClient implements HttpClient {

    private final AsyncHttpClient asyncHttpClient;

    // 通过AsyncCommunicationClientConfig进行自动的注入
    public AsyncYapHttpClient(AsyncHttpClient asyncHttpClient) {
        this.asyncHttpClient = asyncHttpClient;
    }

    public Mono<HttpResponse> request(HttpRequest httpRequest) {
        return doRequest(httpRequest);
    }

    public Mono<HttpResponse> doRequest(HttpRequest httpRequest) {
        // yap 的
        HttpMethod httpMethod = httpRequest.getHttpMethod();
        String url = httpRequest.getUrl();

        Map<String, String> requestParams = httpRequest.getRequestParams();

        String urlWithParams = UrlUtils.appendParams(url, requestParams);

        // 通过自己的HttpRequest 创建asyncHttpClient的request对象
        // 设置url, 参数, 超时时间等
        RequestBuilder requestBuilder = new RequestBuilder(httpMethod.name(), true)
                .setUrl(urlWithParams)
                .setRequestTimeout(httpRequest.getRequestTimeout());

        // 设置header
        if (MapUtils.isNotEmpty(httpRequest.getHttpHeaders())){
            for (Map.Entry<String, String> entry : httpRequest.getHttpHeaders().entrySet()) {
                // 如果name为空，netty会报错，需要判断
                if(StringUtils.isNotBlank(entry.getKey())) {
                    requestBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        Request request = requestBuilder.build();

        long startTime = System.currentTimeMillis();
        return Mono.create(sink -> {
            // 使用MonoSink
            try {
                asyncHttpClient.executeRequest(request, new AsyncCompletionHandler<HttpResponse>() {
                    @Override
                    public void onRequestSend(NettyRequest request) {
                        super.onRequestSend(request);
                    }

                    @Override
                    public void onTcpConnectSuccess(InetSocketAddress remoteAddress, Channel connection) {
                        super.onTcpConnectSuccess(remoteAddress, connection);
                    }

                    @Override
                    public HttpResponse onCompleted(Response response) {
                        long rt = System.currentTimeMillis() - startTime;

                        if (response.getStatusCode() != Consts.HTTP_STATUS_OK) {
                            log.error("error");// todo
                            InternalHttpClientException exception = new InternalHttpClientException(
                                    "HttpStatusCode=" + response.getStatusCode() + ", URL=" + urlWithParams
                            );
                            sink.error(exception);
                            return null;
                        }

                        // 通过asyncHttpClient返回的response创建自己的HttpResponse 对象
                        HttpResponse httpResponse = buildHttpResponse(response, rt);

                        sink.success(httpResponse);
                        return httpResponse;
                    }

                    @Override
                    public void onThrowable(Throwable t) {
                        long rt = System.currentTimeMillis() - startTime;

                        RuntimeException exception;
                        if (t instanceof TimeoutException) {
                            log.error("HttpRequestTimeout, url={}", urlWithParams);
                            exception = new InternalHttpClientException("HttpRequestTimeout, url="+urlWithParams, t);
                        } else {
                            log.error("HttpRequestFail, url={}", urlWithParams);
                            exception = new InternalHttpClientException("HttpRequestFail, url="+urlWithParams, t);
                        }
                        sink.error(exception);
                    }
                });

            }catch (Exception e){
                long rt = System.currentTimeMillis() - startTime;
                log.error("HttpRequestFail, url={}", urlWithParams);
                sink.error(e);
            }
        });
    }

    /**
     * 创建HttpResponse 对象
     * @param response
     * @param rt
     * @return
     */
    private HttpResponse buildHttpResponse(Response response, long rt) {
        Map<String, String> responseHeader = new HashMap<>();

        if (response.hasResponseHeaders()) {
            Iterator<Map.Entry<String, String>> iterator = response.getHeaders().iteratorAsString();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                responseHeader.put(entry.getKey(), entry.getValue());
            }
        }

        return HttpResponse.builder()
                .statusCode(response.getStatusCode())
                .payload(response.getResponseBodyAsBytes())
                .responseBody(response.getResponseBody())
                .host(response.getUri().getHost())
                .path(response.getUri().getPath())
                .query(response.getUri().getQuery())
                .serviceUrl(response.getUri().toUrl())
                .rt(rt)
                .build();
    }

    @Override
    public void close() {
        if (null == this.asyncHttpClient) {
            return;
        }

        if (asyncHttpClient.isClosed()) {
            return;
        }

        try {
            asyncHttpClient.close();
        }catch (Exception e) {
            log.error("Failed to close httpclient", e);
        }

    }

    /**
     * just for test
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AsyncYapHttpClient asyncYapHttpClient = new AsyncYapHttpClient(new DefaultAsyncHttpClient());
        HttpRequest httpRequest = HttpRequest.builder()
                .url("http://www.baidu.com:80/robots.txt")
                .httpMethod(HttpMethod.GET)
                .requestTimeout(1000)
                .build();

        Mono<HttpResponse> response = asyncYapHttpClient.request(httpRequest);

        response.subscribe(res -> {
            System.out.println(new String(res.getPayload()));
        });
    }
}
