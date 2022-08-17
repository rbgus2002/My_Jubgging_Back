package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.Service.BoardService;
import my.plogging.Service.UserService;
import my.plogging.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
    private final BoardService boardService;



    @GetMapping("/test")
    public Map<String, Object> test(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("userId", 1);
        map.put("title", "하이");
        map.put("text", "나 숭실대 장재우. 대학원 가실?");

        return map;
    }
}
