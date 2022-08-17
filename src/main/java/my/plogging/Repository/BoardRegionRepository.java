package my.plogging.Repository;

import my.plogging.domain.BoardRegion;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.function.Function;

public interface BoardRegionRepository extends JpaRepository<BoardRegion, Long> {
    BoardRegion findByBoardId(Long BoardId);
}
