package com.yao.utils;

import com.yao.translate.TransApi;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLDecoder;

public class TranslateUtils {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20171207000103106";
    private static final String SECURITY_KEY = "C2L5SyWaRn3swHP5fUOZ";


    public static String getTranslate(String word,String from,String to){
        String result = "";
        try {
            TransApi api = new TransApi(APP_ID, SECURITY_KEY);
            result = api.getTransResult(word, from, to);
        }catch (Exception e){
            e.printStackTrace();
        }
        return solveJson(result);
    }

    private static String solveJson(String result) {
        String text = "";
        try {
            JSONObject resultJson = new JSONObject(result);
            //获取返回翻译结果
            JSONArray array = (JSONArray) resultJson.get("trans_result");
            if (array == null)
                return "";
            JSONObject dst = (JSONObject) array.get(0);
            if (dst == null)
                return "";
            text = dst.getString("dst");
            text = URLDecoder.decode(text, "UTF8");
            return text.toLowerCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 获取汉字的第一个拼音字母
     * @param chinese
     * @return
     */
    public static String ToPinyin(String chinese) {
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    pinyinStr += PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat)[0]+" ";
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }
}
