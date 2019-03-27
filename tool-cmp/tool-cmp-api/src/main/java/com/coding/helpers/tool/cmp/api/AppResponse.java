package com.coding.helpers.tool.cmp.api;

import com.coding.helpers.tool.cmp.exception.AppBaseStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class AppResponse<T> implements Serializable {

    private static final long serialVersionUID = -4627150270589944255L;

    protected Integer errorCode = AppBaseStatus.success().getErrorCode();

    protected String errorMessage = AppBaseStatus.success().getErrorMessage();

    protected T data;

    public static AppResponse getDefaultResponse() {
        return new AppResponse() {

            private static final long serialVersionUID = 785148869016223225L;
        };
    }
}
