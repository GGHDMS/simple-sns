package study.sns.model.entiry;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class UserEntity {

    @Id
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;
}
