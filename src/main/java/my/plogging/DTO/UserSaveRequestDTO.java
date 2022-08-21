package my.plogging.DTO;

import lombok.Getter;

@Getter
public class UserSaveRequestDTO {
        Long userId;
        String name;
        String nickName;
        String email;
        String profileURL;
        String address;
        String gender;

        public UserSaveRequestDTO(Long userId, String name, String nickName, String email, String profileURL, String address, String gender) {
                this.userId = userId;
                this.name = name;
                this.nickName = nickName;
                this.email = email;
                this.profileURL = profileURL;
                this.address = address;
                this.gender = gender;
        }
}
