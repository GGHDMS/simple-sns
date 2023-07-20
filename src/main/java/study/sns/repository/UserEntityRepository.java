package study.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.sns.model.entiry.UserEntity;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String userName);
}
