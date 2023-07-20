package study.sns.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.sns.exception.ErrorCode;
import study.sns.exception.SnsApplicationException;
import study.sns.model.User;
import study.sns.model.entiry.UserEntity;
import study.sns.repository.UserEntityRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;

    public User join(String username, String password) {
        // 회원가입하려는 userName으로 회원가입된 user가 있는지
         userEntityRepository.findByUserName(username).ifPresent(it -> {
            throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, String.format("%s is duplicated", username));
        });

        // 회원가입 진행 = user를 등록
        return User.fromEntity(userEntityRepository.save(UserEntity.of(username, password)));
    }

    // TODO: implement
    public String login(String username, String password) {
        // 회원가입 여부 체크
        UserEntity userEntity = userEntityRepository.findByUserName(username).orElseThrow(() -> new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, ""));

        // 비밀번호 체크
        if (!userEntity.getPassword().equals(password)) {
            throw new SnsApplicationException(ErrorCode.DUPLICATED_USER_NAME, "");
        }

        // 토큰 생성
        return "";
    }
}
