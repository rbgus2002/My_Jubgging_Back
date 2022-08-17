package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.Service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public Map boardSave(@RequestBody BoardSaveRequestDTO dto){
        Long boardId = boardService.saveBoard(dto);

        // variable to json (map)
        Map<String, Long> map = new HashMap<>();
        map.put("boardId", boardId);

        return map;
    }

    @GetMapping("")
    public List boardPrints(@RequestParam("regionName") String regionName){
        return boardService.getBoardLists(regionName);
    }


}
