package per.guzx.priDiary.service;

public interface AsyncService {
    /**
     * 删除过期图片
     * @param
     * @return
     */
    void deleteOverdueImg();

    void syncChatHistory();
}
