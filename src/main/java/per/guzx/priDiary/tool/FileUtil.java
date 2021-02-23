package per.guzx.priDiary.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import per.guzx.priDiary.enumeration.ErrorEnum;
import per.guzx.priDiary.exception.ServiceException;
import per.guzx.priDiary.pojo.PdDiary;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class FileUtil {

    @Value("${spring.servlet.multipart.location}")
    public String absolt;

    @Autowired
    Environment environment;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private AddressUtil addressUtil;

    /**
     * 文件上传
     *
     * @param detailPhoto
     * @param diary
     * @return
     */
    public String uploadFile(Part detailPhoto, PdDiary diary) {
        boolean isExist = false;
        String filename = detailPhoto.getSubmittedFileName();
        String port = environment.getProperty("local.server.port");
        String address = addressUtil.getV4IP();
        String prefix = "http://" + address + ":" + port + "/File";
        String suffix = "";
        if (filename.lastIndexOf(".") != -1) {
            suffix = filename.substring(filename.lastIndexOf(".") + 1);
        }

        String newFileName = UUID.randomUUID().toString().substring(0, 16).replace("-", "") + "." + suffix;
        // 文件保存路径
        String saveDest = absolt + "/" + dateUtil.getDateStamp() + "/" + diary.getUserId() + "/" + diary.getDiaryWeather().getCode() + "/" + diary.getDiaryMood().getCode() + "/" + diary.getDiaryEvent().getCode() + "/";
        // 文件访问路径
        String accessDest = prefix + "/" + dateUtil.getDateStamp() + "/" + diary.getUserId() + "/" + diary.getDiaryWeather().getCode() + "/" + diary.getDiaryMood().getCode() + "/" + diary.getDiaryEvent().getCode() + "/";
        File dir = new File(saveDest);
        if (!dir.exists()) {
            isExist = dir.mkdirs();
        }
        log.info("上传文件，创建目录是否成功：" + isExist);
        filename = saveDest + newFileName;
        try {
            log.trace("保存文件到：" + saveDest);
            detailPhoto.write(filename);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件写入错误！");
            throw new ServiceException(ErrorEnum.FILE_UPLOAD);
        }
        return accessDest + newFileName;
    }

    /**
     * 文件下载
     *
     * @param fileName
     * @return
     */
    public File downloadFile(String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            if (file.exists()) {
                return file;
            }
        }
        throw new ServiceException(ErrorEnum.FILE_NOT_FOUND);
    }

    /**
     * 文件删除
     *
     * @param fileName
     * @return
     */
    public void deleteFile(String fileName) {
        fileName = absolt + fileName.substring(fileName.indexOf("File") + 4);
        boolean isDelete = false;
        File file = new File(fileName);
        if (file.exists()) {
            isDelete = file.delete();
        }
        log.info("文件删除是否成功：" + isDelete);
    }

}
