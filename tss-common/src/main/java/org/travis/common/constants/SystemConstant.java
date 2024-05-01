package org.travis.common.constants;

/**
 * @ClassName SystemConstant
 * @Description 系统常量类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
public class SystemConstant {
    /**
     * 请求头中的请求 ID 信息
     */
    public static final String REQUEST_ID_HEADER = "requestId";
    /**
     * 请求头中的用户 ID 信息
     */
    public static final String USER_ID_HEADER = "userId";
    /**
     * 请求头字段-请求来源 - 网关模块
     */
    public static final String GATEWAY_ORIGIN_NAME = "gateway";
    /**
     * 请求头字段 - 请求来源
     */
    public static final String REQUEST_FROM_HEADER = "x-request-from";
    /**
     * 请求头字段 - 是否需要鉴权
     */
    // public static final String IS_NEED_AUTH = "is-need-auth";
    /**
     * 标记「异常响应」已处理
     */
    public static final String BODY_PROCESSED_MARK_HEADER = "IS_BODY_PROCESSED";


    /**
     * 数据库表字段 - 创建时间
     */
    public static final String DATA_FIELD_NAME_CREATE_TIME = "create_time";
    /**
     * 数据库表字段 - 创建时间
     */
    public static final String DATA_FIELD_NAME_UPDATE_TIME = "update_time";
    /**
     * 数据库表字段 - 创建人（creator）
     */
    public static final String DATA_FIELD_NAME_CREATOR = "creator";
    /**
     * 数据库表字段 - 更新人（updater）
     */
    public static final String DATA_FIELD_NAME_UPDATER = "updater";
}
