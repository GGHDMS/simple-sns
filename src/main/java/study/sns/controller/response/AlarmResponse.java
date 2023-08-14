package study.sns.controller.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import study.sns.model.Alarm;
import study.sns.model.AlarmArgs;
import study.sns.model.AlarmType;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class AlarmResponse {
    private Long id;
    private AlarmType alarmType;
    private AlarmArgs alarmArgs;
    private String text;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public static AlarmResponse fromAlarm(Alarm alarm) {
        return new AlarmResponse(
                alarm.getId(),
                alarm.getAlarmType(),
                alarm.getAlarmArgs(),
                alarm.getAlarmType().getAlarmText(),
                alarm.getRegisteredAt(),
                alarm.getUpdatedAt(),
                alarm.getDeletedAt()
        );
    }}
