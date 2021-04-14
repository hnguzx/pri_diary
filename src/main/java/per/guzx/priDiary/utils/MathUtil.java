package per.guzx.priDiary.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MathUtil {

    /**
     * 生成指定范围内的整数
     *
     * @param min
     * @param max
     * @return
     */
    public int getRangeInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }
}
