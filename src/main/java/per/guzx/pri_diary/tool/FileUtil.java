package per.guzx.pri_diary.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import per.guzx.pri_diary.enumeration.ErrorEnum;
import per.guzx.pri_diary.exception.ServiceException;
import per.guzx.pri_diary.pojo.PdDiary;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
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

    /**
     * 文件上传
     *
     * @param detailPhoto
     * @param diary
     * @return
     */
    public String uploadFile(Part detailPhoto, PdDiary diary) {
        // 删除原来的图片
//        deleteFile(diary.getDetailPhoto());
        String filename = detailPhoto.getSubmittedFileName();
        String port = environment.getProperty("local.server.port");
        String address = "";
//        try {
//            address = Inet4Address.getLocalHost().getHostAddress();
            address = getV4IP();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            log.error(ErrorEnum.FILE_UPLOAD.getMsg() + e);
//        }
        String prefix = "http://" + address + ":" + port + "/File";
        String suffix = "";
        if (filename.lastIndexOf(".") != -1) {
            suffix = filename.substring(filename.lastIndexOf(".") + 1);
        }

        String newFileName = UUID.randomUUID().toString().substring(0, 16).replace("-", "") + "." + suffix;
//        String newFileName = diary.getDiaryUpdateTime() + "." + suffix;
        // 文件保存路径
        String saveDest = absolt + "/" + dateUtil.getDateStamp() + "/" + diary.getUserId() + "/" + diary.getDiaryWeather().getCode() + "/" + diary.getDiaryMood().getCode() + "/" + diary.getDiaryEvent().getCode() + "/";
        // 文件访问路径
        String accessDest = prefix + "/" + dateUtil.getDateStamp() + "/" + diary.getUserId() + "/" + diary.getDiaryWeather().getCode() + "/" + diary.getDiaryMood().getCode() + "/" + diary.getDiaryEvent().getCode() + "/";
        File dir = new File(saveDest);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        filename = saveDest + newFileName;
        try {
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
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 获取本地ip
     *
     * @return
     */
    public String getV4IP() {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP

        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            log.error("获取ip地址错误！");
            e.printStackTrace();
        }
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }

    }
}
