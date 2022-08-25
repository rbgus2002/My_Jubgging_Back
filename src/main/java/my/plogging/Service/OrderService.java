package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.OrderRequestDTO;
import my.plogging.DTO.OrderResponseDTO;
import my.plogging.Repository.ItemRepository;
import my.plogging.Repository.OrderItemRepository;
import my.plogging.Repository.OrderRepository;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.Item;
import my.plogging.domain.Order;
import my.plogging.domain.OrderItem;
import my.plogging.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;


    public Map printOrders(Long userId){
        List<Order> orders = orderRepository.findByUserId(userId);
        List<Map> targetList = new ArrayList<>();
        for (Order order : orders) {
            OrderItem orderItem = orderItemRepository.findByOrderId(order.getId());
            OrderResponseDTO dto = OrderResponseDTO.builder()
                    .order(order)
                    .orderItem(orderItem)
                    .build();
            targetList.add(dto.OrderResponseDTOToMap());
        }

        Map<String, Object> orderList = new HashMap<>();
        orderList.put("Results", targetList);

        return orderList;
    }

    public Map order(OrderRequestDTO orderRequestDTO){
        User user = userRepository.findById(orderRequestDTO.getUserId()).get();
        Item item = itemRepository.findById(orderRequestDTO.getItemId()).get();
        int count = orderRequestDTO.getCount();
        int price = item.getPrice()*count;
        user.spendPoint(item, count, price);
        Order order = new Order(user);
        OrderItem orderItem = new OrderItem(item, order, price ,count);

        orderRepository.save(order);
        orderItemRepository.save(orderItem);

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", order.getId());

        return map;
    }
}
