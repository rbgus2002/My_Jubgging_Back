package my.plogging.DTO;

import lombok.Getter;
import lombok.Setter;
import my.plogging.domain.CustomTrashAddress;
import my.plogging.domain.User;
import org.springframework.context.annotation.Lazy;

@Getter @Setter
public class TrashResponseDTO {
    private Long id;
    private Long userId;
    private String latitude;
    private String longitude;
    private String kind;
    private String profileURL;
    private String nickName;
    private int heart;
    private int addPlaceNum;

    public TrashResponseDTO(@Lazy CustomTrashAddress customTrashAddress, @Lazy User user) {
        this.id = customTrashAddress.getId();
        this.userId = user.getId();
        this.latitude = customTrashAddress.getLatitude();
        this.longitude = customTrashAddress.getLongitude();
        this.kind = customTrashAddress.getKind();
        this.profileURL = user.getProfileURL();
        this.nickName = user.getNickName();
        this.heart = user.getHeart();
        this.addPlaceNum = user.getAddPlaceNum();
    }
}
