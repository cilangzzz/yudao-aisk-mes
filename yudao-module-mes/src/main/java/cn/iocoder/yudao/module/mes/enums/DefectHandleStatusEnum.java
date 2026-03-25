package cn.iocoder.yudao.module.mes.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 不合格处理状态枚举
 */
@Getter
@AllArgsConstructor
public enum DefectHandleStatusEnum {

    PENDING(0, "待处理"),
    PROCESSING(1, "处理中"),
    CLOSED(2, "已闭环");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(DefectHandleStatusEnum::getStatus).toArray();

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 名称
     */
    private final String name;

    /**
     * 根据状态获取名称
     */
    public static String getNameByStatus(Integer status) {
        if (status == null) {
            return "";
        }
        for (DefectHandleStatusEnum value : values()) {
            if (value.getStatus().equals(status)) {
                return value.getName();
            }
        }
        return "";
    }
}