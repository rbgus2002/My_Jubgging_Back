package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {


}
