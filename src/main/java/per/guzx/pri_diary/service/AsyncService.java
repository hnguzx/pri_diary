package per.guzx.pri_diary.service;

import org.springframework.stereotype.Service;

@Service
public interface AsyncService {
    /**
     * 删除过期图片
     * @param fileName
     * @return
     */
    public void deleteOverdueImg();

    public void syncChatHistory();
}
