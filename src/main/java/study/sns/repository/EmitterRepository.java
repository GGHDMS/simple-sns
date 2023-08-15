package study.sns.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class EmitterRepository {

    private Map<String, SseEmitter> sseEmitterMap = new HashMap<>();

    public SseEmitter save(Long userId, SseEmitter sseEmitter) {
        final String key = getKey(userId);
        sseEmitterMap.put(key, sseEmitter);
        log.info("Set sseEmitter {}", userId);
        return sseEmitter;
    }

    public Optional<SseEmitter> get(Long userId) {
        final String key = getKey(userId);
        SseEmitter sseEmitter = sseEmitterMap.get(key);
        log.info("get sseEmitter {}", userId);
        return Optional.ofNullable(sseEmitter);
    }

    public void delete(Long userId) {
        sseEmitterMap.remove(getKey(userId));
    }

    private String getKey(Long userId) {
        return "Emitter:UID" + userId;
    }
}
