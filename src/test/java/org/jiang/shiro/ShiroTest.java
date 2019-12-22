package org.jiang.shiro;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.jiang.shiro.encrypt.MyEncypt;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Li.Linhua
 * @description TODO
 * @Date 2019/7/17
 */
public class ShiroTest {

    private Logger logger = LoggerFactory.getLogger(ShiroTest.class);
    /**
     * @description shiro 密码加密测试
     * @param
     * @return
     * @author Li.Linhua
     * @date 2019/7/17
     */
    @Test
    public void haseServerTest(){
        DefaultHashService hashService = new DefaultHashService();//默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");//设置加密算法
        hashService.setPrivateSalt(new SimpleByteSource("123"));//私盐
        hashService.setGeneratePublicSalt(true);//是否生成公盐，默认false，没有传公盐，则生成公盐
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐
        hashService.setHashIterations(2);//生成hash值的迭代次数
        HashRequest hashRequest = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("123456"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(hashRequest).toHex();
        logger.info("password:123456,salt:123,privateSalt:123,hex="+hex);
    }

    @Test
    public void hashTest(){
        String password = "222222";
        String salt = "root7fca63477993c5741c70643215e5f88a";
        String encrypt = MyEncypt.encrypt(password, salt);
        System.out.println(encrypt);//
    }


}
