package com.yap.yapcommon.framework.request;

public class InternalHttpClientException extends RuntimeException{

    public InternalHttpClientException() {

    }

    public InternalHttpClientException(String msg) {
        super(msg);
    }

    public InternalHttpClientException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InternalHttpClientException(Throwable cause) {
        super(cause);
    }

    public InternalHttpClientException(String msg, Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStaceTrace) {
        super(msg, cause, enableSuppression, writableStaceTrace);

    }
}
