package per.guzx.pri_diary.tool;

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

@Controller
@RequestMapping("/request")
public class FileUtil {

    @RequestMapping("/view")
    public String filePage(){
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
    public ApiResp uploadRequestByPart(Part file, @RequestParam("userId") int userId, @RequestParam("detailContent") String detailContent,
                                       @RequestParam("diaryId") int diaryId, @RequestParam("diaryTitle") String diaryTitle,
                                       @RequestParam("diaryWeather") String diaryWeather, @RequestParam("diaryMood") String diaryMood,
                                       @RequestParam("diaryEvent") String diaryEvent, @RequestParam("diaryLocation") String diaryLocation) {
        String filename = file.getSubmittedFileName();
        try {
            file.write(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResp.retFail(ErrorEnum.SYS_ERROR);
        }
        return ApiResp.retOk();
    }
}
