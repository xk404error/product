package com.xk.product.action.redisTest;

import com.xk.product.service.redis.busi.RankByRedis;
import com.xk.product.service.redis.model.RankDO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class RedisTestController {
    @Resource
    RankByRedis rankByRedis;

    @GetMapping("/updateUser")
    public void updateUser( String userId, String socre){
        rankByRedis.updateUserSocre(userId,socre);
    }

    @GetMapping("rankUser")
    public RankDO rankUser(String userId){
        return rankByRedis.rankUser(userId);
    }
}
