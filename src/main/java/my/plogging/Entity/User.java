package my.plogging.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {
    

    @Id//Primary key
    //프로젝트에서 연결된db의 넘버링 전략을 따라간다.  -> 오라클이나 mysql에 따라 바뀐다는 뜻이다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 시퀀스, auto_increment


    @Column(nullable = false, length = 30)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // -> 비밀번호를 암호화 하기위해서 해쉬를 사용해야 한다.
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column
    private String picture;

}
