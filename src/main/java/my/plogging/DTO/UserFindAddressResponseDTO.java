package my.plogging.DTO;

import lombok.Getter;
import lombok.Setter;
import my.plogging.domain.User;

@Getter @Setter
public class UserFindAddressResponseDTO {
    private String address;

    public UserFindAddressResponseDTO(String address) {
        this.address = address;
    }
}
