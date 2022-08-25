package my.plogging.domain;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    private String information;

    private int price;
    private int stock;

    public void removeStock(int quantity){
        int restStock = this.stock - quantity;
        if(restStock < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        this.stock = restStock;
    }
}
