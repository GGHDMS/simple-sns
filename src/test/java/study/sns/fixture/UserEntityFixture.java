package study.sns.fixture;

import study.sns.model.entity.UserEntity;

public class UserEntityFixture {

    public static UserEntity get(String username, String password, Long userId) {
        UserEntity result = new UserEntity();
        result.setId(userId);
        result.setUserName(username);
        result.setPassword(password);

        return result;
    }
}
