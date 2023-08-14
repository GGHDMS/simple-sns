package study.sns.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import study.sns.model.entity.AlarmEntity;
import study.sns.model.entity.UserEntity;

public interface AlarmEntityRepository extends JpaRepository<AlarmEntity, Long> {
    Page<AlarmEntity> findAllByUser(UserEntity user, Pageable pageable);
}
