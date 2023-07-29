package study.sns.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.sns.exception.ErrorCode;
import study.sns.exception.SnsApplicationException;
import study.sns.model.entiry.PostEntity;
import study.sns.model.entiry.UserEntity;
import study.sns.repository.PostEntityRepository;
import study.sns.repository.UserEntityRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostEntityRepository postEntityRepository;
    private final UserEntityRepository userEntityRepository;


    @Transactional
    public void create(String title, String body, String userName) {

        //user find
        UserEntity userEntity = userEntityRepository.findByUserName(userName)
                .orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", userName)));

        //post save
        postEntityRepository.save(PostEntity.of(title, body, userEntity));
    }
}
