package org.jiang.shiro.encrypt;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author Li.Linhua
 * @description TODO
 * @Date 2019/7/18
 */
public class MyEncypt {

    public static String encrypt(String password,String salt){
        SimpleHash simpleHash = new SimpleHash("SHA",password,salt,2);
        String encypt = simpleHash.toHex();
        return encypt;
    }
}
