#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.param.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/** 平台抽成参数设置. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class PlatBenefitPercentParamPO implements BaseParamPO {

    private static final long serialVersionUID = -6403971624524835946L;

    @JsonIgnore
    private boolean enabled;

    /** 普通商品利益抽成百分比. */
    private BigDecimal normalGoodsBenefitPercent;

    /** 畅玩卡商品利益抽成百分比. */
    private BigDecimal superCardBenefitPercent;
}
