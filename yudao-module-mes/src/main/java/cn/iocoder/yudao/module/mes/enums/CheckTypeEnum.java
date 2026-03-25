package cn.iocoder.yudao.module.mes.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 检验类型枚举
 */
@Getter
@AllArgsConstructor
public enum CheckTypeEnum {

    PROCESS(0, "过程检"),
    FINAL(1, "终检");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CheckTypeEnum::getType).toArray();

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 名称
     */
    private final String name;

    /**
     * 根据类型获取名称
     */
    public static String getNameByType(Integer type) {
        if (type == null) {
            return "";
        }
        for (CheckTypeEnum value : values()) {
            if (value.getType().equals(type)) {
                return value.getName();
            }
        }
        return "";
    }
}