package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.Service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public Map boardSave(@RequestBody BoardSaveRequestDTO dto){
        Long boardId = boardService.boardSave(dto);

        // variable to json (map)
        Map<String, Long> map = new HashMap<>();
        map.put("boardId", boardId);
        return map;
    }
}
