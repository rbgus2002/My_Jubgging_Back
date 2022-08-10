package my.plogging.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PublicTrashAddress {
    @Id @GeneratedValue
    private Long id;

    private String address;
}
