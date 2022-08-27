package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.Service.BoardService;
import my.plogging.Service.ExcelPOIHelper;
import my.plogging.Service.UserService;
import my.plogging.domain.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
    private final BoardService boardService;
    private final EntityManager em;
    private final ExcelPOIHelper excelPOIHelpercel;

    @Transactional
    @GetMapping("/drop")
    public void dropTable(){
        String query = "DROP TABLE board_region, comment, board, attending_user";
        int i = em.createNativeQuery(query)
                .executeUpdate();
        System.out.println(i);
    }

    @GetMapping("/test")
    public Map<String, Object> test(){
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("userId", 1);
        map.put("title", "하이");
        map.put("text", "나 서울시립대 이진수. 대학원 가실?");

        return map;
    }

    @GetMapping("/trash")
    public void saveTrash() throws IOException {
        excelPOIHelpercel.readExcelAndSave();
    }
}
