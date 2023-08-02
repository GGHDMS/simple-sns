package study.sns.fixture;

import study.sns.model.entity.PostEntity;
import study.sns.model.entity.UserEntity;

public class PostEntityFixture {

    public static PostEntity get(String username, Long postId, Long userId) {
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setUserName(username);
        PostEntity result = new PostEntity();
        result.setUser(user);
        result.setId(postId);

        return result;
    }
}
