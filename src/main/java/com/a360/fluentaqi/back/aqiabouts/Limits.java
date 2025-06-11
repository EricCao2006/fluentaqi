package com.a360.fluentaqi.back.aqiabouts;

/**
 * 污染级别转换函数
 * @author 尚阳@A360
 */
public class Limits {
    public static LimitDto coLimit(double value) {
        // TODO by 尚阳
        LimitDto dto = null;
        if (0 <= value && value <= 5) {
            dto = new LimitDto(1, "一级", "优", "#02E300");
        } else if (6 <= value && value <= 10) {
            dto = new LimitDto(2, "二级", "良", "#FFFF00");
        } else if (11 <= value && value <= 35) {
            dto = new LimitDto(3, "三级", "轻度污染", "#FF7E00");
        } else if (36 <= value && value <= 60) {
            dto = new LimitDto(4, "四级", "中度污染", "#FE0000");
        } else if (61 <= value && value <= 90) {
            dto = new LimitDto(5, "五级", "重度污染", "#98004B");
        } else if (91 <= value) {
            dto = new LimitDto(6, "六级", "严重污染", "#7E0123");
        }
        return dto;
    }

    public static LimitDto so2Limit(double value) {
        // TODO by 尚阳
        LimitDto dto = null;
        if (0 <= value && value <= 50) {
            dto = new LimitDto(1, "一级", "优", "#02E300");
        } else if (51 <= value && value <= 150) {
            dto = new LimitDto(2, "二级", "良", "#FFFF00");
        } else if (151 <= value && value <= 475) {
            dto = new LimitDto(3, "三级", "轻度污染", "#FF7E00");
        } else if (476 <= value && value <= 800) {
            dto = new LimitDto(4, "四级", "中度污染", "#FE0000");
        } else if (801 <= value && value <= 1600) {
            dto = new LimitDto(5, "五级", "重度污染", "#98004B");
        } else if (1600 <= value) {
            dto = new LimitDto(6, "六级", "严重污染", "#7E0123");
        }
        return dto;
    }

    public static LimitDto pmLimit(double value) {
        // TODO by 尚阳
        LimitDto dto = null;
        if (0 <= value && value <= 35) {
            dto = new LimitDto(1, "一级", "优", "#02E300");
        } else if (36 <= value && value <= 75) {
            dto = new LimitDto(2, "二级", "良", "#FFFF00");
        } else if (76 <= value && value <= 115) {
            dto = new LimitDto(3, "三级", "轻度污染", "#FF7E00");
        } else if (116 <= value && value <= 150) {
            dto = new LimitDto(4, "四级", "中度污染", "#FE0000");
        } else if (151 <= value && value <= 250) {
            dto = new LimitDto(5, "五级", "重度污染", "#98004B");
        } else if (251 <= value) {
            dto = new LimitDto(6, "六级", "严重污染", "#7E0123");
        }
        return dto;
    }

    public static LimitDto confirmLevel(int coLimit, int so2Limit, int pmLimit){
        // TODO by 尚阳
        LimitDto dto = null;
        int max = coLimit > so2Limit ? coLimit : so2Limit;
        max = pmLimit > max ? pmLimit : max;
        switch (max) {
            case 1:
                dto = new LimitDto(1, "一级", "优", "#02E300");
                break;
            case 2:
                dto = new LimitDto(2, "二级", "良", "#FFFF00");
                break;
            case 3:
                dto = new LimitDto(3, "三级", "轻度污染", "#FF7E00");
                break;
            case 4:
                dto = new LimitDto(4, "四级", "中度污染", "#FE0000");
                break;
            case 5:
                dto = new LimitDto(5, "五级", "重度污染", "#98004B");
                break;
            case 6:
                dto = new LimitDto(6, "六级", "严重污染", "#7E0123");
                break;
            default:
                break;
        }
        return dto;
    }
}
