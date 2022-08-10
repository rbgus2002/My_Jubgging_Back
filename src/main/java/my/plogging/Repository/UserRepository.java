package my.plogging.Repository;

import lombok.RequiredArgsConstructor;
import my.plogging.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save (User user){
        System.out.println("hello");
        em.persist(user);
    }

    public User findOne(Long userId) {
        return em.find(User.class, userId);
    }


}
