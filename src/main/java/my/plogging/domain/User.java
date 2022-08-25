package my.plogging.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id @Column(name = "user_id")
    private Long id;

    private String name;
    private String nickName;

    private String email;
    private String profileURL;

    private int point;

    private String gender;

    private String roadAddress;
    private String specificAddress;
    private String dong;

    private int addPlaceNum;
    private int heart;

    @Builder
    public User(Long id, String name, String nickName, String gender, String roadAddress, String specificAddress, String dong, String email, String profileURL){
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.profileURL = profileURL;
        this.point = 0;
        this.gender = gender;
        this.roadAddress = roadAddress;
        this.specificAddress = specificAddress;
        this.dong = dong;
        this.addPlaceNum = 0;
        this.heart = 0;
    }

    public void spendPoint(Item item, int count, int spendPoint){
        int restPoint = this.point - spendPoint;
        if(restPoint<0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        item.removeStock(count);
        this.point = restPoint;
    }


}
