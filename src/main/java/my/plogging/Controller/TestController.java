package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.Service.BoardService;
import my.plogging.Service.UserService;
import my.plogging.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
    private final BoardService boardService;

    @PostMapping("/user/join")
    public Long userJoin(@RequestBody User user){
        return userService.join(user);
    } //임시 -> DTO로 받기
}
