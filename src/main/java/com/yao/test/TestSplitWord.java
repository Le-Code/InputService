package com.yao.test;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.tagging.PinyinTagging;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

public class TestSplitWord {
    @Test
    public void testSplit(){
        List<Word> words = WordSegmenter.segWithStopWords("《速度与激情7》的中国内地票房自4月12日上映以来，在短短两周内突破20亿人民币");
        PinyinTagging.process(words);
        Stack<String>stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (Word word:words){
            sb.setLength(0);
            String suoxie = word.getAcronymPinYin();
            String pinyin = word.getFullPinYin();
            char[]chars = suoxie.toCharArray();
            for (int i = chars.length-1;i>=1;i--){
                stack.push(pinyin.substring(pinyin.lastIndexOf(chars[i])));
                pinyin = pinyin.substring(0,pinyin.lastIndexOf(chars[i]));
            }
            stack.push(pinyin);
            sb.append(stack.pop());
            if (sb.length()==0||word.getText().length()<2)
                continue;
            while (!stack.isEmpty()){
                sb.append("'"+stack.pop());
            }
            System.out.println(word.getText()+":"+sb.toString());
        }
    }
}
