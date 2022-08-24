package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.ItemResponseDTO;
import my.plogging.Service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ItemController {
    private final ItemService itemService;

    @GetMapping()
    public Map itemShopPrint(){
        return itemService.getItemList();
    }

    @GetMapping("/items/{itemId}")
    public ItemResponseDTO itemPrint(@PathVariable Long itemId){
        return itemService.getItem(itemId);
    }
}
