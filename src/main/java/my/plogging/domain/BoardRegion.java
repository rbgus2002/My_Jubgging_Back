package my.plogging.domain;

import javax.persistence.*;

@Entity
public class BoardRegion {
    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String region1;
    private String region2;
    private String region3;
}
