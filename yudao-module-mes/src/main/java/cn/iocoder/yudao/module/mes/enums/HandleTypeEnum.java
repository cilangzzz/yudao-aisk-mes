package cn.iocoder.yudao.module.mes.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 不合格处理方式枚举
 */
@Getter
@AllArgsConstructor
public enum HandleTypeEnum {

    REPAIR(0, "返修"),
    SCRAP(1, "报废"),
    CONCESSION(2, "让步接收");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(HandleTypeEnum::getType).toArray();

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
        for (HandleTypeEnum value : values()) {
            if (value.getType().equals(type)) {
                return value.getName();
            }
        }
        return "";
    }
}