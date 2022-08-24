package my.plogging.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import my.plogging.domain.User;

@Getter @Setter
public class UserProfileResponseDTO {
    /*
    "profileURL": "~~~"
            "gender": "Male"
            "name": "이진수"
            "email": "dlwlstn0709@uos.ac.kr"
            "roadAddress": "서울특별시 가나구 다라동 1-23"
            "specificAddress": "김치빌라 102호"
            "nickname": "여마"
            "point": 99
    */
    private String profileURL;
    private String gender;
    private String name;
    private String email;
    private String roadAddress;
    private String specificAddress;
    private String nickname;
    private int point;

    @Builder
    public UserProfileResponseDTO(User user){
        this.profileURL = user.getProfileURL();
        this.gender = user.getGender();
        this.name = user.getName();
        this.email = user.getEmail();
        this.roadAddress = user.getRoadAddress();
        this.specificAddress = user.getSpecificAddress();
        this.nickname = user.getNickName();
        this.point = user.getPoint();
    }
}
