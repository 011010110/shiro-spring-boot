package org.jiang.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Component
public class MySessionDao extends EnterpriseCacheSessionDAO {

    private Logger logger = LoggerFactory.getLogger(MySessionDao.class);

    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable serializable = generateSessionId(session);
        assignSessionId(session,serializable);
        redisTemplate.opsForValue().set(serializable.toString(),session);
        logger.info("创建session ,sessionId="+serializable.toString());
        return serializable.toString();
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        Session session = (Session) redisTemplate.opsForValue().get(serializable.toString());
        logger.info("读取session ,sessionId="+serializable);
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        String serializable = (String) session.getId();
        logger.info("更新session ,sessionId="+serializable);
        redisTemplate.opsForValue().set(serializable,session);
    }

    @Override
    public void delete(Session session) {
        String sessionId = (String) session.getId();
        logger.info("删除session ,sessionId="+sessionId);
        redisTemplate.delete(sessionId);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return Collections.EMPTY_SET;
    }

}
