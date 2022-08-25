package my.plogging.DTO;

import lombok.Getter;

@Getter
public class HeartRequestDTO {
    private Long customTrashAddressId;
    private Long userId;

    public HeartRequestDTO(Long customTrashAddressId, Long userId) {
        this.customTrashAddressId = customTrashAddressId;
        this.userId = userId;
    }
}
