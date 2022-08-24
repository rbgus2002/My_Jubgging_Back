package my.plogging.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import my.plogging.domain.Item;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ItemResponseDTO {
    private String itemURL;
    private String name;
    private String information;
    private int price;
    private int stock;

    @Builder
    public ItemResponseDTO(Item item){
        this.itemURL = item.getItemURL();
        this.name = item.getName();
        this.information = item.getInformation();
        this.price = item.getPrice();
        this.stock = item.getStock();
    }

    public Map<String, Object> ItemResponseDTOToMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("itemURL", this.itemURL);
        map.put("name", this.name);
        map.put("information", this.information);
        map.put("price", this.price);
        map.put("stock", this.stock);

        return map;
    }
}
