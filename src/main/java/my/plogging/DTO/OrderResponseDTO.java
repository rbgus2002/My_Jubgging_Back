package my.plogging.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import my.plogging.domain.Order;
import my.plogging.domain.OrderItem;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class OrderResponseDTO {
    private LocalDateTime orderDate;
    private String itemURL;
    private String name;
    private int orderPrice;
    private int count;

    @Builder
    public OrderResponseDTO(Order order, OrderItem orderItem){
        this.orderDate = order.getOrderDate();
        this.itemURL = orderItem.getItem().getItemURL();
        this.name = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();
    }

    public Map<String, Object> OrderResponseDTOToMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("orderDate", this.orderDate);
        map.put("itemURL", this.itemURL);
        map.put("name", this.name);
        map.put("orderPrice", this.orderPrice);
        map.put("count", this.count);

        return map;
    }
}
