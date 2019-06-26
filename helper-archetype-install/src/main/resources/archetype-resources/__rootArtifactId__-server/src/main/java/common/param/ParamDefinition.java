#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * 文件名称：Param.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180813 17:28
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180813-01         Rushing0711     M201808131728 新建文件
 ********************************************************************************/
package ${package}.common.param;


import ${package}.common.param.po.BaseParamPO;
import ${package}.common.param.po.DistributionWithdrawalParamPO;
import ${package}.common.param.po.PlatBenefitPercentParamPO;
import ${package}.common.param.po.PlatDeductPercentParamPO;

/**
 * 定义系统参数信息.
 *
 * <p>创建时间: <font style="color:${symbol_pound}00FFFF">20180813 17:28</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ParamDefinition {

    interface BaseParamDefinition<T extends BaseParamPO> {
        String getName();

        Class<T> getClazz();
    }

    // ==================================================华丽的分割线==================================================

    /** 默认用户头像. */
    interface DefaultAvatar {
        String NAME = "default_avator";
    }

    /** 是否允许重复登录. */
    interface AllowDuplicateLogin {
        String NAME = "allow_duplicate_login";
    }

    /** 是否显示监控日志. */
    interface ShowWebsiteMonitorLog {
        String NAME = "show_website_monitor_log";
    }

    /** 平台抽成参数设置. */
    enum PlatBenefitPercent implements BaseParamDefinition<PlatBenefitPercentParamPO> {
        INSTANCE();

        @Override
        public String getName() {
            return "PLAT_BENEFIT_PERCENT";
        }

        @Override
        public Class<PlatBenefitPercentParamPO> getClazz() {
            return PlatBenefitPercentParamPO.class;
        }
    }

    /** 分销商提现相关参数. */
    enum DistributionWithdrawal implements BaseParamDefinition<DistributionWithdrawalParamPO> {
        INSTANCE();

        @Override
        public String getName() {
            return "DISTRIBUTION_WITHDRAWAL";
        }

        @Override
        public Class<DistributionWithdrawalParamPO> getClazz() {
            return DistributionWithdrawalParamPO.class;
        }
    }

    /** 平台分佣比例. */
    enum PlatDeductPercent implements BaseParamDefinition<PlatDeductPercentParamPO> {
        INSTANCE();

        @Override
        public String getName() {
            return "PLAT_DEDUCT_PERCENT";
        }

        @Override
        public Class<PlatDeductPercentParamPO> getClazz() {
            return PlatDeductPercentParamPO.class;
        }
    }
}
