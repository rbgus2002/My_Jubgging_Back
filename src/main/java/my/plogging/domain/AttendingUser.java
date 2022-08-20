package my.plogging.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class AttendingUser {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;

    @ManyToOne
    @JoinColumn(name = "user3_id")
    private User user3;

    @ManyToOne
    @JoinColumn(name = "user4_id")
    private User user4;

    @ManyToOne
    @JoinColumn(name = "user5_id")
    private User user5;

    @ManyToOne
    @JoinColumn(name = "user6_id")
    private User user6;

    @ManyToOne
    @JoinColumn(name = "user7_id")
    private User user7;

    @ManyToOne
    @JoinColumn(name = "user8_id")
    private User user8;

    @ManyToOne
    @JoinColumn(name = "user9_id")
    private User user9;

    @ManyToOne
    @JoinColumn(name = "user10_id")
    private User user10;



}
