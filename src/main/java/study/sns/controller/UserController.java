package study.sns.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import study.sns.controller.request.UserJoinRequest;
import study.sns.controller.request.UserLoginRequest;
import study.sns.controller.response.AlarmResponse;
import study.sns.controller.response.Response;
import study.sns.controller.response.UserJoinResponse;
import study.sns.controller.response.UserLoginResponse;
import study.sns.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        UserJoinResponse response = UserJoinResponse.fromUser(userService.join(request.getName(), request.getPassword()));
        return Response.success(response);
    }

    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        System.out.println(request.getName() + " " + request.getPassword());
        String token = userService.login(request.getName(), request.getPassword());
        return Response.success(new UserLoginResponse(token));
    }

    @GetMapping("/alarm")
    public Response<Page<AlarmResponse>> alarm(Pageable pageable, Authentication authentication) {
        return Response.success(userService.alarmList(authentication.getName(), pageable).map(AlarmResponse::fromAlarm));
    }
}
