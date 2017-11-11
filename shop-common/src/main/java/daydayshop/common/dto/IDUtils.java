package daydayshop.common.dto;

import java.util.Random;

public class IDUtils {

    public static long getItemId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end);
        Long id = new Long(str);
        return id;
    }
}
