package com.yap.yapcommon.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yap.yapcommon.pojo.Person;

public class LocalDO {
    // 必须加JsonProperty
    @JsonProperty("code")
    private Integer code;

    @JsonProperty("status")
    private String status;

    @JsonProperty("person")
    private Person person;
}
