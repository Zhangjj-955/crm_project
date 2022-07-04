package crm.workbench.service;

import crm.workbench.domain.Activity;
import crm.workbench.mapper.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService{
    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public int insertActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    @Override
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    @Override
    public int queryActivityNumByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityCountByConditionForPage(map);
    }

    @Override
    public int deleteById(String[] ids) {
        return activityMapper.deleteById(ids);
    }
}
