package my.plogging.DTO;

import lombok.Getter;
import lombok.Setter;
import my.plogging.domain.CustomTrashAddress;
import my.plogging.domain.User;

@Getter @Setter
public class TrashResponseDTO {
    private Long id;
    private Long userId;
    private String latitude;
    private String longitude;
    private String kind;

    public TrashResponseDTO(CustomTrashAddress customTrashAddress, User user) {
        this.id = customTrashAddress.getId();
        this.userId = user.getId();
        this.latitude = customTrashAddress.getLatitude();
        this.longitude = customTrashAddress.getLongitude();
        this.kind = customTrashAddress.getKind();
    }
}
