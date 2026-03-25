package cn.iocoder.yudao.module.mes.enums;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 作业状态枚举
 */
@Getter
@AllArgsConstructor
public enum OperationStatusEnum implements IntArrayValuable {

    IN_PROGRESS(0, "进行中"),
    COMPLETED(1, "已完成"),
    ABNORMAL(2, "异常");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(OperationStatusEnum::getStatus).toArray();

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 名称
     */
    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }

    /**
     * 根据状态获取名称
     */
    public static String getNameByStatus(Integer status) {
        if (status == null) {
            return "";
        }
        for (OperationStatusEnum value : values()) {
            if (value.getStatus().equals(status)) {
                return value.getName();
            }
        }
        return "";
    }

}