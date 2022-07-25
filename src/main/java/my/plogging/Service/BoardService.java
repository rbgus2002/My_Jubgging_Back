package my.plogging.Service;

import my.plogging.Entity.Board;
import my.plogging.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    /*
    @Autowired가 DI를 통해 Bin에서 관리해줌!
    인터페이스라 객체 못만드는데 그냥 처리가 되나봄 신기하네
     */
    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board){
        boardRepository.save(board);
    }

    public List<Board> boardList(){

        return boardRepository.findAll();
    }

    public void deleteBoard(Integer id){
        boardRepository.deleteById(id);
    }
}
