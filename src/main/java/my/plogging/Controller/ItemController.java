package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.Service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ItemController {
    private final ItemService itemService;

    @GetMapping()
    public Map itemPrint(){
        return itemService.getItemList();
    }

}
