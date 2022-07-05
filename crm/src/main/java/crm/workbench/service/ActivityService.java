package crm.workbench.service;

import crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int insertActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);
    int queryActivityNumByConditionForPage(Map<String,Object> map);

    int deleteById(String[] ids);

    Activity queryActivityById(String id);
}
