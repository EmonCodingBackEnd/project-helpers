package helper.archetype.cloud.common.param.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/** 分销商提现相关参数. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class DistributionWithdrawalParamPO implements BaseParamPO {

    private static final long serialVersionUID = -2147963961694298493L;

    @JsonIgnore
    private boolean enabled;

    /** 分销商提现费率. */
    private BigDecimal withdrawalRate;
}
