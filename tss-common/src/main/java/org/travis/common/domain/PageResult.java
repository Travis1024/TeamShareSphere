package org.travis.common.domain;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName PageResult
 * @Description 分页结果封装类
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页结果")
public class PageResult<T> {

    @Schema(description = "总条数")
    protected Long total;
    @Schema(description = "总页码数")
    protected Long pages;
    @Schema(description = "当前页数据")
    protected List<T> list;

    /**
     * @MethodName empty
     * @Description 返回空分页结果-1
     * @Author travis-wei
     * @Data 2024/4/21
     * @param total 总条数
     * @param pages	总页数
     * @Return org.travis.common.domain.PageObject<T>
     **/
    public static <T> PageResult<T> empty(Long total, Long pages) {
        return new PageResult<>(total, pages, CollUtil.empty(List.class));
    }

    /**
     * @MethodName empty
     * @Description 返回空分页结果-2
     * @Author travis-wei
     * @Data 2024/4/21
     * @param page	mybatis分页查询结果
     * @Return org.travis.common.domain.PageObject<T>
     **/
    public static <T> PageResult<T> empty(Page<?> page) {
        return new PageResult<>(page.getTotal(), page.getPages(), CollUtil.empty(List.class));
    }


    /**
     * @MethodName of
     * @Description 分页结果封装函数-1
     * @Author travis-wei
     * @Data 2024/4/21
     * @param page mybatis分页查询结果
     * @Return org.travis.common.domain.PageObject<T>
     **/
    public static <T> PageResult<T> of(Page<T> page) {
        if(page == null){
            return new PageResult<>();
        }
        if (CollUtil.isEmpty(page.getRecords())) {
            return empty(page);
        }
        return new PageResult<>(page.getTotal(), page.getPages(), page.getRecords());
    }

    /**
     * @MethodName of
     * @Description 分页结果封装函数-2
     * @Author travis-wei
     * @Data 2024/4/21
     * @param page  mybatis分页查询结果
     * @param mapper   分页结果列表处理函数
     * @Return org.travis.common.domain.PageObject<T>
     **/
    public static <T,R> PageResult<T> of(Page<R> page, Function<R, T> mapper) {
        if(page == null){
            return new PageResult<>();
        }
        if (CollUtil.isEmpty(page.getRecords())) {
            return empty(page);
        }
        return new PageResult<>(page.getTotal(), page.getPages(),
                page.getRecords().stream().map(mapper).collect(Collectors.toList()));
    }

    /**
     * @MethodName of
     * @Description 分页结果封装函数-3
     * @Author travis-wei
     * @Data 2024/4/21
     * @param page	mybatis分页查询结果
     * @param list	手动传入当前页 List 列表
     * @Return org.travis.common.domain.PageObject<T>
     **/
    public static <T> PageResult<T> of(Page<?> page, List<T> list) {
        return new PageResult<>(page.getTotal(), page.getPages(), list);
    }

    /**
     * @MethodName of
     * @Description 分页结果封装函数-4
     * @Author travis-wei
     * @Data 2024/4/21
     * @param page  mybatis分页查询结果
     * @param clazz T 类 -> List(T)
     * @Return org.travis.common.domain.PageObject<T>
     **/
    public static <T, R> PageResult<T> of(Page<R> page, Class<T> clazz) {
        return new PageResult<>(page.getTotal(), page.getPages(), BeanUtil.copyToList(page.getRecords(), clazz));
    }

    /**
     * 判断当前页数据是否为空
     * @return true:空 | false:非空
     */
    @Schema(hidden = true)
    @JsonIgnore
    public boolean isEmpty(){
        return list == null || list.isEmpty();
    }
}
