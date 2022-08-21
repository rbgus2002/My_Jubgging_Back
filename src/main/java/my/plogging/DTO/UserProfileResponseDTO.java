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
            "nickname": "여마"
            "point": "99"
    */
    private String profileURL;
    private String gender;
    private String name;
    private String email;
    private String nickname;
    private int point;

    @Builder
    public UserProfileResponseDTO(User user){
        this.profileURL = user.getProfileURL();
        this.gender = user.getGender();
        this.name = user.getName();
        this.email = user.getEmail();
        this.nickname = user.getNickName();
        this.point = user.getPoint();
    }
}
