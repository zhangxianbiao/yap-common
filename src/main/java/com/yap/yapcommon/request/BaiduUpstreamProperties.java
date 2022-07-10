package com.yap.yapcommon.request;

import com.yap.yapcommon.framework.request.UpstreamProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upstream.baidu")
public class BaiduUpstreamProperties extends UpstreamProperties {
}
