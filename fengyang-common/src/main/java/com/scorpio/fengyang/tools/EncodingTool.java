package com.scorpio.fengyang.tools;

import com.alibaba.druid.sql.visitor.functions.If;
import org.omg.IOP.Encoding;

/**
 * Created by hao on 2017/8/16.
 */
public class EncodingTool {
    public static String encoding(String str) {
        try {
            if (str != null) {
                return new String(str.getBytes("ISO-8859-1"), "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
