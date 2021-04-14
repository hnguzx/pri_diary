package per.guzx.priDiary.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import per.guzx.priDiary.enumeration.ErrorEnum;
import per.guzx.priDiary.exception.ServiceException;
import per.guzx.priDiary.pojo.PdDiary;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Administrator
 */
@Component
@Slf4j
public class FileUtil {

    @Value("${spring.servlet.multipart.location}")
    public String absolute;

    @Resource
    Environment environment;

    @Resource
    private DateUtil dateUtil;

    @Resource
    private AddressUtil addressUtil;

    public String uploadFile(MultipartFile detailPhoto) {
        return this.uploadFile(detailPhoto, null);
    }

    /**
     * 文件上传
     *
     * @param detailPhoto
     * @param diary
     * @return
     */
    public String uploadFile(MultipartFile detailPhoto, PdDiary diary) {
        boolean isExist = false;
        String filename = detailPhoto.getOriginalFilename();
        String port = environment.getProperty("local.server.port");
//        String address = addressUtil.getV4IP();
        String address = addressUtil.getInnerIp();
        String prefix = "http://" + address + ":" + port + "/File";
        String suffix = "";
        String saveDest;
        String accessDest;
        if (filename.lastIndexOf(".") != -1) {
            suffix = filename.substring(filename.lastIndexOf(".") + 1);
        }
        String newFileName = UUID.randomUUID().toString().substring(0, 16).replace("-", "") + "." + suffix;
        if (Objects.isNull(diary)) {
            saveDest = absolute + "/" + dateUtil.getDateStamp() + "/";
            accessDest = prefix + "/" + dateUtil.getDateStamp() + "/";
        } else {
            // 文件保存路径
            saveDest = absolute + "/" + dateUtil.getDateStamp() + "/" + diary.getUserId() + "/" + diary.getDiaryWeather().getCode() + "/" + diary.getDiaryMood().getCode() + "/" + diary.getDiaryEvent().getCode() + "/";
            // 文件访问路径
            accessDest = prefix + "/" + dateUtil.getDateStamp() + "/" + diary.getUserId() + "/" + diary.getDiaryWeather().getCode() + "/" + diary.getDiaryMood().getCode() + "/" + diary.getDiaryEvent().getCode() + "/";
//            accessDest = "/" + dateUtil.getDateStamp() + "/" + diary.getUserId() + "/" + diary.getDiaryWeather().getCode() + "/" + diary.getDiaryMood().getCode() + "/" + diary.getDiaryEvent().getCode() + "/";
        }

        File dir = new File(saveDest);
        if (!dir.exists()) {
            isExist = dir.mkdirs();
        }
        log.info("上传文件，创建目录是否成功：" + isExist);
        filename = saveDest + newFileName;
        try {
            log.trace("保存文件到：" + saveDest);
//            detailPhoto.write(filename);
            detailPhoto.transferTo(new File(filename));
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
        fileName = absolute + fileName.substring(fileName.indexOf("File") + 4);
        boolean isDelete = false;
        File file = new File(fileName);
        if (file.exists()) {
            isDelete = file.delete();
        }
        log.info("文件删除是否成功：" + isDelete);
    }

}
