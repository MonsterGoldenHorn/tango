package com.priva.tango.cron;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CronCheckUtil {

    public static String cronValidate(String cronExp) {
        String message;
        String[] cronParams = cronExp.trim().split(" ");
        if (StringUtils.isEmpty(cronParams[0])) {
            return "cron表达式首位不能是空格";
        }
        //判断cron表达式是否具有该具有的属性长度，没有年份的长度为6，带年份的长度为7，其他情况都是错误的
        if (cronParams.length < 6 || cronParams.length > 7) {
            return "cron表达式需要输入6-7位参数，请重新输入";
        }

        //日和周必须有一个为?，或者全为*
        boolean flag =
                ("?".equals(cronParams[3]) && !"?".equals(cronParams[5])) ||
                        ("?".equals(cronParams[5]) && !"?".equals(cronParams[3])) ||
                        ("*".equals(cronParams[3]) && "*".equals(cronParams[5]));
        if (flag) {
            //检查第一位的秒是否正确
            message = checkSecondsField(cronParams[0]);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }

            //检查第二位的分是否正确
            message = checkMinutesField(cronParams[1]);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }

            //检查第三位的时是否正确
            message = checkHoursField(cronParams[2]);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }

            //检查第四位的日是否正确
            message = checkDayOfMonthField(cronParams[3]);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }

            //检查第五位的月是否正确
            message = checkMonthsField(cronParams[4]);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }

            //检查第6位的周是否正确
            message = checkDayOfWeekField(cronParams[5]);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }

            //检查第七位的年是否正确
            if (cronParams.length > 6) {
                message = checkYearField(cronParams[6]);
                if (StringUtils.isNotEmpty(message)) {
                    return message;
                }
            }
            return "";
        }
        return "指定日时周必须设为不指定(?),指定周时日必须设为不指定(?)";
    }

    //检查秒的函数方法
    public static String checkSecondsField(String secondsField) {
        // 这里按照业务需求改写 第一位即秒只能出现0-59的数字 不允许出现特殊符号
        return onlyNumber(secondsField) ? "" : "秒的参数只能是0-59的整数";
    }

    //检查分的函数方法
    public static String checkMinutesField(String minutesField) {
        // return checkField(minutesField, 0, 59, "分");
        // 这里按照业务需求改写 第二位即秒只能出现0-59的数字 不允许出现特殊符号
        return onlyNumber(minutesField) ? "" : "分的参数只能是0-59的整数";
    }

    /**
     * 校验数字
     *
     * @param str 目标字符串
     * @return 是否为数字
     */
    private static boolean onlyNumber(String str) {
        String pattern = "^([0-9]|[1-5][0-9])$";
        return str.matches(pattern);
    }

    //检查小时的函数方法
    private static String checkHoursField(String hoursField) {
        return checkField(hoursField, 0, 23, "时");
    }

    //检查日期的函数方法
    private static String checkDayOfMonthField(String dayOfMonthField) {
        if ("?".equals(dayOfMonthField)) {
            return "";
        }
        if (dayOfMonthField.contains("L")) {
            return checkFieldWithLetter(dayOfMonthField, "L", 1, 7, "日");
        } else if (dayOfMonthField.contains("W")) {
            return checkFieldWithLetter(dayOfMonthField, "W", 1, 31, "日");
        } else if (dayOfMonthField.contains("C")) {
            return checkFieldWithLetter(dayOfMonthField, "C", 1, 31, "日");
        }
        return checkField(dayOfMonthField, 1, 31, "日");
    }

    //检查月份的函数方法
    private static String checkMonthsField(String monthsField) {
        //月份简写处理
        if (!"*".equals(monthsField)) {
            monthsField = monthsField.replace("JAN", "1");
            monthsField = monthsField.replace("FEB", "2");
            monthsField = monthsField.replace("MAR", "3");
            monthsField = monthsField.replace("APR", "4");
            monthsField = monthsField.replace("MAY", "5");
            monthsField = monthsField.replace("JUN", "6");
            monthsField = monthsField.replace("JUL", "7");
            monthsField = monthsField.replace("AUG", "8");
            monthsField = monthsField.replace("SEP", "9");
            monthsField = monthsField.replace("OCT", "10");
            monthsField = monthsField.replace("NOV", "11");
            monthsField = monthsField.replace("DEC", "12");
            return checkField(monthsField, 1, 12, "月份");
        } else {
            return "";
        }
    }

    //星期验证
    private static String checkDayOfWeekField(String dayOfWeekField) {
        dayOfWeekField = dayOfWeekField.replace("SUN", "1");
        dayOfWeekField = dayOfWeekField.replace("MON", "2");
        dayOfWeekField = dayOfWeekField.replace("TUE", "3");
        dayOfWeekField = dayOfWeekField.replace("WED", "4");
        dayOfWeekField = dayOfWeekField.replace("THU", "5");
        dayOfWeekField = dayOfWeekField.replace("FRI", "6");
        dayOfWeekField = dayOfWeekField.replace("SAT", "7");
        if ("?".equals(dayOfWeekField)) {
            return "";
        }
        if (dayOfWeekField.contains("L")) {
            return checkFieldWithLetterWeek(dayOfWeekField, "L", 1, 7, "星期");
        } else if (dayOfWeekField.contains("C")) {
            return checkFieldWithLetterWeek(dayOfWeekField, "C", 1, 7, "星期");
        } else if (dayOfWeekField.contains("#")) {
            return checkFieldWithLetterWeek(dayOfWeekField, "#", 1, 7, "星期");
        } else {
            return checkField(dayOfWeekField, 1, 7, "星期");
        }
    }

    //检查年份的函数方法
    private static String checkYearField(String yearField) {
        return checkField(yearField, 1970, 2099, "年的");
    }

    //通用的检查值的大小范围的方法( - , / *)
    private static String checkField(String value, Integer minimal, Integer maximal, String attribute) {
        //校验值中是否有“-”，如果有“-”的话，下标会>0
        if (value.contains("-")) {
            return checkRangeAndCycle(value, minimal, maximal, attribute);
        }
        //校验值中是否有“，”，如果有“,”的话，下标会>0
        else if (value.contains(",")) {
            return checkListField(value, minimal, maximal, attribute);
        }
        //校验值中是否有“/”，如果有“/”的话，下标会>0
        else if (value.contains("/")) {
            return checkIncrementField(value, minimal, maximal, attribute);
        }
        //校验值是否为“*”
        else if ("*".equals(value)) {
            return "";
        }
        //校验单独的数字，英文字母，以及各种神奇的符号等...
        else {
            return checkIntValue(value, minimal, maximal, true, attribute);
        }
    }

    /**
     * 检测是否是整数以及是否在范围内,参数：检测的值，下限，上限，是否检查端点，检查的属性
     */
    private static String checkIntValue(String value, Integer minimal, Integer maximal, boolean checkExtremity, String attribute) {
        try {
            //用10进制方法来进行整数转换
            int val = Integer.parseInt(value, 10);
            if (Integer.parseInt(value) == val) {
                if (checkExtremity) {
                    if (val < minimal || val > maximal) {
                        return attribute + "的参数取值范围必须在" + minimal + "-" + maximal + "之间";
                    }
                    return "";
                }
                return "";
            }
            return attribute + "的参数存在非法字符，必须为整数或允许的大写英文";
        } catch (Exception e) {
            return attribute + "的参数有非法字符，必须是整数~";
        }
    }

    /**
     * 检验枚举类型的参数是否正确
     */
    private static String checkListField(String value, Integer minimal, Integer maximal, String attribute) {
        String[] st = value.split(",");
        String[] values = new String[st.length];
        //计算枚举的数字在数组中中出现的次数，出现一次为没有重复的。
        int count;
        System.arraycopy(st, 0, values, 0, values.length);
        //判断枚举类型的值是否重复
        for (String s : values) {
            //判断枚举的值是否在范围内
            String message = checkIntValue(s, minimal, maximal, true, attribute);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }
            count = 0;
            for (String item : values) {
                if (Objects.equals(s, item)) {
                    count++;
                }
                if (count > 1) {
                    return attribute + "中的参数重复";
                }
            }
        }
        int previousValue = -1;
        //判断枚举的值是否排序正确
        for (String currentValue : values) {
            try {
                int val = Integer.parseInt(currentValue, 10);
                if (val < previousValue) {
                    return attribute + "的参数应该从小到大";
                } else {
                    previousValue = val;
                }
            } catch (Exception e) {
                //前面验证过了，这边的代码不可能跑到
                return "这段提示用不到";
            }
        }
        return "";
    }

    /**
     * 检验循环
     */
    private static String checkIncrementField(String value, Integer minimal, Integer maximal, String attribute) {
        if (value.split("/").length > 2) {
            return attribute + "中的参数只能有一个'/'";
        }
        String start = value.substring(0, value.indexOf("/"));
        String increment = value.substring(value.indexOf("/") + 1);
        if (!"*".equals(start)) {
            //检验前值是否正确
            String message = checkIntValue(start, minimal, maximal, true, attribute);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }
            //检验后值是否正确
            message = checkIntValue(increment, minimal, maximal, true, attribute);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }
            return "";
        } else {
            //检验后值是否正确
            return checkIntValue(increment, minimal, maximal, false, attribute);
        }
    }


    /**
     * 检验范围
     */
    private static String checkRangeAndCycle(String params, Integer minimal, Integer maximal, String attribute) {
        //校验“-”符号是否只有一个
        if (params.split("-").length > 2) {
            return attribute + "中的参数只能有一个'-'";
        }
        String value;
        String cycle = null;
        //检验范围内是否有嵌套周期
        if (params.contains("/")) {
            //校验“/”符号是否只有一个
            if (params.split("/").length > 2) {
                return attribute + "中的参数只能有一个'/'";
            }
            value = params.split("/")[0];
            cycle = params.split("/")[1];
            //判断循环的参数是否正确
            String message = checkIntValue(cycle, minimal, maximal, true, attribute);
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }
        } else {
            value = params;
        }
        String startValue = value.substring(0, value.indexOf("-"));
        String endValue = value.substring(value.indexOf("-") + 1);
        //判断参数范围的第一个值是否正确
        String message = checkIntValue(startValue, minimal, maximal, true, attribute);
        if (StringUtils.isNotEmpty(message)) {
            return message;
        }
        //判断参数范围的第二个值是否正确
        message = checkIntValue(endValue, minimal, maximal, true, attribute);
        if (StringUtils.isNotEmpty(message)) {
            return message;
        }
        //判断参数的范围前值是否小于后值
        try {
            int startVal = Integer.parseInt(startValue, 10);
            int endVal = Integer.parseInt(endValue, 10);
            if (endVal < startVal) {
                return attribute + "的取值范围错误，前值必须小于后值";
            }
            if (endVal - startVal < Integer.parseInt(cycle, 10)) {
                return attribute + "的取值范围内的循环无意义";
            }
            return "";
        } catch (Exception e) {
            //用不到这行代码的
            return attribute + "的参数有非法字符，必须是整数";
        }
    }

    /**
     * 检查日中的特殊字符
     */
    private static String checkFieldWithLetter(String value, String letter, Integer minimalBefore, Integer maximalBefore, String attribute) {
        //判断是否只有一个字母
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == letter.charAt(0)) {
                count++;
            }
            if (count > 1) {
                return attribute + "的值的" + letter + "字母只能有一个";
            }
        }
        //校验L
        if ("L".equals(letter)) {
            if ("LW".equals(value)) {
                return "";
            }
            if ("L".equals(value)) {
                return "";
            }
            if (value.endsWith("LW") && value.length() > 2) {
                return attribute + "中的参数，最后的LW前面不能有任何字母参数";
            }
            if (!value.endsWith("L")) {
                return attribute + "中的参数，L字母后面不能有W以外的字符、数字等";
            } else {
                String num = value.substring(0, value.indexOf(letter));
                return checkIntValue(num, minimalBefore, maximalBefore, true, attribute);
            }
        }

        //校验W
        if ("W".equals(letter)) {
            if (!value.endsWith("W")) {
                return attribute + "中的参数的W必须作为结尾";
            } else {
                if ("W".equals(value)) {
                    return attribute + "中的参数的W前面必须有数字";
                }
                String num = value.substring(0, value.indexOf(letter));
                return checkIntValue(num, minimalBefore, maximalBefore, true, attribute);
            }
        }

        if ("C".equals(letter)) {
            if (!value.endsWith("C")) {
                return attribute + "中的参数的C必须作为结尾";
            } else {
                if ("C".equals(value)) {
                    return attribute + "中的参数的C前面必须有数字";
                }
                String num = value.substring(0, value.indexOf(letter));
                return checkIntValue(num, minimalBefore, maximalBefore, true, attribute);
            }
        }
        return "";
    }

    /**
     * 检查星期中的特殊字符
     */
    private static String checkFieldWithLetterWeek(String value, String letter, Integer minimalBefore, Integer maximalBefore, String attribute) {
        //判断是否只有一个字母
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == letter.charAt(0)) {
                count++;
            }
            if (count > 1) {
                return attribute + "的值的" + letter + "字母只能有一个";
            }
        }

        //校验L
        if ("L".equals(letter)) {
            if ("L".equals(value)) {
                return "";
            }
            if (!value.endsWith("L")) {
                return attribute + "中的参数，L字母必须是最后一位";
            } else {
                String num = value.substring(0, value.indexOf(letter));
                return checkIntValue(num, minimalBefore, maximalBefore, true, attribute);
            }
        }

        if ("C".equals(letter)) {
            if (!value.endsWith("C")) {
                return attribute + "中的参数的C必须作为结尾";
            } else {
                if ("C".equals(value)) {
                    return attribute + "中的参数的C前面必须有数字";
                }
                String num = value.substring(0, value.indexOf(letter));
                return checkIntValue(num, minimalBefore, maximalBefore, true, attribute);
            }
        }

        if ("#".equals(letter)) {
            if ("#".equals(value)) {
                return attribute + "中的#前后必须有整数";
            }
            if (value.charAt(0) == letter.charAt(0)) {
                return attribute + "中的#前面必须有整数";
            }
            if (value.endsWith("#")) {
                return attribute + "中的#后面必须有整数";
            }
            String num1 = value.substring(0, value.indexOf(letter));
            String num2 = value.substring(value.indexOf(letter) + 1);
            String message = checkIntValue(num1, 1, 4, true, attribute + "的#前面");
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }
            message = checkIntValue(
                    num2,
                    minimalBefore,
                    maximalBefore,
                    true,
                    attribute + "的#后面"
            );
            if (StringUtils.isNotEmpty(message)) {
                return message;
            }
            return "";
        }
        return "";
    }
}
