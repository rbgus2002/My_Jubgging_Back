package my.plogging.DTO;

import lombok.Builder;
import my.plogging.domain.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemListsResponseDTO {
    private String itemURL;
    private String name;
    private int price;
    private int stock;

    @Builder
    public ItemListsResponseDTO(Item item){
        this.itemURL = item.getItemURL();
        this.name = item.getName();
        this.price = item.getPrice();
        this.stock = item.getStock();
    }

    public Map<String, Object> ItemListsResponseDTOToMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("itemURL", this.itemURL);
        map.put("name", this.name);
        map.put("price", this.price);
        map.put("stock", this.stock);

        return map;
    }
}
