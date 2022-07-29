package my.plogging.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {
    @RequestMapping("/test")
    public List<String> LogTest(){
        return Arrays.asList("안녕하세요..", "Hello");
    }

}
