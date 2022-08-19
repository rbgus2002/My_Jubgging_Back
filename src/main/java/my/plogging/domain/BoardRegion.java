package my.plogging.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class BoardRegion {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String region1;
    private String region2;
    private String region3;

    @Builder
    public BoardRegion(Board board, String region1, String region2, String region3) {
        this.board = board;
        this.region1 = region1;
        this.region2 = region2;
        this.region3 = region3;
    }

    public void updateRegion(String region1, String region2, String region3){
        this.region1 = region1;
        this.region2 = region2;
        this.region3 = region3;
    }
}
