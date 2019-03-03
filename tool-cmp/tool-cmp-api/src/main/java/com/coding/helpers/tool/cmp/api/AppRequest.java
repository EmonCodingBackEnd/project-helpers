package com.coding.helpers.tool.cmp.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Getter
@Setter
public abstract class AppRequest<T extends AppResponse> implements Serializable {

    private static final long serialVersionUID = -311322238195233470L;

    /** 请求ID，用作幂等性（idempotence）校验，要求每次请求的ID不重复. */
    protected String requestId;

    /** 数字签名区域. */
    protected String signature;

    /** 加密. */
    protected boolean encryption = false;

    /**
     * 获取应答类型.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190301 18:25</font><br>
     * [请在此输入功能详述]
     *
     * @return java.lang.Class<T>
     * @author Rushing0711
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public Class<T> responseClass() {
        return (Class<T>)
                ((ParameterizedType) this.getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }
}
