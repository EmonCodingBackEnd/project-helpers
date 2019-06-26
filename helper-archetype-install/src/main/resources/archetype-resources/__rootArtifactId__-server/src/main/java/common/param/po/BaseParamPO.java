#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.param.po;

import java.io.Serializable;

public interface BaseParamPO extends Serializable {

    /** 参数是否启用. */
    boolean isEnabled();

    void setEnabled(boolean enabled);
}
