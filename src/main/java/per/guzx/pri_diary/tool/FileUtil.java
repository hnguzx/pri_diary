package per.guzx.pri_diary.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.pojo.ApiResp;
import per.guzx.pri_diary.pojo.PdDiary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUtil {

    @Value("${spring.servlet.multipart.location}")
    public String absolt;

    public String uploadFile(HttpServletRequest request, Part detailPhoto, PdDiary diary) {
        String filename = detailPhoto.getSubmittedFileName();
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        String newFileName = UUID.randomUUID().toString().substring(0, 16).replace("-", "") + "." + suffix;
        // 文件保存路径
        String dest = absolt + "\\" + DateUtil.getDateStamp() + "\\" + diary.getUserId() + "\\" + diary.getDiaryWeather().getCode() + "\\" + diary.getDiaryMood().getCode() + "\\";
        File dir = new File(dest);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        filename = dest + newFileName;
        try {
            detailPhoto.write(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filename;
    }

    public File downloadFile(String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }
}
