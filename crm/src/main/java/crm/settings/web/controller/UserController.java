package crm.settings.web.controller;

import crm.commons.Utils.DateFormatUtil;
import crm.commons.contants.Contants;
import crm.commons.domain.ReturnObject;
import crm.settings.domain.User;
import crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/settings")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    @RequestMapping("/qx/user/Login.do")
    @ResponseBody
    public Object Login(String username, String password, String check, HttpServletRequest request, HttpSession session, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",username);
        map.put("loginPwd",password);
        User user = userService.queryUserByLoginActAndPwd(map);
        ReturnObject returnObject = new ReturnObject();
        if (user==null){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("账号或密码错误");
        }else {
            String nowData = DateFormatUtil.DateTime(new Date());
            if (nowData.compareTo(user.getExpiretime())>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账户已过期");
            }else if ("0".equals(user.getLockstate())){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账户已锁定");
            }else if (!user.getAllowips().contains(request.getRemoteAddr())){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("用户IP非法");
            }else {
                Cookie c1 = new Cookie("username",username);
                Cookie c2 = new Cookie("password",password);
                if (check.equals("true")){
                    c1.setMaxAge(10*24*60*60);
                    c2.setMaxAge(10*24*60*60);
                }else {
                    c1.setMaxAge(0);
                    c2.setMaxAge(0);                            //cookie设置为0就删除了
                }
                response.addCookie(c1);
                response.addCookie(c2);

                session.setAttribute(Contants.SESSION_USER,user);           //通过session保存用户信息供前台使用
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }
        }
        return returnObject;
    }
    @RequestMapping("/qx/user/logout.do")
    public String Logout(HttpSession session,HttpServletResponse response){
        Cookie c1 = new Cookie("username","1");     //清除cookie
        Cookie c2 = new Cookie("password","1");
        c1.setMaxAge(0);
        c2.setMaxAge(0);
        response.addCookie(c1);
        response.addCookie(c2);

        session.invalidate();                                   //清除session,省服务器内存
        return "redirect:/";                                    //借助springmvc重定向，实际是response.sendRedirect("/crm");
    }
}