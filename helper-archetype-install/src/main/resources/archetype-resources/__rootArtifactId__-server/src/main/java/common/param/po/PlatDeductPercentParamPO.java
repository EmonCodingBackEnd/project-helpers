#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.param.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/** 平台分佣比例. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class PlatDeductPercentParamPO implements BaseParamPO {

    private static final long serialVersionUID = -1384999956352285972L;

    @JsonIgnore
    private boolean enabled;

    /** 团队奖励分佣比例定义. */
    private TeamPercent teamPercent;

    /** 培训奖励分佣比例定义. */
    private TrainPercent trainPercent;

    /** 掌柜升级到主管的规则定义. */
    private FirstUpgradeRule firstUpgradeRule;

    /** 团队奖励分佣比例定义. */
    @Data
    public static class TeamPercent implements Serializable {
        private static final long serialVersionUID = 378398077434820231L;
        /** 一级分佣比例. */
        private BigDecimal levelOne;

        /** 二级分佣比例. */
        private BigDecimal levelTwo;

        /** 三级分佣比例比例. */
        private BigDecimal levelThree;
    }

    /** 培训奖励分佣比例定义. */
    @Data
    public static class TrainPercent implements Serializable {
        private static final long serialVersionUID = 2812427702661340924L;
        /** 一级分佣比例. */
        private BigDecimal levelOne;

        /** 二级分佣比例. */
        private BigDecimal levelTwo;

        /** 三级分佣比例比例. */
        private BigDecimal levelThree;
    }

    /** 初次升级规则，掌柜升级到主管. */
    @Data
    public static class FirstUpgradeRule implements Serializable {

        private static final long serialVersionUID = -5077382654516455940L;

        /** 触发升级的金额. */
        private BigDecimal triggerAmount;

        /** 触发金额的数量. */
        private Integer triggerNumber;
    }
}
