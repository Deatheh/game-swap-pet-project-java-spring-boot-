package petproject.gameswap.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RefreshTokenService {

    @Value("${jwt.refresh.expiration}")
    private long refreshExpiration;

    private final RedisTemplate<String, String> stringRedisTemplate;

    public RefreshTokenService(RedisTemplate<String, String> stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void save(Long userId, String refreshToken){
        String key = "refresh:token:" + userId;
        stringRedisTemplate.opsForValue().set(key, refreshToken, refreshExpiration, TimeUnit.MILLISECONDS);
    }

    public boolean isValid(Long userId, String refreshToken){
        String key = "refresh:token:" + userId;
        String storedToken = stringRedisTemplate.opsForValue().get(key);
        return refreshToken.equals(storedToken);
    }

    public void revoke(Long userId){
        String key = "refresh:token:" + userId;
        stringRedisTemplate.delete(key);
    }

    public void revokeAll(Long userId){
        String pattern = "refresh:token:" + userId + "*";
        Set<String> keys = stringRedisTemplate.keys(pattern);

        if (keys != null && !keys.isEmpty()) {
            stringRedisTemplate.delete(keys);
        }
    }
}
