package per.guzx.priDiary.demo;

import java.io.File;

public class FileDeleteTest {
    public static void main(String[] args) {
        String fileName = "http://10.0.0.5:80/File/20200730/9/1/1/e09f8b0d0b464a.png";
        fileName = "F:\\Files"+fileName.substring(fileName.indexOf("File")+4);
        File file = new File(fileName);
        if (file.exists()){
            file.delete();
        }
        System.out.println(fileName);
    }
}
