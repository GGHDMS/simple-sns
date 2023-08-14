package study.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.sns.model.entity.LikeEntity;
import study.sns.model.entity.PostEntity;
import study.sns.model.entity.UserEntity;

import java.util.Optional;

public interface LikeEntityRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByUserAndPost(UserEntity user, PostEntity post);

    Long countAllByPost(PostEntity post);

    @Modifying
    @Query("update LikeEntity entity set entity.deletedAt = now() where entity.post = :post")
    void deleteAllByPost(@Param("post") PostEntity post);

//    List<LikeEntity> findAllByPost(PostEntity post);
}
