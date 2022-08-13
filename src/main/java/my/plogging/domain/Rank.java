package my.plogging.domain;

import javax.persistence.*;

@Entity
@Table(name = "RANKS")
public class Rank {
    @Id @GeneratedValue
    @Column(name = "rank_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "user_id"),
            @JoinColumn(name = "date")
    })
    private UserRecord userRecord;
}
