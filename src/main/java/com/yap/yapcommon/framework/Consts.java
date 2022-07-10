package com.yap.yapcommon.framework;

import java.nio.charset.Charset;

public interface Consts {

    /**
     *
     */
    String MARK_AND = "&";
    String MARK_QUESTION = "?";
    String MARK_EQUAL = "=";

    int HTTP_STATUS_OK = Integer.parseInt("200");

    String UTF8_NAME = "UTF-8";
    Charset UTF8 = Charset.forName(UTF8_NAME);
}
