package com.cloud.web.model.redis;/*
package cn.xq.cool.cloud.statistics.config.redis;

import cn.xq.cool.cloud.statistics.utils.RedisSingeUtils;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

*/
/**
 * @BelongsProject: dataAny
 * @BelongsPackage: cn.xq.cool.cloud.statistics.config.redis
 * @Author: kkkk.l
 * @CreateTime: 2022-12-14  11:39
 * @Description: TODO
 * @Version: 1.0
 *//*

@Slf4j
public class RedisClusterClient {
    private static JedisPoolConfig jedisPoolConfig = new JedisPoolConfig(); // 配置信息
    public JedisCluster jedisCluster = null;
    public static Properties properties;
    public static RedisClusterClient redisClusterClient = new RedisClusterClient();
    static {
        InputStream is = RedisSingeUtils.class.getClassLoader().getResourceAsStream("jediscluster.properties");
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public RedisClusterClient() {
        init();
    }

    public static RedisClusterClient getInsance() {
        if (redisClusterClient != null) {
            return redisClusterClient;
        } else {
            redisClusterClient = new RedisClusterClient();
            return redisClusterClient;
        }
    }

    public boolean init() {
        try {
            // 读取配置文件
            String pwd = "hi.cool";
            String[] cNodes = properties.getProperty("nodes").split(",");
            Set<HostAndPort> nodes = new HashSet<HostAndPort>();
            // 分割出集群节点
            for (String node : cNodes) {
                String[] hp = node.split(":");
                nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
            }
            try {
                jedisPoolConfig.setMaxIdle(Integer.valueOf(properties.getProperty("max-idle")));
                jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(properties.getProperty("max-wait")));
                jedisPoolConfig.setMinIdle(0);
                jedisPoolConfig.setBlockWhenExhausted(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            jedisCluster = new JedisCluster(nodes, Integer.valueOf(properties.getProperty("timeout")), Integer.valueOf(properties.getProperty("timeout")),Integer.valueOf(properties.getProperty("retry")),pwd,jedisPoolConfig);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("发生错误：{}",e);
        }
        return false;
    }

}
    */
