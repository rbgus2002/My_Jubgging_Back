package my.plogging.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Item {
    @Id @GeneratedValue
    private Long id;

    private String itemURL;
    private String name;
    private int price;
    private int stock;
}
