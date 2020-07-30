package per.guzx.pri_diary.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.exception.CommonException;
import per.guzx.pri_diary.pojo.PdDiary;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.UUID;

@Component
public class FileUtil {

    @Value("${spring.servlet.multipart.location}")
    public String absolt;

    @Autowired
    Environment environment;

    public String uploadFile(Part detailPhoto, PdDiary diary) {
        String filename = detailPhoto.getSubmittedFileName();
        String port = environment.getProperty("local.server.port");
        String address = "";
        try {
            address = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String prefix = "http://" + address + ":" + port + "/File";
        String suffix = "";
        if (filename.lastIndexOf(".") != -1) {
            suffix = filename.substring(filename.lastIndexOf(".") + 1);
        }

        String newFileName = UUID.randomUUID().toString().substring(0, 16).replace("-", "") + "." + suffix;
        // 文件保存路径
        String saveDest = absolt + "/" + DateUtil.getDateStamp() + "/" + diary.getUserId() + "/" + diary.getDiaryWeather().getCode() + "/" + diary.getDiaryMood().getCode() + "/";
        // 文件访问路径
        String accessDest = prefix + "/" + DateUtil.getDateStamp() + "/" + diary.getUserId() + "/" + diary.getDiaryWeather().getCode() + "/" + diary.getDiaryMood().getCode() + "/";

        File dir = new File(saveDest);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        filename = saveDest + newFileName;
        try {
            detailPhoto.write(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessDest + newFileName;
    }

    public File downloadFile(String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            if (file.exists()) {
                return file;
            }
        }
        throw new CommonException(ErrorEnum.FILE_NOT_FOUND);
    }
}
