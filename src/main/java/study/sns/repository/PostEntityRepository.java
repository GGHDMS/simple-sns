package study.sns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import study.sns.model.entity.PostEntity;
import study.sns.model.entity.UserEntity;

public interface PostEntityRepository extends JpaRepository<PostEntity, Long> {
    Page<PostEntity> findAllByUser(UserEntity entity, Pageable pageable);
}
