package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.ItemListsResponseDTO;
import my.plogging.Repository.ItemRepository;
import my.plogging.domain.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Map getItemList(){
        List<Item> items = itemRepository.findAll();
        List<Map> targetList = new ArrayList<>();
        for (Item item : items) {
            ItemListsResponseDTO dto = ItemListsResponseDTO.builder()
                    .item(item)
                    .build();
            targetList.add(dto.ItemListsResponseDTOToMap());
        }

        Map<String, Object> itemList = new HashMap<>();
        itemList.put("Results", targetList);

        return itemList;
    }

}
