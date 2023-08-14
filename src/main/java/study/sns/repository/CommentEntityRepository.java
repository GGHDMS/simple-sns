package study.sns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.sns.model.entity.CommentEntity;
import study.sns.model.entity.PostEntity;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, Long> {
    Page<CommentEntity> findAllByPost(PostEntity post, Pageable pageable);

    @Modifying
    @Query("update CommentEntity entity set entity.deletedAt = now() where entity.post = :post")
    void deleteAllByPost(@Param("post") PostEntity post);
}
