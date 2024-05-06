package org.travis.team.service;

import java.util.List;
import org.travis.team.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ClassName DepartmentService
 * @Description DepartmentService
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
public interface DepartmentService extends IService<Department>{


    int updateBatch(List<Department> list);

    int updateBatchSelective(List<Department> list);

    int batchInsert(List<Department> list);

    int insertOrUpdate(Department record);

    int insertOrUpdateSelective(Department record);

}
