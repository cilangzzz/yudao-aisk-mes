package cn.iocoder.yudao.module.mes.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 工艺路线状态枚举
 */
@Getter
@AllArgsConstructor
public enum RoutingStatusEnum  {

    DRAFT(0, "草稿"),
    ACTIVE(1, "生效"),
    INACTIVE(2, "失效");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(RoutingStatusEnum::getStatus).toArray();

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 名称
     */
    private final String name;



}