package study.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.sns.model.entity.LikeEntity;
import study.sns.model.entity.PostEntity;
import study.sns.model.entity.UserEntity;

import java.util.Optional;

public interface LikeEntityRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByUserAndPost(UserEntity user, PostEntity post);

    Integer countAllByPost(PostEntity post);

//    List<LikeEntity> findAllByPost(PostEntity post);

}
