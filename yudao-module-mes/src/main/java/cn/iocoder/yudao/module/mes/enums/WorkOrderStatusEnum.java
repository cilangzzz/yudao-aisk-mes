package cn.iocoder.yudao.module.mes.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 工单状态枚举
 */
@Getter
@AllArgsConstructor
public enum WorkOrderStatusEnum  {

    CREATED(0, "待下发"),
    RELEASED(1, "已下发"),
    IN_PROGRESS(2, "生产中"),
    COMPLETED(3, "已完成"),
    CLOSED(4, "已关闭");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(WorkOrderStatusEnum::getStatus).toArray();

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 名称
     */
    private final String name;


}