package my.plogging.Controller;


import lombok.RequiredArgsConstructor;
import my.plogging.DTO.UserFindAddressResponseDTO;
import my.plogging.Service.UserService;
import my.plogging.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public Map userJoin(@RequestBody User user){
        return userService.join(user);
    } //임시 -> DTO로 받기

    @GetMapping("/{userId}/address")
    public UserFindAddressResponseDTO findUserAddress(@PathVariable Long userId){
        return userService.findUserAddress(userId);
    }
}
