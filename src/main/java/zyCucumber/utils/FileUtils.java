package zyCucumber.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * 文件处理工具类
 */
public class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class);

    /**
     * 读取Yaml文件
     * @param fileName 文件名称
     * @return 读取后的内容
     */
    public static Map<String,Object> getResourceMap(String fileName){
        Yaml yaml = new Yaml();
        Object o = null;
        try {
            o = yaml.load(getFileStream(fileName));
        }catch (Exception e){
            logger.error("读取yaml文件失败");
            throw  new RuntimeException(e.getMessage());
        }
        return (Map<String,Object>) o;
    }

    /**
     * 将文件转化为流的形式
     * @param fileName 文件名称
     * @return 对应文件流
     */
    public  static InputStream getFileStream(String fileName) {
        logger.info("需要转换为流的文件名称:" + fileName);
         InputStream is = null;
        try {
            is = new FileInputStream(getFileAbsolutePath(fileName));
        } catch (FileNotFoundException e) {
            logger.error("文件转换为文件流失败!" + e.getMessage());
            throw new RuntimeException("文件转换为文件流失败!");
        }
        return  is;
    }

    /**
     * 获取文件的绝对路径
     * @param fileName 文件名称
     * @return 对应文件的绝对路径
     */
    public  static String getFileAbsolutePath(String fileName) {
        if(StringUtils.isBlank(fileName)) {
            return null;
        }
        URL url = FileUtils.class.getClassLoader().getResource(fileName);
        if(url != null ) {
            try {
                return  url.toURI().getPath();
            } catch (URISyntaxException e) {
                logger.error("获取文件绝对路径异常" + e.getMessage());
                return null;
            }
        }
        logger.error("获取文件绝对路径为空");
        return  null;
    }


}
