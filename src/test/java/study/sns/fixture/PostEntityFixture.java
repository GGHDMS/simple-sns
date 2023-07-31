package study.sns.fixture;

import study.sns.model.entiry.PostEntity;
import study.sns.model.entiry.UserEntity;

public class PostEntityFixture {

    public static PostEntity get(String username, Long postId) {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUserName(username);

        PostEntity result = new PostEntity();
        result.setUser(user);
        result.setId(postId);

        return result;
    }
}
