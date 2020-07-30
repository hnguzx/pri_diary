package per.guzx.pri_diary.service;

public interface AsyncService {
    /**
     * 删除过期图片
     * @param fileName
     * @return
     */
    public void deleteOverdueImg();
}
