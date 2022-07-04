package crm.workbench.mapper;

import crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {
    int insertActivity(Activity activity);
    List<Activity> selectActivityByConditionForPage(Map<String,Object> map);

    int selectActivityCountByConditionForPage(Map<String,Object> map);

    int deleteById(String[] ids);
}