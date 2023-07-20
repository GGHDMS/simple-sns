package study.sns.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.sns.controller.request.UserJoinRequest;
import study.sns.controller.response.Response;
import study.sns.controller.response.UserJoinResponse;
import study.sns.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        UserJoinResponse response = UserJoinResponse.fromUser(userService.join(request.getUserName(), request.getPassword()));
        return Response.success(response);
    }
}
