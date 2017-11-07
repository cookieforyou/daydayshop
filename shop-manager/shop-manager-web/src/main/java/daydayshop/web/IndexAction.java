package daydayshop.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class IndexAction {

    @RequestMapping("/")
    public String goIndex() {
        return "index";
    }
    @RequestMapping("/{tab}")
    public String tab(@PathVariable("tab") String tab) {
        return tab;
    }
}
