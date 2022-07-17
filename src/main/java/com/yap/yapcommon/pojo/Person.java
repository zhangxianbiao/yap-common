package com.yap.yapcommon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "do.person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String id;
    private String name;
    private String age;
}
