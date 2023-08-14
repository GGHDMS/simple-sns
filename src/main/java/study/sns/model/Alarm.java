package study.sns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.sns.model.entity.AlarmEntity;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Alarm {
    private Long id;
    private User user;
    private AlarmType alarmType;
    private AlarmArgs alarmArgs;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public static Alarm fromEntity(AlarmEntity entity) {
        return new Alarm(
                entity.getId(),
                User.fromEntity(entity.getUser()),
                entity.getAlarmType(),
                entity.getAlarmArgs(),
                entity.getRegisteredAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt()
        );
    }
}
