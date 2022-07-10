package com.yap.yapcommon.framework.request;

import com.google.common.base.Charsets;
import com.yap.yapcommon.framework.request.httpenum.Protocol;
import com.yap.yapcommon.framework.request.httpenum.HttpMethod;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;

@Setter
@Getter
public class UpstreamProperties {


    private Charset responseCharset = Charsets.UTF_8;
    /**
     * 协议
     */
    private Protocol protocol = Protocol.http;

    /**
     * 请求方法
     */
    private HttpMethod method = HttpMethod.GET;

    /**
     * host
     */
    private String host;

    /**
     * port
     */
    private String port;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 连接超时时间
     */
    private int connectTimeout = 500;

    /**
     * 请求超时时间
     */
    private int requestTimeout = 1000;

}
