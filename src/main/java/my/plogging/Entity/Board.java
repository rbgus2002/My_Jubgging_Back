package my.plogging.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Table 이름과 일치 시키기
//Entitiy Annotation 달아주면 JPA가 읽어들임
@Entity
// Data Annotaion이 Lombok 실행시켜 자동으로 getter, setter 만들어줌!
@Data
public class Board {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;
}
