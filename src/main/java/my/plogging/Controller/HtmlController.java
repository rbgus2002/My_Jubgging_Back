package my.plogging.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    @GetMapping("/addr")
        public String webView() {
            return "daum.html";
    }
}
