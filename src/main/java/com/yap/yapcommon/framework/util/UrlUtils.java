package com.yap.yapcommon.framework.util;

import com.google.common.base.Joiner;
import com.yap.yapcommon.framework.Consts;
import org.apache.commons.collections.MapUtils;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

public class UrlUtils {

    public static String urlEncode(String str){
        return URLEncoder.encode(str, Consts.UTF8);
    }

    public static String appendParams(String url, Map<String, String> params) {
        return appendParams(url, params, Consts.MARK_AND);

    }

    public static String appendParams(String url, Map<String, String> params, String paramDelimiter) {
        if(MapUtils.isEmpty(params)){
            return url;
        }

        String queryParams = Joiner.on(paramDelimiter)
                .withKeyValueSeparator(Consts.MARK_EQUAL)
                .useForNull("")
                .join(params);

        StringBuilder sb = new StringBuilder();
        if(url.contains(Consts.MARK_QUESTION)){
            sb.append(url).append(paramDelimiter).append(queryParams);
        }else {
            sb.append(url).append(Consts.MARK_QUESTION).append(queryParams);
        }
        return sb.toString();
    }

    public static String appendAndEncodeParams(String url, Map<String, String> params, String paramDelimiter, Charset charset) {
        return appendAndEncodeParams(url, params, charset, Consts.MARK_AND);
    }

    public static String appendAndEncodeParams(String url, Map<String, String> params, Charset charset, String paramDelimiter) {
        if(MapUtils.isEmpty(params)) {
            return url;
        }
        // todo zxb
        return url;
    }
}
