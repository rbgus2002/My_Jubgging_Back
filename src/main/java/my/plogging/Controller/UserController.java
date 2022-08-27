package my.plogging.Controller;


import lombok.RequiredArgsConstructor;
import my.plogging.DTO.*;
import my.plogging.Service.OrderService;
import my.plogging.Service.UserService;
import my.plogging.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/{userId}/profiles")
    public UserProfileResponseDTO getProfile(@PathVariable Long userId){
        return userService.getUserProfile(userId);
    }

    @PostMapping("/member")
    public Map userSave(@RequestBody UserSaveRequestDTO dto){
        return userService.saveUser(dto);
    }

    @GetMapping("/member")
    public Map userCheck(@RequestParam Long userId) {
        return userService.checkUser(userId);
    }

    @GetMapping("/signup")
    public Map nickNameCheck(@RequestParam String nickName) {
        return userService.checkNickName(nickName);
    }

    @GetMapping("/{userId}/orders")
    public Map printOrders(@PathVariable Long userId){
        return orderService.printOrders(userId);
    }
}
