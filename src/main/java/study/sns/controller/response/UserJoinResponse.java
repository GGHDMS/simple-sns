package study.sns.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.sns.model.User;
import study.sns.model.UserRole;

@Getter
@AllArgsConstructor
public class UserJoinResponse {
    private Long id;
    private String userName;
    private UserRole userRole;

    public static UserJoinResponse fromUser(User user) {
        return new UserJoinResponse(
                user.getId(),
                user.getUserName(),
                user.getUserRole()
        );
    }
}
