package uploadTest;
/*
import daydayshop.common.util.FTPUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class FtpUploadTest {
    @Test
    public void testFtpupload() throws Exception {
        //创建FTPClient客户端对象
        FTPClient ftpClient = new FTPClient();
        //创建FTP连接
        ftpClient.connect("10.31.161.77", 21);
        //FTP连接登录
        ftpClient.login("ftpuser", "ftpusers");
        //创建流读取本地文件
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\2.jpg"));
        //配置上传参数
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置二进制字节流上传
        //上传文件
        ftpClient.storeFile("2.jpg", fileInputStream);
        //关闭连接
        fileInputStream.close();
        ftpClient.logout();
    }

    @Test
    public void testFTPUtils() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\2.jpg"));
        FTPUtils.uploadFile("10.31.161.77", 21, "ftpuser", "ftpusers", "/home/ftpuser/www/images", "/2017/09/11", "hello.jpg", fileInputStream);
    }
}*/
