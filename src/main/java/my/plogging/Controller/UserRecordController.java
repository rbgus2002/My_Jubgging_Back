package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.UserRecordResponseDTO;
import my.plogging.DTO.UserRecordSaveRequestDTO;
import my.plogging.Service.UserRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/record")
public class UserRecordController {
    private final UserRecordService userRecordService;


    @PostMapping("")
    public Map userRecordSave(@RequestBody UserRecordSaveRequestDTO dto){
        return userRecordService.saveUserRecord(dto);
    }

    @GetMapping("")
    public UserRecordResponseDTO userRecordPrint(@RequestParam Long userId, @RequestParam String date){
        return userRecordService.getUserRecord(userId, date);
    }
}
