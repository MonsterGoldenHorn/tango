package com.priva.tango.letcode.medium;

/**
 * 单词倒序
 *
 *
 * 先翻转每个单词，再翻转整个字符串。
 */
public class SwapWord {

    public String ReverseSentence(String str) {
        char[] chars = str.toCharArray();
        int done = 0;//整体长度
        int index = 0;//单词长度
        int size = chars.length;
        while(done < size){
            if(chars[index]==' '){
                //交换 0 - index和index+1 - size 的位置，0换到
                
            } else {
                index++;
            }
            done++;
        }
        return "";
    }
}
