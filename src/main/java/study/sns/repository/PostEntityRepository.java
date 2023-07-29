package study.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.sns.model.entiry.PostEntity;

public interface PostEntityRepository extends JpaRepository<PostEntity, Long> {
}
