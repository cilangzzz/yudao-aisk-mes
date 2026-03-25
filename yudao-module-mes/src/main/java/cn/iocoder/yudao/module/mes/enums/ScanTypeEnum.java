package cn.iocoder.yudao.module.mes.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 扫码类型枚举
 */
@Getter
@AllArgsConstructor
public enum ScanTypeEnum {

    VIN("vin", "VIN码", 17),
    WORK_ORDER("work_order", "工单码", 14),
    MATERIAL("material", "物料码", null);

    /**
     * 类型
     */
    private final String type;
    /**
     * 名称
     */
    private final String name;
    /**
     * 长度
     */
    private final Integer length;

    /**
     * 根据扫码内容判断类型
     *
     * @param code 扫码内容
     * @return 扫码类型
     */
    public static ScanTypeEnum parse(String code) {
        if (code == null || code.isEmpty()) {
            return null;
        }
        // VIN码：17位字母数字组合
        if (code.length() == 17 && code.matches("^[A-Za-z0-9]+$")) {
            return VIN;
        }
        // 工单码：WO+日期+流水，14位
        if (code.length() == 14 && code.startsWith("WO")) {
            return WORK_ORDER;
        }
        // 其他为物料码
        return MATERIAL;
    }

}