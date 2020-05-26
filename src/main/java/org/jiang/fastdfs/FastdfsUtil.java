package org.jiang.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;

import java.io.IOException;

/**
 * @Description
 * @Author li.linhua
 * @Date 2020/3/26
 * @Version 1.0
 */
public class FastdfsUtil {

    public static void init(){
        try {
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}
