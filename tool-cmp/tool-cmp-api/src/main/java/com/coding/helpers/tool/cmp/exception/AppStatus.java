package com.coding.helpers.tool.cmp.exception;

import lombok.Getter;

@Getter
public enum AppStatus {
    SUCCESS("00000", "一切 ok - 正确执行后的返回"),

    U0001("U0001", "用户端错误 - 一级宏观错误码"),
    U0100("U0100", "用户注册错误 - 二级宏观错误码"),
    U0101("U0101", "用户未同意隐私协议"),
    U0102("U0102", "注册国家或地区受限"),
    U0110("U0110", "用户名校验失败"),
    U0111("U0111", "用户名已存在"),
    U0112("U0112", "用户名包含敏感词"),
    U0113("U0113", "用户名包含特殊字符"),
    U0120("U0120", "密码校验失败"),
    U0121("U0121", "密码长度不够"),
    U0122("U0122", "密码强度不够"),
    U0130("U0130", "校验码输入错误"),
    U0131("U0131", "短信校验码输入错误"),
    U0132("U0132", "邮件校验码输入错误"),
    U0133("U0133", "语音校验码输入错误"),
    U0140("U0140", "用户证件异常"),
    U0141("U0141", "用户证件类型未选择"),
    U0142("U0142", "大陆身份证编号校验非法"),
    U0143("U0143", "护照编号校验非法"),
    U0144("U0144", "军官证编号校验非法"),
    U0150("U0150", "用户基本信息校验失败"),
    U0151("U0151", "手机格式校验失败"),
    U0152("U0152", "地址格式校验失败"),
    U0153("U0153", "邮箱格式校验失败"),
    U0200("U0200", "用户登陆异常 - 二级宏观错误码"),
    U0201("U0201", "用户账户不存在"),
    U0202("U0202", "用户账户被冻结"),
    U0203("U0203", "用户账户已作废"),
    U0210("U0210", "用户密码错误"),
    U0211("U0211", "用户输入密码次数超限"),
    U0220("U0220", "用户身份校验失败"),
    U0221("U0221", "用户指纹识别失败"),
    U0222("U0222", "用户面容识别失败"),
    U0223("U0223", "用户未获得第三方登陆授权"),
    U0230("U0230", "用户登陆已过期"),
    U0240("U0240", "用户验证码错误"),
    U0241("U0241", "用户验证码尝试次数超限"),
    U0300("U0300", "访问权限异常 - 二级宏观错误码"),
    U0301("U0301", "访问未授权"),
    U0302("U0302", "正在授权中"),
    U0303("U0303", "用户授权申请被拒绝"),
    U0310("U0310", "因访问对象隐私设置被拦截"),
    U0311("U0311", "授权已过期"),
    U0312("U0312", "无权限使用 API"),
    U0320("U0320", "用户访问被拦截"),
    U0321("U0321", "黑名单用户"),
    U0322("U0322", "账号被冻结"),
    U0323("U0323", "非法 IP 地址"),
    U0324("U0324", "网关访问受限"),
    U0325("U0325", "地域黑名单"),
    U0330("U0330", "服务已欠费"),
    U0340("U0340", "用户签名异常"),
    U0341("U0341", "RSA 签名错误"),
    U0400("U0400", "用户请求参数错误 - 二级宏观错误码"),
    U0401("U0401", "包含非法恶意跳转链接"),
    U0402("U0402", "无效的用户输入"),
    U0410("U0410", "请求必填参数为空"),
    U0411("U0411", "用户订单号为空"),
    U0412("U0412", "订购数量为空"),
    U0413("U0413", "缺少时间戳参数"),
    U0414("U0414", "非法的时间戳参数"),
    U0420("U0420", "请求参数值超出允许的范围"),
    U0421("U0421", "参数格式不匹配"),
    U0422("U0422", "地址不在服务范围"),
    U0423("U0423", "时间不在服务范围"),
    U0424("U0424", "金额超出限制"),
    U0425("U0425", "数量超出限制"),
    U0426("U0426", "请求批量处理总个数超出限制"),
    U0427("U0427", "请求 JSON 解析失败"),
    U0430("U0430", "用户输入内容非法"),
    U0431("U0431", "包含违禁敏感词"),
    U0432("U0432", "图片包含违禁信息"),
    U0433("U0433", "文件侵犯版权"),
    U0440("U0440", "用户操作异常"),
    U0441("U0441", "用户支付超时"),
    U0442("U0442", "确认订单超时"),
    U0443("U0443", "订单已关闭"),
    U0500("U0500", "用户请求服务异常 - 二级宏观错误码"),
    U0501("U0501", "请求次数超出限制"),
    U0502("U0502", "请求并发数超出限制"),
    U0503("U0503", "用户操作请等待"),
    U0504("U0504", "WebSocket 连接异常"),
    U0505("U0505", "WebSocket 连接断开"),
    U0506("U0506", "用户重复请求"),
    U0600("U0600", "用户资源异常 - 二级宏观错误码"),
    U0601("U0601", "账户余额不足"),
    U0602("U0602", "用户磁盘空间不足"),
    U0603("U0603", "用户内存空间不足"),
    U0604("U0604", "用户 OSS 容量不足"),
    U0605("U0605", "用户配额已用光"),
    U0700("U0700", "用户上传文件异常 - 二级宏观错误码"),
    U0701("U0701", "用户上传文件类型不匹配"),
    U0702("U0702", "用户上传文件太大"),
    U0703("U0703", "用户上传图片太大"),
    U0704("U0704", "用户上传视频太大"),
    U0705("U0705", "用户上传压缩文件太大"),
    U0800("U0800", "用户当前版本异常 - 二级宏观错误码"),
    U0801("U0801", "用户安装版本与系统不匹配"),
    U0802("U0802", "用户安装版本过低"),
    U0803("U0803", "用户安装版本过高"),
    U0804("U0804", "用户安装版本已过期"),
    U0805("U0805", "用户 API 请求版本不匹配"),
    U0806("U0806", "用户 API 请求版本过高"),
    U0807("U0807", "用户 API 请求版本过低"),
    U0900("U0900", "用户隐私未授权 - 二级宏观错误码"),
    U0901("U0901", "用户隐私未签署"),
    U0902("U0902", "用户摄像头未授权"),
    U0904("U0904", "用户图片库未授权"),
    U0905("U0905", "用户文件未授权"),
    U0906("U0906", "用户位置信息未授权"),
    U0907("U0907", "用户通讯录未授权"),
    U1000("U1000", "用户设备异常 - 二级宏观错误码"),
    U1001("U1001", "用户相机异常"),
    U1002("U1002", "用户麦克风异常"),
    U1003("U1003", "用户听筒异常"),
    U1004("U1004", "用户扬声器异常"),
    U1005("U1005", "用户 GPS 定位异常"),

    S0001("S0001", "系统执行出错 - 一级宏观错误码"),
    S0100("S0100", "系统执行超时 - 二级宏观错误码"),
    S0101("S0101", "系统订单处理超时"),
    S0200("S0200", "系统容灾功能被触发 - 二级宏观错误码"),
    S0210("S0210", "系统限流"),
    S0220("S0220", "系统功能降级"),
    S0300("S0300", "系统资源异常 - 二级宏观错误码"),
    S0310("S0310", "系统资源耗尽"),
    S0311("S0311", "系统磁盘空间耗尽"),
    S0312("S0312", "系统内存耗尽"),
    S0313("S0313", "文件句柄耗尽"),
    S0314("S0314", "系统连接池耗尽"),
    S0315("S0315", "系统线程池耗尽"),
    S0320("S0320", "系统资源访问异常"),
    S0321("S0321", "系统读取磁盘文件失败"),

    T0001("T0001", "调用第三方服务出错 - 一级宏观错误码"),
    T0100("T0100", "中间件服务出错 - 二级宏观错误码"),
    T0110("T0110", "RPC 服务出错"),
    T0111("T0111", "RPC 服务未找到"),
    T0112("T0112", "RPC 服务未注册"),
    T0113("T0113", "接口不存在"),
    T0120("T0120", "消息服务出错"),
    T0121("T0121", "消息投递出错"),
    T0122("T0122", "消息消费出错"),
    T0123("T0123", "消息订阅出错"),
    T0124("T0124", "消息分组未查到"),
    T0130("T0130", "缓存服务出错"),
    T0131("T0131", "key 长度超过限制"),
    T0132("T0132", "value 长度超过限制"),
    T0133("T0133", "存储容量已满"),
    T0134("T0134", "不支持的数据格式"),
    T0140("T0140", "配置服务出错"),
    T0150("T0150", "网络资源服务出错"),
    T0151("T0151", "VPN 服务出错"),
    T0152("T0152", "CDN 服务出错"),
    T0153("T0153", "域名解析服务出错"),
    T0154("T0154", "网关服务出错"),
    T0200("T0200", "第三方系统执行超时 - 二级宏观错误码"),
    T0210("T0210", "RPC 执行超时"),
    T0220("T0220", "消息投递超时"),
    T0230("T0230", "缓存服务超时"),
    T0240("T0240", "配置服务超时"),
    T0250("T0250", "数据库服务超时"),
    T0300("T0300", "数据库服务出错 - 二级宏观错误码"),
    T0311("T0311", "表不存在"),
    T0312("T0312", "列不存在"),
    T0321("T0321", "多表关联中存在多个相同名称的列"),
    T0331("T0331", "数据库死锁"),
    T0341("T0341", "主键冲突"),
    T0400("T0400", "第三方容灾系统被触发 - 二级宏观错误码"),
    T0401("T0401", "第三方系统限流"),
    T0402("T0402", "第三方功能降级"),
    T0500("T0500", "通知服务出错 - 二级宏观错误码"),
    T0501("T0501", "短信提醒服务失败"),
    T0502("T0502", "语音提醒服务失败"),
    T0503("T0503", "邮件提醒服务失败"),
    ;

    /**
     * 错误码规范：<br>
     *
     * <ol>
     *   <li><span color="red">【强制】</span>错误码的制定规则：快速溯源、简单易记、沟通标准化。
     *   <li><span color="red">【强制】</span>错误码不体现版本号和错误等级信息。
     *   <li><span color="red">【强制】</span>全部正常，但不得不填充错误码时返回五个零：00000。
     *   <li><span color="red">【强制】</span>错误码为字符串类型，共5位，分成两个部分：错误产生来源 + 四位数字编号。
     *       <p>说明：错误产生来源分为U/S/T，U表示错误来源于用户，比如参数错误；S表示错误来源于当前系统，往往是业务逻辑出错；T表示错误来源于第三方服务，比如CDN服务出错；
     *       四位数字编号从0001到9999，大类之间的步长间距预留100。
     *   <li><span color="red">【强制】</span>编号不与公司业务架构、更不与阻止架构挂钩，一切与平台先到先申请的原则进行，审批生效，编号即被永久固定。
     *   <li><span color="red">【强制】</span>错误码使用者避免随意定义新的错误码。
     *   <li><span color="red">【强制】</span>错误码不能直接输出给用户作为提示信息使用。
     *       <p>说明：堆栈（stack_trace）、错误信息（error_message）、错误码（error_code）、提示信息（user_tip）是一个有效关联并相互转义的和谐整体，但是请勿越俎代庖。
     *   <li><span color="yellow">【推荐】</span>错误码之外的业务独特信息由error_message来承载，而不是让错误码本身涵盖过多具体业务属性。
     *   <li><span color="yellow">【推荐】</span>在获取第三方服务错误码时，向上抛出允许本系统转义，由C转为B，并且在错误信息上带有原有的第三方错误码。
     *   <li><span color="green">【参考】</span>错误码分为一级宏观错误码、二级宏观错误码、三级宏观错误码。
     *       <p>说明：在无法更加具体确定的错误场景中，可以直接使用一级宏观错误码。
     *       <p>正例：调用第三方服务出错是一级、中间件错误是二级、消息服务出错是三级。
     * </ol>
     */
    private String errorCode;

    private String errorMessage;

    AppStatus(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
