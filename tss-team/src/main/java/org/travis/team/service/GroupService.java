package org.travis.team.service;

import java.util.List;
import org.travis.team.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;
    /**
 * @ClassName GroupService
 * @Description TODO
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/4/30
 */
public interface GroupService extends IService<Group>{


    int updateBatch(List<Group> list);

    int updateBatchSelective(List<Group> list);

    int batchInsert(List<Group> list);

    int insertOrUpdate(Group record);

    int insertOrUpdateSelective(Group record);

}
