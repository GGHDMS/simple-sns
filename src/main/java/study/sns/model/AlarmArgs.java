package study.sns.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlarmArgs {

    // 알람을 발생시킨 사람
    private Long fromUserId;
    // 현재는 postId;
    private Long targetId;
}
