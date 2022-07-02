package crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
    @RequestMapping("/")                                    //理论上是http://localhost:8080/crm/,springmvc省略了前面重复的
    public String index(){                                      //这里要public，因为是核心控制器调用这个方法，
        return "index";
    }
}