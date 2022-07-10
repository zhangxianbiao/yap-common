package com.yap.yapcommon.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "do.person")
@Data
public class Person {
    private String name;
    private String age;
}
