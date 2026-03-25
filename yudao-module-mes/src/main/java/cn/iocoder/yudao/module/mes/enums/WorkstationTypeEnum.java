package cn.iocoder.yudao.module.mes.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 工作站类型枚举
 */
@Getter
@AllArgsConstructor
public enum WorkstationTypeEnum implements IntArrayValuable {

    NORMAL(0, "普通工位"),
    KEY(1, "关键工位"),
    INSPECTION(2, "检验工位");

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(WorkstationTypeEnum::getType).toArray();

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 名称
     */
    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }

}