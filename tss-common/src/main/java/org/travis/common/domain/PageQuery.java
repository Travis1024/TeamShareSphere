package org.travis.common.domain;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.travis.common.constants.SystemConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

/**
 * @ClassName PageQuery
 * @Description 分页查询请求参数「父类」
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Data
@Schema(description = "分页查询请求参数")
@Accessors(chain = true)
public class PageQuery {

    public static final Integer DEFAULT_PAGE_SIZE = 20;
    public static final Integer DEFAULT_PAGE_NUM = 1;

    @Schema(description = "页码", example = "1")
    @Min(value = 1, message = "页码不能小于1")
    private Integer pageNo = DEFAULT_PAGE_NUM;

    @Schema(description = "每页大小", example = "10")
    @Min(value = 1, message = "每页查询数量不能小于1")
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    @Schema(description = "排序字段", example = "id")
    private String sortBy;

    @Schema(description = "是否升序", example = "true")
    private Boolean isAsc = true;


    /**
     * @MethodName from
     * @Description 起始条数
     * @Author travis-wei
     * @Data 2024/4/21
     * @Return int
     **/
    public int from(){
        return (pageNo - 1) * pageSize;
    }

    /**
     * @MethodName toMpPage
     * @Description 将「分页查询请求参数」转为「Page」参数
     * @Author travis-wei
     * @Data 2024/4/21
     * @param orderItems 排序参数列表
     * @Return com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>
     **/
    public <T> Page<T> toMpPage(OrderItem ... orderItems) {
        // 1.初始化 Page 参数
        Page<T> page = new Page<>(pageNo, pageSize);

        // 2.判断是否已经手动指定排序方式（后端指定）
        if (orderItems != null && orderItems.length > 0) {
            for (OrderItem orderItem : orderItems) {
                page.addOrder(orderItem);
            }
            return page;
        }
        // 3.判断前端是否有排序字段（前端指定）
        if (StrUtil.isNotEmpty(sortBy)){
            OrderItem orderItem = new OrderItem();
            orderItem.setAsc(isAsc);
            orderItem.setColumn(sortBy);
            page.addOrder(orderItem);
        }
        return page;
    }

    /**
     * @MethodName toMpPage
     * @Description 将「分页查询请求参数」转为「Page」参数（设置默认排序方式）
     * @Author travis-wei
     * @Data 2024/4/21
     * @param defaultSortBy 默认排序字段
     * @param isAsc 是否增序排序
     * @Return com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>
     **/
    public <T> Page<T> toMpPage(String defaultSortBy, boolean isAsc) {
        // 1.判断前端是否有排序字段，如果存在，替换掉默认排序字段
        if (StrUtil.isEmpty(sortBy)){
            sortBy = defaultSortBy;
            this.isAsc = isAsc;
        }
        // 2.包装 Page 参数
        Page<T> page = new Page<>(pageNo, pageSize);
        OrderItem orderItem = new OrderItem();
        orderItem.setAsc(this.isAsc);
        orderItem.setColumn(sortBy);
        page.addOrder(orderItem);
        return page;
    }

    /**
     * @MethodName toMpPageDefaultSortByCreateTimeDesc
     * @Description 设置为根据数据库中记录创建时间，倒序排序
     * @Author travis-wei
     * @Data 2024/4/21
     * @Return com.baomidou.mybatisplus.extension.plugins.pagination.Page<T>
     **/
    public <T> Page<T> toMpPageDefaultSortByCreateTimeDesc() {
        return toMpPage(SystemConstant.DATA_FIELD_NAME_CREATE_TIME, false);
    }
}
