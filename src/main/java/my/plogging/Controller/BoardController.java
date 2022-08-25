package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardResponseDTO;
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
        return boardService.saveBoard(dto);
    }

    @GetMapping("")
    public Map boardPrints(@RequestParam("regionName") String regionName){
        return boardService.getBoardLists(regionName);
    }

    @GetMapping("/spec")
    public BoardResponseDTO boardPrint(@RequestParam("boardId") Long boardId){
        return boardService.getBoard(boardId);
    }

    @DeleteMapping("")
    public Map boardDelete(@RequestParam("boardId") Long boardId){
        return boardService.deleteBoard(boardId);
    }

    @PutMapping("")
    public Map boardUpdate(@RequestParam("boardId") Long boardId, @RequestBody BoardSaveRequestDTO dto){
        return boardService.updateBoard(boardId, dto);
    }

    @GetMapping("/attend")
    public Map boardAttendingNumPlus(@RequestParam Long boardId, @RequestParam Long userId){
        return boardService.nowAttendingNumPlus(boardId, userId);
    }


}