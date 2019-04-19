package com.bamboo.Exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wls
 * @date 2019-04-19 15:49
 */
public class MyException extends RuntimeException{

    private static final long serialVersionUID = -6971716908203238516L;
    //private String code;
    //private String description;
    //private Map<String, Object> resultMap = new HashMap();


    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
