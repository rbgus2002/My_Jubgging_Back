package my.plogging.Repository;

import my.plogging.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/*
JpaRepository 상속받고 Generic에는 Class type과 PK의 type 넣기~!
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
