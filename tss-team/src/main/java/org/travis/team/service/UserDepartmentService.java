package org.travis.team.service;

import java.util.List;
import org.travis.team.entity.UserDepartment;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName UserDepartmentService
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
public interface UserDepartmentService extends IService<UserDepartment>{


    int updateBatch(List<UserDepartment> list);

    int updateBatchSelective(List<UserDepartment> list);

    int batchInsert(List<UserDepartment> list);

    int insertOrUpdate(UserDepartment record);

    int insertOrUpdateSelective(UserDepartment record);

}
