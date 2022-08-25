package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.OrderRequestDTO;
import my.plogging.Service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public Map order(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.order(orderRequestDTO);
    }
}
