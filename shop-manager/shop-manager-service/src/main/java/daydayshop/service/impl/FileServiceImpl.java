package daydayshop.service.impl;

import daydayshop.common.util.FTPUtils;
import daydayshop.common.util.IDUtils;
import daydayshop.common.util.Prop;
import daydayshop.common.util.PropKit;
import daydayshop.service.FileService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Map<String, Object> uploadImages(MultipartFile multipartFile) {

        //获取FTP配置文件对象
        Prop FtpProperties = PropKit.use("ftp.properties");
        //读取FTP配置文件信息
        String host = FtpProperties.get("ftp.address");
        int port = FtpProperties.getInt("ftp.port");
        String username = FtpProperties.get("ftp.username");
        String password = FtpProperties.get("ftp.password");
        String basePath = FtpProperties.get("ftp.basePath");
        //使用日期时间类DateTime生成格式化的日期文件路径
        String filePath = new DateTime().toString("/yyyy/MM/dd");
        //获取原来的文件名，包括扩展名
        String original = multipartFile.getOriginalFilename();
        //截取出扩展名
        String fileType = original.substring(original.lastIndexOf("."));
        //使用自定义工具类产生新的文件名，只产生了文件名，未产生扩展名
        String fileName = IDUtils.genImageName();
        //拼接出新的文件名+扩展名
        fileName += fileType;

        //获取上传图片输入流对象
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //使用上传文件工具类上传图片
        boolean boo = FTPUtils.uploadFile(host, port, username, password, basePath, filePath, fileName, inputStream);
        Map<String, Object> map = new HashMap<>();
        if (boo) {
            map.put("state", "SUCCESS");
            map.put("original", original);
            map.put("size", multipartFile.getSize());
            map.put("title", fileName);
            map.put("type", fileType);
            map.put("url", filePath + "/" + fileName);
        }

        return map;
    }
}
