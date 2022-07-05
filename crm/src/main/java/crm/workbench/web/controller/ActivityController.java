package crm.workbench.web.controller;

import crm.commons.Utils.DateFormatUtil;
import crm.commons.Utils.UUIDUtil;
import crm.commons.contants.Contants;
import crm.commons.domain.ReturnObject;
import crm.settings.domain.User;
import crm.settings.service.UserService;
import crm.workbench.domain.Activity;
import crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.queryAllUser();
        request.setAttribute("UserList",userList);
        return "workbench/activity/index";
    }
    @RequestMapping("/workbench/activity/saveActivity.do")
    @ResponseBody
    public Object save(Activity activity,HttpSession session){
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateby(user.getId());
        activity.setCreatetime(DateFormatUtil.DateTime(new Date()));
        int rows = activityService.insertActivity(activity);

        ReturnObject returnObject = new ReturnObject();
        if (rows>0){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }else {
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("服务器忙，请稍后重试");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/web/controller/queryAllActivity.do")
    @ResponseBody
    public Map<String, Object> queryAllActivity(String name, String owner, String startDate, String endDate, Integer pageNo, Integer pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("ActivityName",name);
        map.put("Owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",pageNo);
        map.put("pageSize",pageSize);
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int count = activityService.queryActivityNumByConditionForPage(map);

        Map<String,Object> result = new HashMap<>();
        result.put("activityList",activityList);
        result.put("count",count);
        return result;
    }
}
