package daydayshop.common.util;

import java.util.Random;

public class IDUtils {

    //生成ItemID
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

    //生成图片名
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);
        return str;
    }
}
