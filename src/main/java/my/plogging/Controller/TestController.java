package my.plogging.Controller;

import my.plogging.Entity.Board;
import my.plogging.Entity.Test;
import my.plogging.Repository.TestRepository;
import my.plogging.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private BoardService boardService;

    @RequestMapping("/test")
    public List<String> LogTest(){
        return Arrays.asList("안녕하세요..", "Hello");
    }

    @GetMapping("/board/list")
    @ResponseBody
    public List<Board> boardlist(Model model){
//        model.addAttribute("list", boardService.boardList());
        return boardService.boardList();
    }
}
