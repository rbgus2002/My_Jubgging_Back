package my.plogging.Controller;


import lombok.RequiredArgsConstructor;
import my.plogging.DTO.UserFindAddressResponseDTO;
import my.plogging.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}/address")
    public UserFindAddressResponseDTO findUserAddress(@PathVariable Long userId){
        return userService.findUserAddress(userId);
    }

}
