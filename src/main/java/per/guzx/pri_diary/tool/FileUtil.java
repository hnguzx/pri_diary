package per.guzx.pri_diary.tool;

import org.springframework.beans.factory.annotation.Value;
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

@Controller
@RequestMapping("/request")
public class FileUtil {

    @Value("${spring.servlet.multipart.location}")
    private String absolt;

    @RequestMapping("/view")
    public String filePage() {
        return "index";
    }

    @PostMapping("/file")
    @ResponseBody
    public ApiResp uploadRequest(HttpServletRequest request) {
        boolean flag = false;
        MultipartHttpServletRequest multipartHttpServletRequest = null;
        if (request instanceof MultipartHttpServletRequest) {
            multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        } else {
            return ApiResp.retFail(ErrorEnum.SYS_ERROR);
        }
        // 获取文件信息
        MultipartFile file = multipartHttpServletRequest.getFile("detailPhoto");
        // 获取文件名称
        String fileName = file.getOriginalFilename();
        File saveFile = new File(fileName);
        try {
            // 保存文件
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResp.retFail(ErrorEnum.SYS_ERROR);
        }
        return ApiResp.retOk();

    }

    @PostMapping("/MultipartRequest")
    @ResponseBody
    public ApiResp uploadRequestByMultipartRequest(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResp.retFail(ErrorEnum.SYS_ERROR);
        }
        return ApiResp.retOk();
    }

    // 推荐使用
    @PostMapping("/part")
    @ResponseBody
//    public ApiResp uploadRequestByPart(Part detailPhoto, int userId, String detailContent,
//                                       int diaryId, String diaryTitle, String diaryWeather, String diaryMood,
//                                       String diaryEvent, String diaryLocation) {
    public ApiResp uploadRequestByPart(@RequestPart("detailPhoto") Part detailPhoto, @RequestPart(name = "diary",required = false) PdDiary diary) {
        // 原文件名
        String filename = detailPhoto.getSubmittedFileName();
        // 文件后缀
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        String newFileName = UUID.randomUUID().toString().replace("-", "") + "." + suffix;
        // 文件保存路径
        String dest = absolt + "/" + diary.getUserId() + "/" + DateUtil.getTimeStamp() + "/" + diary.getDiaryId() + "/";
        File dir = new File(dest);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        filename = dest + newFileName;
        try {
            detailPhoto.write(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResp.retFail(ErrorEnum.SYS_ERROR);
        }
        return ApiResp.retOk();
    }
}
