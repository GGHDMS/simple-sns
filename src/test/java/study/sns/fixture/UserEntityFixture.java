package study.sns.fixture;

import study.sns.model.entiry.UserEntity;

public class UserEntityFixture {

    public static UserEntity get(String username, String password) {
        UserEntity result = new UserEntity();
        result.setId(1L);
        result.setUserName(username);
        result.setPassword(password);

        return result;
    }
}
