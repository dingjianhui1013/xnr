/**
 *2011-8-26 下午05:16:06
 */
package com.cntinker.util;


/**
 * @author bin_liu
 *
 */
public class CnCharCount{

    /**
     * 测试
     * 
     * @param args
     *            String[]
     */
    public static void main(String[] args) throws Exception{

        String cnStr = "测试数据";
        char[] chars = cnStr.toCharArray();

        for(int i = 0;i < chars.length;i ++ ){
            System.out.print(chars[i] + " : " + getStrokeCount(chars[i]));
        }

    }

    /**
     * 根据汉字字符获得笔画数,拼音和非法字符默认为0
     * 
     * @param charcator
     *            char
     * @return int
     */
    public static int getStrokeCount(char charcator){
        byte[] bytes = ( String.valueOf(charcator) ).getBytes();
        if(bytes == null || bytes.length > 2 || bytes.length <= 0){
            // 错误引用,非合法字符
            return 0;
        }
        if(bytes.length == 1){
            // 英文字符
            return 0;
        }
        if(bytes.length == 2){
            // 中文字符
            int highByte = 256 + bytes[0];
            int lowByte = 256 + bytes[1];
            return getStrokeCount(highByte,lowByte);
        }

        // 未知错误
        return 0;
    }

    /**
     * @param highByte
     *            int：高位字节
     * @param lowByte
     *            int：低位字节
     * @return int
     */
    private static int getStrokeCount(int highByte,int lowByte){
        if(highByte < 0xB0 || highByte > 0xF7 || lowByte < 0xA1
                || lowByte > 0xFE){
            // 非GB2312合法字符
            return -1;
        }
        int offset = ( highByte - 0xB0 ) * ( 0xFE - 0xA0 ) + ( lowByte - 0xA1 );
        return gb2312StrokeCount[offset];
    }

    // GB2312的字符集的笔划列表，可以参考gb2312字符全集
    private static int[] gb2312StrokeCount = {
    /* B0 */
    10,7,10,10,8,10,9,11,17,14,13,5,13,10,12,15,10,6,10,9,13,8,10,10,8,8,10,5,
            10,14,16,9,12,12,15,15,7,10,5,5,7,10,2,9,4,8,12,13,7,10,7,21,10,8,
            5,9,6,13,8,8,9,13,12,10,13,7,10,10,8,8,7,8,7,19,5,4,8,5,9,10,14,14,
            9,12,15,10,15,12,12,8,9,5,15,10,
            /* B1 */
            16,13,9,12,8,8,8,7,15,10,13,19,8,13,12,8,5,12,9,4,9,10,7,8,12,12,
            10,8,8,5,11,11,11,9,9,18,9,12,14,4,13,10,8,14,13,14,6,10,9,4,7,13,
            6,11,14,5,13,16,17,16,9,18,5,12,8,9,9,8,4,16,16,17,12,9,11,15,8,19,
            16,7,15,11,12,16,13,10,13,7,6,9,5,8,9,9,
            /* B2 */
            10,6,8,11,15,8,10,8,12,9,13,10,14,7,8,11,11,14,12,8,7,10,2,10,7,11,
            4,5,7,19,10,8,17,11,12,7,3,7,12,15,8,11,11,14,16,8,10,9,11,11,7,7,
            10,4,7,17,16,16,15,11,9,8,12,8,5,9,7,19,12,3,9,9,9,14,12,14,7,9,8,
            8,10,10,12,11,14,12,11,13,11,6,11,19,8,11,
            /* B3 */
            6,9,11,4,11,7,2,12,8,11,10,12,7,9,12,15,15,11,7,8,4,7,15,12,7,15,
            10,6,7,6,11,7,7,7,12,8,15,10,9,16,6,7,10,12,12,15,8,8,10,10,10,6,
            13,9,11,6,7,6,6,10,8,8,4,7,10,5,9,6,6,6,11,8,8,13,12,14,13,13,13,4,
            11,14,4,10,7,5,16,12,18,12,13,12,9,13,
            /* B4 */
            10,12,24,13,13,5,12,3,9,13,7,11,12,7,9,12,15,7,6,6,7,8,11,13,8,9,
            13,15,10,11,7,21,18,11,11,9,14,14,13,13,10,7,6,8,12,6,15,12,7,5,4,
            5,11,11,15,17,9,19,16,12,14,11,13,10,13,14,11,14,7,6,3,14,15,12,11,
            10,13,12,6,12,14,5,3,7,4,12,17,9,9,5,9,11,9,11,
            /* B5 */
            9,10,8,4,8,10,11,9,5,12,7,11,11,8,11,11,6,9,10,9,10,2,10,17,10,7,
            11,6,8,15,11,12,11,15,11,8,19,6,12,12,17,14,4,12,7,14,8,10,11,7,10,
            14,14,8,8,6,12,11,9,7,10,12,16,11,13,13,9,8,16,9,5,7,7,8,11,12,11,
            13,13,5,16,10,2,11,6,8,10,12,10,14,15,8,11,13,
            /* B6 */
            2,7,5,7,8,12,13,8,4,6,5,5,12,15,6,9,8,9,7,9,11,7,4,9,7,10,12,10,13,
            9,12,9,10,11,13,12,7,14,7,9,12,7,14,12,14,9,11,12,11,7,4,5,15,7,19,
            12,10,7,9,9,12,11,9,6,6,9,13,6,13,11,8,12,11,13,10,12,9,15,6,10,10,
            4,7,12,11,10,10,6,2,6,5,9,9,2,
            /* B7 */
            9,5,9,12,6,4,9,8,9,18,6,12,18,15,8,8,17,3,10,4,7,8,8,5,7,7,7,7,4,8,
            8,6,7,6,6,7,8,11,8,11,3,8,10,10,7,8,8,8,9,7,11,7,8,4,7,7,12,7,10,8,
            6,8,12,12,4,9,8,13,10,12,4,9,11,10,5,13,6,8,4,7,7,4,15,8,14,7,8,13,
            12,9,11,6,9,8,
            /* B8 */
            10,11,13,11,5,7,7,11,10,10,8,11,12,8,14,9,11,18,12,9,12,5,8,4,13,6,
            12,4,7,6,13,8,15,14,8,7,13,9,11,12,3,5,7,9,9,7,10,13,8,11,21,4,6,9,
            9,7,7,7,12,7,16,10,10,14,10,16,13,15,15,7,10,14,12,4,11,10,8,12,9,
            12,10,12,9,12,11,3,6,9,10,13,10,7,8,19,
            /* B9 */
            10,10,11,3,7,5,10,11,8,10,4,9,3,6,7,9,7,6,9,4,7,8,8,9,8,8,11,12,11,
            8,14,7,8,8,8,13,5,11,9,7,8,9,10,8,12,8,5,9,14,9,13,8,8,8,12,6,8,9,
            6,14,11,23,12,20,8,6,3,10,13,8,6,11,5,7,9,6,9,8,9,10,8,13,9,8,12,
            13,12,12,10,8,8,14,6,9,15,9,10,10,6,10,9,12,14,7,12,7,11,12,8,12,7,
            16,16,10,7,16,10,11,6,5,5,8,10,17,17,14,11,9,6,10,5,10,8,12,10,11,
            10,5,8,7,6,11,13,9,8,11,14,14,15,9,15,12,11,9,9,9,10,7,15,16,9,8,9,
            10,9,11,9,7,5,6,12,9,12,7,9,10,6,8,5,8,13,10,12,9,15,8,15,12,
            /* BB */
            8,8,11,7,4,7,4,7,9,6,12,12,8,6,4,8,13,9,7,11,7,6,8,10,7,12,10,11,
            10,12,13,11,10,9,4,9,12,11,16,15,17,9,11,12,13,10,13,9,11,6,9,12,
            17,9,12,6,13,10,15,5,12,11,10,11,6,10,5,6,9,9,9,8,11,13,9,11,17,9,
            6,4,10,8,12,16,8,11,5,6,11,6,13,15,10,14,
            /* BC */
            6,5,9,16,4,7,10,11,12,6,7,12,13,20,12,3,9,10,6,7,13,6,9,2,10,3,13,
            7,16,8,6,11,8,11,9,11,11,4,5,9,7,7,7,10,6,14,9,6,8,10,5,9,12,10,5,
            10,11,15,6,9,8,13,7,10,7,6,11,7,13,10,8,8,6,12,9,11,9,14,12,8,10,
            13,9,11,11,9,14,13,12,9,4,13,15,6,
            /* BD */
            10,10,9,8,11,12,10,8,15,9,9,10,6,19,12,10,9,6,6,13,8,15,12,17,12,
            10,6,8,9,9,9,20,12,11,11,8,11,9,7,9,16,9,13,11,14,10,10,5,12,12,11,
            9,11,12,6,14,7,5,10,8,11,13,14,9,9,13,8,7,17,7,9,10,4,9,9,8,3,12,4,
            8,4,9,18,10,13,4,13,7,13,10,13,7,10,10,
            /* BE */
            6,7,9,14,8,13,12,16,8,11,14,13,8,4,19,12,11,14,14,12,16,8,10,13,11,
            10,8,9,12,12,7,5,7,9,3,7,2,10,11,11,5,6,13,8,12,8,17,8,8,10,8,8,11,
            7,8,9,9,8,14,7,11,4,8,11,15,13,10,5,11,8,10,10,12,10,10,11,8,10,15,
            23,7,11,10,17,9,6,6,9,7,11,9,6,7,10,
            /* BF */
            9,12,10,9,10,12,8,5,9,4,12,13,8,12,5,12,11,7,9,9,11,14,17,6,7,4,8,
            6,9,10,15,8,8,9,12,15,14,9,7,9,5,12,7,8,9,10,8,11,9,10,7,7,8,10,4,
            11,7,3,6,11,9,10,13,8,14,7,12,6,9,9,13,10,7,13,8,7,10,12,6,12,7,10,
            8,11,7,7,3,11,8,13,12,9,13,11,
            /* C0 */
            12,12,12,8,8,10,7,9,6,13,12,8,8,12,14,12,14,11,10,7,13,13,11,9,8,
            16,12,5,15,14,12,9,16,12,9,13,11,12,10,11,8,10,10,10,7,7,6,8,9,13,
            10,10,11,5,13,18,16,15,11,17,9,16,6,9,8,12,13,7,9,11,11,15,16,10,
            10,13,11,7,7,15,5,10,9,6,10,7,5,7,10,4,7,12,8,9,
            /* C1 */
            12,5,11,7,8,2,14,10,9,12,10,7,18,13,8,10,8,11,11,12,10,9,8,13,10,
            11,13,7,7,11,12,12,9,10,15,11,14,7,16,14,5,15,2,14,17,14,10,6,12,
            10,6,11,12,8,17,16,9,7,20,11,15,10,7,8,9,11,13,13,10,7,11,10,7,10,
            8,11,5,5,13,11,14,12,13,10,6,15,10,9,4,5,11,8,11,16,
            /* C2 */
            11,8,8,7,13,9,12,15,14,8,7,5,11,7,8,11,7,8,12,19,13,21,13,10,11,16,
            12,8,7,15,7,6,11,8,10,15,12,12,10,12,9,11,13,11,9,10,9,13,7,7,11,
            11,7,8,6,4,7,7,6,11,17,8,11,13,14,14,13,12,9,9,9,6,11,7,8,9,3,9,14,
            6,10,6,7,8,6,9,15,14,12,13,14,11,14,14,
            /* C3 */
            13,6,9,8,8,6,10,11,8,13,4,5,10,5,8,9,12,14,9,3,8,8,11,14,15,13,7,9,
            12,14,7,9,9,12,8,12,3,7,5,11,13,17,13,13,11,11,8,11,15,19,17,9,11,
            8,6,10,8,8,14,11,12,12,10,11,11,7,9,10,12,9,8,11,13,17,9,12,8,7,14,
            5,5,8,5,11,10,9,8,16,8,11,6,8,13,13,
            /* C4 */
            14,19,14,14,16,15,20,8,5,10,15,16,8,13,13,8,11,6,9,8,7,7,8,5,13,14,
            13,12,14,4,5,13,8,16,10,9,7,9,6,9,7,6,2,5,9,8,9,7,10,22,9,10,9,8,
            11,8,10,4,14,10,8,16,10,8,5,7,7,10,13,9,13,14,8,6,15,15,11,8,10,14,
            5,7,10,10,19,11,15,15,10,11,9,8,16,5,
            /* C5 */
            8,8,4,7,9,7,10,9,6,7,5,7,9,3,13,9,8,9,17,20,10,10,8,9,8,18,7,11,7,
            11,9,8,8,8,12,8,11,12,11,12,9,19,15,11,15,9,10,7,9,6,8,10,16,9,7,8,
            7,9,10,12,8,8,9,11,14,12,10,10,8,7,12,9,10,8,11,15,12,13,12,13,16,
            16,8,13,11,13,8,9,21,7,8,15,12,9,
            /* C6 */
            11,12,10,5,4,12,15,7,20,15,11,4,12,15,14,16,11,14,16,9,13,8,9,13,6,
            8,8,11,5,8,10,7,9,8,8,11,11,10,14,8,11,10,5,12,4,10,12,11,13,10,6,
            10,12,10,14,19,18,12,12,10,11,8,2,10,14,9,7,8,12,8,8,11,11,10,6,14,
            8,6,11,10,6,3,6,7,9,9,16,4,6,7,7,8,5,11,
            /* C7 */
            9,9,9,6,8,10,3,6,13,5,12,11,16,10,10,9,15,13,8,15,11,12,4,14,8,7,
            12,7,14,14,12,7,16,14,14,10,10,17,6,8,5,16,15,12,10,9,10,4,8,5,8,9,
            9,9,9,10,12,13,7,15,12,13,7,8,9,9,10,10,11,16,12,12,11,8,10,6,12,7,
            9,5,7,11,7,5,9,8,12,4,11,6,11,8,7,11,
            /* C8 */
            8,11,17,15,5,11,23,6,16,10,6,11,10,4,8,4,10,8,16,7,13,14,12,11,12,
            13,12,16,5,9,22,20,20,20,5,9,7,9,12,10,4,4,2,7,7,6,4,3,7,6,5,4,4,6,
            9,13,9,16,14,13,10,9,4,12,9,6,9,20,16,17,6,10,8,6,2,15,8,6,15,13,
            12,7,10,8,10,15,9,11,13,17,13,14,3,8,
            /* C9 */
            6,12,10,13,8,12,12,6,12,13,6,10,12,14,10,9,6,8,7,7,13,11,13,12,10,
            9,8,7,3,7,14,8,5,8,16,17,16,12,6,10,15,14,6,11,12,10,3,8,14,11,10,
            12,10,6,3,14,4,10,7,8,11,11,11,6,8,11,13,10,13,10,7,6,10,5,8,7,7,
            11,10,8,9,7,8,11,9,8,13,11,7,5,12,9,4,11,
            /* CA */
            9,11,12,9,5,6,5,9,9,12,8,3,8,2,5,9,7,4,9,9,8,7,5,5,8,9,8,8,6,5,3,5,
            9,8,9,14,10,8,9,13,16,9,5,8,12,8,4,5,9,9,8,8,6,4,9,6,7,11,11,8,14,
            11,15,8,11,10,7,13,8,12,11,12,4,12,11,15,16,12,17,13,13,12,13,12,5,
            8,9,7,6,9,14,11,13,14,
            /* CB */
            10,8,9,14,10,5,5,10,9,17,4,11,10,4,13,12,7,17,9,12,9,11,10,9,12,15,
            15,9,7,5,5,6,13,6,13,5,7,6,8,3,8,10,8,10,9,7,6,9,12,15,16,14,7,12,
            9,10,10,12,14,13,13,11,7,8,14,13,14,9,11,11,10,21,13,6,17,12,14,10,
            6,10,10,13,11,10,14,11,10,12,8,13,5,5,6,12,
            /* CC */
            16,9,17,15,9,8,8,5,10,11,4,8,7,7,13,8,15,13,7,17,13,15,14,10,8,12,
            10,14,11,5,9,6,13,13,11,12,15,10,16,10,15,11,15,10,11,10,13,10,11,
            10,9,11,10,5,10,10,18,13,10,13,11,10,15,12,12,15,16,12,7,12,17,11,
            10,9,8,4,11,13,5,11,9,14,12,9,7,8,11,13,9,10,8,4,7,9,
            /* CD */
            5,6,11,9,9,9,12,10,10,13,17,6,11,7,12,11,10,12,9,12,11,7,5,10,5,7,
            9,8,10,10,10,11,3,6,8,12,6,11,13,13,13,14,9,7,4,17,8,6,11,10,7,6,8,
            12,7,8,12,9,9,12,9,9,4,10,9,5,15,9,12,8,10,3,11,7,13,10,11,12,11,8,
            11,3,12,7,4,3,8,6,8,8,11,7,6,9,
            /* CE */
            20,13,6,4,7,10,7,11,11,4,14,11,7,11,8,6,6,7,7,5,14,8,9,9,12,17,7,
            12,11,11,15,3,14,12,10,4,9,7,7,14,10,6,13,10,8,9,13,10,12,7,14,8,
            12,7,7,7,9,4,6,9,9,4,7,11,7,7,4,8,4,10,4,14,6,9,7,5,13,11,8,4,5,10,
            9,8,14,8,6,11,8,12,15,6,13,10,
            /* CF */
            12,10,7,11,15,3,11,14,11,13,6,12,17,11,10,3,13,12,11,9,7,12,6,8,15,
            9,7,17,14,13,9,8,9,3,12,10,6,11,13,6,5,14,6,9,8,11,11,7,9,8,13,9,9,
            8,13,7,13,11,12,9,10,8,8,9,11,22,9,15,17,12,3,12,10,8,13,9,8,9,9,
            15,13,6,11,11,12,15,9,10,18,12,10,10,11,10,
            /* D0 */
            3,7,10,7,11,10,10,13,8,13,15,15,6,9,13,6,11,8,11,5,11,9,19,16,8,8,
            12,10,16,7,12,8,7,13,7,4,9,11,9,13,12,12,6,6,9,7,6,6,16,8,7,8,8,5,
            4,10,6,7,12,14,6,9,10,6,13,12,7,10,10,14,6,14,11,14,9,10,6,13,11,9,
            6,7,10,9,12,12,11,11,7,12,9,11,11,5,
            /* D1 */
            9,19,10,9,13,16,8,5,11,6,9,14,12,6,8,6,6,6,10,6,5,5,9,6,6,8,9,10,7,
            3,7,4,10,11,13,11,12,9,6,6,11,9,11,10,11,10,7,9,12,8,7,7,15,11,8,8,
            8,11,11,9,14,10,12,16,6,9,12,10,9,12,10,11,10,9,5,10,10,7,6,8,8,6,
            9,6,10,6,11,9,10,14,16,13,7,14,
            /* D2 */
            13,6,13,11,12,9,9,10,9,9,20,12,15,8,6,11,7,3,6,11,5,5,6,12,8,11,1,
            12,7,12,11,8,6,6,13,6,12,11,5,10,14,7,8,9,18,12,9,10,3,1,7,4,4,7,8,
            7,6,3,7,17,11,13,9,6,13,13,15,4,3,10,13,8,5,10,7,6,17,11,8,9,9,6,
            10,9,6,8,7,11,11,11,7,4,4,11,
            /* D3 */
            5,8,15,11,18,7,14,10,11,11,9,14,7,17,9,15,13,12,9,9,8,7,17,10,11,
            13,14,13,8,8,10,5,11,9,5,9,6,11,7,4,5,7,10,7,8,12,7,6,4,5,7,12,9,2,
            5,6,11,3,8,13,13,13,14,7,9,12,8,12,12,11,11,4,10,8,3,6,9,6,9,6,5,
            11,6,8,6,12,12,10,12,13,11,9,8,13,
            /* D4 */
            10,12,12,10,15,5,10,11,10,4,9,10,10,12,14,7,7,10,13,13,12,7,8,14,9,
            9,4,6,12,11,9,8,12,4,10,10,10,4,9,4,9,4,7,15,11,10,13,5,5,10,6,10,
            9,7,10,10,6,6,9,19,12,16,10,10,12,14,17,12,19,8,6,16,9,20,16,10,7,
            7,17,8,8,6,8,10,9,15,15,12,16,4,12,12,5,5,
            /* D5 */
            11,8,9,9,14,8,5,9,7,14,10,6,10,10,14,18,9,13,11,8,10,8,14,11,10,22,
            9,5,9,10,12,11,15,11,14,14,7,12,10,7,3,7,8,5,8,16,13,8,9,7,8,9,13,
            13,6,14,5,14,7,10,12,16,8,13,14,7,10,9,13,10,13,10,16,6,7,8,8,10,7,
            15,10,15,6,13,9,11,8,9,6,8,16,9,5,9,
            /* D6 */
            9,10,8,7,6,8,4,7,14,8,8,10,5,3,8,11,8,12,12,6,10,8,7,9,4,11,5,6,7,
            7,10,11,6,10,13,8,9,8,12,10,13,8,8,11,12,8,11,4,9,8,9,10,8,9,8,9,6,
            6,6,8,6,9,7,12,9,7,8,8,10,8,9,17,10,10,12,6,11,10,8,10,6,10,12,8,
            17,15,5,11,9,7,11,8,12,12,
            /* D7 */
            7,8,9,8,7,4,9,4,9,8,15,14,15,10,6,12,6,15,6,7,12,13,9,14,7,11,10,
            10,10,8,8,10,12,8,10,11,11,7,9,9,9,10,9,12,11,7,12,5,9,13,3,6,11,6,
            18,12,15,8,11,9,7,7,7,9,12,10,7,8,11,9,7,7,8,10,20,16,15,12,13,12,
            15,9,5,7,9,11,7,7,10,0,0,0,0,0,
            /* D8 */
            3,3,3,4,4,4,5,6,6,10,10,16,1,8,1,2,3,4,4,5,5,6,9,11,14,14,19,1,8,
            14,2,6,4,7,7,11,14,4,6,10,11,12,14,15,16,2,5,8,11,11,15,8,7,2,4,6,
            7,8,8,8,9,10,10,10,13,13,14,14,15,16,2,8,2,4,4,4,5,5,5,5,6,6,6,6,6,
            6,6,6,6,7,7,7,7,7,
            /* D9 */
            7,7,7,7,8,8,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,10,10,10,10,10,
            10,10,10,10,10,10,10,10,11,11,11,11,11,11,11,12,12,12,13,14,14,14,
            14,14,14,15,15,5,6,7,7,9,17,6,8,4,12,16,17,18,21,2,9,9,11,6,6,7,2,
            8,10,10,11,12,12,12,13,16,19,19,2,6,8,8,
            /* DA */
            10,2,10,10,2,5,5,5,6,6,6,7,7,7,7,7,7,8,8,8,8,8,8,8,8,8,8,8,9,9,9,9,
            10,10,10,10,10,10,10,11,11,11,11,11,11,11,11,11,11,11,12,12,12,12,
            12,13,13,14,14,14,15,15,19,2,8,2,5,5,6,6,7,7,7,7,8,9,9,10,10,10,11,
            11,11,16,5,5,5,5,6,6,7,7,7,7,
            /* DB */
            7,7,8,8,8,8,8,8,8,9,9,9,9,9,10,10,11,11,13,13,13,14,14,16,19,17,5,
            7,5,7,7,8,10,10,11,15,9,17,20,2,2,6,10,2,5,10,12,7,9,9,14,16,16,17,
            6,6,6,6,6,6,6,7,7,7,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,10,10,10,
            10,10,10,11,11,11,11,11,
            /* DC */
            11,11,11,11,11,12,12,12,12,13,13,14,14,14,15,20,21,22,3,5,5,6,6,6,
            6,6,6,6,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,
            8,8,8,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,
            9,9,9,9,
            /* DD */
            9,9,9,9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
            10,10,10,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,
            11,11,11,11,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,
            12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,14,14,14,
            14,14,14,14,
            /* DE */
            14,14,14,14,15,15,15,15,15,15,15,15,15,16,16,16,16,16,16,16,16,16,
            17,17,17,17,17,18,19,19,19,20,20,22,3,9,6,7,9,9,10,10,11,3,5,5,12,
            3,6,7,8,8,8,8,9,9,9,10,10,10,11,11,11,11,11,11,11,11,11,11,11,11,
            12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,14,14,14,14,
            /* DF */
            14,15,15,15,15,16,16,16,17,17,19,23,25,3,7,8,12,5,5,5,5,5,5,6,6,6,
            7,7,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,9,9,
            9,9,9,9,9,9,9,9,9,9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,11,11,
            11,11,11,11,11,11,11,11,11,
            /* E0 */
            11,11,11,11,11,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,
            12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
            13,13,14,14,14,14,14,14,14,14,14,15,15,15,15,15,15,15,15,15,15,15,
            16,16,16,16,16,16,17,17,19,25,3,6,6,7,7,8,9,10,11,11,16,7,8,8,8,10,
            11,11,
            /* E1 */
            11,12,14,14,15,15,6,6,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8,8,8,9,9,9,
            9,10,10,11,11,11,11,11,11,11,12,12,12,12,12,12,12,12,12,12,13,13,
            13,14,15,15,17,17,19,3,7,8,9,9,9,10,11,11,12,13,15,16,24,3,3,5,6,6,
            6,7,7,8,8,8,9,9,9,9,10,10,10,10,10,10,
            /* E2 */
            10,11,11,11,11,11,11,11,12,12,12,12,12,12,14,14,15,15,16,17,20,6,
            14,12,14,3,3,6,7,7,7,7,7,8,9,10,10,11,12,12,13,13,14,15,15,25,5,7,
            7,8,9,9,11,11,11,11,12,13,14,15,16,16,17,3,5,6,6,7,7,7,7,7,7,7,7,7,
            7,7,8,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9,
            /* E3 */
            9,9,10,10,10,10,10,10,10,10,11,11,11,11,11,11,11,11,12,12,12,12,12,
            12,12,13,13,14,15,15,15,16,16,18,8,17,4,6,7,7,7,7,9,9,10,10,10,11,
            11,11,11,11,11,12,12,13,13,13,14,3,4,8,3,6,6,6,7,7,7,7,7,7,7,7,7,7,
            7,7,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,
            /* E4 */
            9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,10,10,10,10,10,10,10,10,10,10,
            10,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,12,12,12,12,12,12,
            12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
            13,14,14,14,14,14,14,14,14,14,14,14,14,14,14,15,15,15,15,15,15,16,
            /* E5 */
            16,16,16,16,16,17,17,17,17,17,19,19,19,20,20,21,24,3,5,8,8,9,10,12,
            13,14,14,15,16,16,17,17,3,7,7,8,8,8,8,8,8,8,9,9,10,10,10,10,10,10,
            11,11,11,11,12,12,12,12,13,13,13,13,15,15,16,16,17,17,18,3,11,9,12,
            5,9,10,10,12,14,15,21,8,8,9,11,12,22,3,6,6,7,7,7,7,
            /* E6 */
            7,7,7,7,7,7,8,8,8,8,9,9,9,9,9,9,9,10,10,10,10,10,10,10,10,11,11,11,
            11,11,11,11,12,12,12,12,13,13,13,13,13,13,14,14,14,14,14,14,14,15,
            16,16,17,17,20,5,9,7,8,12,3,3,8,8,8,8,8,8,8,8,9,9,9,10,11,11,11,11,
            12,12,13,13,13,14,14,15,19,20,3,6,6,6,6,6,
            /* E7 */
            7,7,7,8,8,8,8,8,8,8,9,9,9,10,10,10,11,11,11,11,11,11,11,11,11,11,
            11,12,12,12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,14,14,14,
            14,14,15,15,15,16,16,16,16,19,3,15,3,8,10,6,6,8,8,8,9,9,9,9,9,9,9,
            9,10,10,10,10,10,10,10,10,10,11,12,12,12,12,12,12,12,12,
            /* E8 */
            12,12,13,13,13,13,13,14,14,15,15,15,15,15,15,15,16,17,17,17,18,20,
            20,13,13,14,7,7,7,7,7,8,8,8,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,
            9,9,9,9,9,9,9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,11,11,11,11,11,11,11,12,12,12,12,12,
            /* E9 */
            12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,
            13,13,13,13,14,14,14,14,14,14,14,14,14,14,14,14,14,15,15,15,15,15,
            15,15,15,16,16,16,16,16,16,16,16,16,16,16,17,17,17,17,18,13,14,8,9,
            9,9,11,11,11,12,12,14,16,7,8,9,9,9,9,9,9,9,9,9,10,10,10,10,11,12,
            12,
            /* EA */
            12,12,13,15,16,10,5,8,11,12,12,13,13,13,14,14,8,9,12,16,16,17,4,6,
            6,7,8,8,8,8,8,8,8,9,9,9,9,9,9,10,10,10,10,10,10,11,11,12,13,13,14,
            14,16,18,18,20,21,9,9,9,9,10,10,10,10,11,11,11,12,12,14,9,10,11,12,
            13,14,15,15,9,13,6,8,9,11,11,12,12,12,13,14,10,11,12,
            /* EB */
            14,17,10,10,12,12,12,13,15,16,16,22,5,6,7,7,9,10,10,11,13,4,11,13,
            12,13,15,9,15,6,7,7,7,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,9,9,9,9,10,
            10,10,10,10,10,10,10,10,11,11,11,11,11,11,12,12,12,12,12,12,12,13,
            13,13,13,13,13,13,13,14,14,14,15,15,16,17,17,17,17,
            /* EC */
            17,16,7,11,12,13,13,16,9,9,12,13,16,16,4,13,13,17,12,15,16,8,10,10,
            10,11,11,13,14,7,8,8,8,9,9,9,9,9,10,10,11,11,11,12,12,13,13,13,13,
            13,13,13,13,14,15,15,15,15,16,16,16,18,21,30,4,11,13,16,8,8,9,11,
            12,4,7,8,8,9,9,9,9,9,9,9,10,10,12,12,13,14,16,21,7,7,
            /* ED */
            9,10,10,10,10,10,10,11,13,13,14,16,16,17,17,24,4,6,8,9,12,7,8,8,9,
            9,9,9,9,9,9,10,10,10,10,10,10,10,10,10,10,11,11,11,11,11,11,11,11,
            12,13,13,13,13,13,14,14,14,14,14,15,15,15,16,16,17,17,18,19,18,21,
            11,12,17,19,8,9,9,9,9,9,10,10,10,11,11,11,11,12,12,12,12,13,13,
            /* EE */
            13,13,14,14,14,14,15,15,16,16,16,17,18,7,8,9,9,9,10,12,13,17,9,10,
            10,12,13,14,14,16,17,17,10,16,23,5,6,6,7,7,7,8,8,8,8,8,8,9,9,9,9,9,
            9,9,9,9,9,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
            11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,
            /* EF */
            11,11,11,11,11,11,11,11,12,12,12,12,12,12,12,12,12,12,12,12,12,12,
            12,12,12,13,13,13,13,13,13,13,13,13,13,13,13,14,14,14,14,14,14,14,
            14,14,14,14,14,15,15,15,15,15,15,15,15,16,16,16,16,16,16,16,16,17,
            17,17,17,17,17,17,17,17,17,18,18,18,19,20,14,9,12,13,9,9,10,10,11,
            12,12,12,13,13,
            /* F0 */
            15,15,16,17,18,22,9,11,12,13,17,10,11,7,7,8,9,9,10,10,10,10,10,10,
            11,11,11,11,11,12,12,12,12,12,12,13,13,13,13,13,14,14,14,14,14,15,
            15,16,16,16,17,17,17,17,18,18,22,5,7,7,8,8,9,9,10,10,10,10,10,10,
            10,10,11,11,12,12,12,12,12,12,13,13,13,13,13,13,13,14,14,14,14,14,
            14,14,
            /* F1 */
            15,15,15,15,16,16,16,16,16,16,16,16,17,18,18,18,18,21,23,11,12,8,8,
            9,9,10,11,13,13,14,14,14,15,5,8,9,9,9,9,10,11,11,11,11,12,12,12,12,
            13,13,13,13,13,13,14,14,14,14,14,15,15,16,17,19,24,5,9,11,12,9,6,9,
            10,12,12,13,14,15,15,16,16,22,12,8,11,11,11,12,15,16,12,9,10,10,
            /* F2 */
            12,12,12,12,13,15,15,16,16,16,18,20,21,6,10,7,8,9,9,9,9,10,10,10,
            10,10,10,10,10,10,10,11,11,11,11,11,11,11,11,11,11,11,12,12,12,12,
            12,12,12,12,12,12,12,12,13,13,13,13,13,13,13,13,14,14,14,14,14,14,
            14,14,14,14,14,14,14,14,15,15,15,15,15,15,15,15,15,15,15,15,15,15,
            16,16,16,16,
            /* F3 */
            16,16,16,16,16,16,17,17,17,17,17,17,17,17,17,17,17,18,18,18,18,19,
            19,19,19,20,21,24,26,6,14,17,17,10,8,9,9,9,10,10,10,10,10,11,11,11,
            11,11,11,11,11,11,11,11,11,12,12,12,12,12,12,13,13,13,13,13,13,14,
            14,14,14,14,14,14,14,14,14,14,14,15,15,15,15,16,16,16,16,16,17,17,
            17,17,17,17,
            /* F4 */
            18,18,18,19,19,19,8,9,11,12,10,10,9,9,9,10,10,10,10,11,11,11,11,12,
            13,13,14,15,17,18,19,10,10,11,13,13,19,11,11,13,15,15,16,9,10,10,
            11,11,12,12,13,14,14,14,15,15,15,15,15,16,18,6,15,9,11,12,14,14,15,
            15,16,17,6,12,14,14,17,25,11,19,9,12,13,13,23,11,15,10,11,9,10,10,
            10,12,
            /* F5 */
            12,12,13,13,13,14,14,14,14,14,15,15,16,16,16,17,17,18,19,19,19,20,
            20,21,7,16,10,13,14,18,18,10,10,11,11,11,12,12,12,12,12,12,12,12,
            13,13,13,13,13,13,13,14,14,15,15,15,15,15,15,15,15,16,16,16,16,16,
            16,16,16,17,17,17,19,19,19,19,19,20,21,22,22,23,24,7,12,13,13,17,
            17,11,11,12,12,13,
            /* F6 */
            13,14,15,13,18,12,11,12,12,14,14,16,16,16,19,19,20,22,10,13,13,13,
            14,14,15,15,17,8,12,20,8,10,10,13,14,18,18,14,14,15,16,17,18,18,21,
            24,12,12,13,13,13,13,13,13,13,13,14,14,14,14,14,14,14,14,15,15,15,
            15,15,15,15,15,15,15,16,16,16,16,16,16,16,16,16,16,16,16,17,17,17,
            17,17,17,17,17,
            /* F7 */
            18,18,18,18,18,19,19,19,19,19,19,20,20,20,21,14,14,15,15,16,18,18,
            18,19,19,13,13,14,14,14,15,15,17,17,18,18,19,19,22,14,14,15,16,16,
            17,19,12,15,18,22,22,10,13,14,15,15,16,16,16,18,19,20,23,25,14,15,
            17,13,16,16,17,19,19,21,23,17,17,17,18,18,19,20,20,20,20,21,17,18,
            20,23,23,16,17,23
    /* F8 */
    };

}
