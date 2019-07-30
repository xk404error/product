package com.xk.product.service.redis.busi;

import com.xk.product.service.redis.model.RankDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RankByRedis {
    @Resource
    RedisTemplate myRedisTemplate;
    private String rankKey="rankKey";
    public RankDO getRankDOByUserId(String userId){
        //stringRedisTemplate.opsForZSet().
        return null;
    }
    public void updateUserSocre(String userId,String socre){
        myRedisTemplate.opsForZSet().add(rankKey,userId,Double.valueOf(socre));
    }

    public RankDO rankUser(String userId){
        RankDO rankDO = new RankDO();
        Long rank = myRedisTemplate.opsForZSet().rank(rankKey, userId);
        rankDO.setRank(String.valueOf(rank));
        Double score = myRedisTemplate.opsForZSet().score(rankKey, userId);
        rankDO.setSocre(String.valueOf(score));
        rankDO.setUserId(userId);
        return rankDO;
    }

}
