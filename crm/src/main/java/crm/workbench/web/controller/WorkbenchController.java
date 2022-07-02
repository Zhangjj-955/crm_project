package crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WorkbenchController {
    @RequestMapping("/workbench/index.do")
    public String toIndex(){
        return "workbench/index";
    }
    @RequestMapping("/workbench/main.do")
    public String main(){
        return "workbench/main/index";
    }
}
