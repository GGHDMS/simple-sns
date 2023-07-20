package study.sns.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sns.exception.SnsApplicationException;
import study.sns.model.User;
import study.sns.model.entiry.UserEntity;
import study.sns.repository.UserEntityRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    // TODO: implement
    public User join(String username, String password) {
        // 회원가입하려는 userName으로 회원가입된 user가 있는지
        Optional<UserEntity> userEntity = userEntityRepository.findByUserName(username);

        // 회원가입 진행 = user를 등록
        userEntityRepository.save(new UserEntity());

        return new User();
    }

    // TODO: implement
    public String login(String username, String password) {
        // 회원가입 여부 체크
        UserEntity userEntity = userEntityRepository.findByUserName(username).orElseThrow(SnsApplicationException::new);

        // 비밀번호 체크
        if (!userEntity.getPassword().equals(password)) {
            throw new SnsApplicationException();
        }

        // 토큰 생성
        return "";
    }
}
