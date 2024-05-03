package org.travis.common.config.mybatis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.travis.common.constants.SystemConstant;
import org.travis.common.utils.UserThreadLocalUtil;

import java.sql.SQLException;

/**
 * @ClassName MybatisAutoCompleteInterceptor
 * @Description Mybatis 字段自动填充插件（记录 修改人 + 创建人）
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
public class MybatisAutoCompleteInterceptor implements InnerInterceptor {
    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        // 1.对于所有「插入+更新」操作执行 updateOperation
        updateOperation(parameter);

        // 2.对于所有「插入」操作执行 insertOperation
        if (ms.getSqlCommandType().compareTo(SqlCommandType.INSERT) == 0) {
            insertOperation(parameter);
        }
    }

    private void updateOperation(Object parameter) {
        // 判断是否有 updater 字段
        if (ReflectUtil.hasField(parameter.getClass(), SystemConstant.DATA_FIELD_NAME_UPDATER)) {
            // 存在 updater 字段，则尝试获取 userId
            Long userId = UserThreadLocalUtil.getUserId();
            if (userId != null) {
                // 添加 updater = 当前处理用户 userId
                ReflectUtil.setFieldValue(parameter, SystemConstant.DATA_FIELD_NAME_UPDATER, userId);
            }
        }
        // 判断是否有 update_time 字段
        if (ReflectUtil.hasField(parameter.getClass(), SystemConstant.DATA_FIELD_NAME_UPDATE_TIME)) {
            ReflectUtil.setFieldValue(parameter, SystemConstant.DATA_FIELD_NAME_UPDATE_TIME, DateUtil.date());
        }
    }

    private void insertOperation(Object parameter) {
        // 判断是否有 creator 字段
        if (ReflectUtil.hasField(parameter.getClass(), SystemConstant.DATA_FIELD_NAME_CREATOR)) {
            // 存在 creator 字段，则尝试获取 userId
            Long userId = UserThreadLocalUtil.getUserId();
            if (userId != null) {
                // 添加 creator = 当前处理用户 userId
                ReflectUtil.setFieldValue(parameter, SystemConstant.DATA_FIELD_NAME_CREATOR, userId);
            }
        }
        // 判断是否有 create_time 字段
        if (ReflectUtil.hasField(parameter.getClass(), SystemConstant.DATA_FIELD_NAME_CREATE_TIME)) {
            ReflectUtil.setFieldValue(parameter, SystemConstant.DATA_FIELD_NAME_CREATE_TIME, DateUtil.date());
        }
        // 判断是否有 is_deleted 字段
        if (ReflectUtil.hasField(parameter.getClass(), SystemConstant.DATA_FIELD_NAME_IS_DELETED)) {
            ReflectUtil.setFieldValue(parameter, SystemConstant.DATA_FIELD_NAME_IS_DELETED, 0);
        }
    }
}
