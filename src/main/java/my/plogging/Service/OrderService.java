package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.OrderRequestDTO;
import my.plogging.Repository.ItemRepository;
import my.plogging.Repository.OrderItemRepository;
import my.plogging.Repository.OrderRepository;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.Item;
import my.plogging.domain.Order;
import my.plogging.domain.OrderItem;
import my.plogging.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    public Map order(OrderRequestDTO orderRequestDTO){
        User user = userRepository.findById(orderRequestDTO.getUserId()).get();
        Item item = itemRepository.findById(orderRequestDTO.getItemId()).get();
        int count = orderRequestDTO.getCount();
        item.removeStock(count);
        Order order = new Order(user);
        OrderItem orderItem = new OrderItem(item, order, item.getPrice()*count ,count);

        orderRepository.save(order);
        orderItemRepository.save(orderItem);

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", order.getId());

        return map;
    }
}
