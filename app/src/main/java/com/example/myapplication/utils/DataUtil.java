package com.example.myapplication.utils;

import java.text.DecimalFormat;

/**
 * 数据转换工具
 **/
public class DataUtil {


    /**
     * 保留浮点数小数点后面几位
     *
     * @param f      浮点数
     * @param format 浮点数的格式
     * @return
     */
    public static String formatFloat(float f, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(f);
    }

    /**
     * 将两个字节拼接还原成有符号的整型数据
     * 数据域中的数据都按小端存储，示例：数据0x1234，则Byte0为0x34,Byte1为0x12
     *
     * @param byte1
     * @param byte2
     * @return
     */
    public static int pinJie2ByteToInt(byte byte1, byte byte2) {
        int result = byte1;
        result = (result << 8) | (0x00FF & byte2);
        return result;
    }


    public static short parseByteToInt(short byte1, short byte2) {
        short result = byte2;
        result = (short)(((0xFF & result) << 8) | (0xFF & byte1));
//        LogUtil.log("解析结果 byte1： " + byte1 + " byte1： " + byte2 + " result： " + result);
        return result;
    }

    /**
     * 整型数据拆分为长度为2的字节数组，高8位存放在序号小的元素，高8位存放在序号大的元素中(大端存储)
     *
     * @param data
     * @return
     */
    public static byte[] chaiFenDataIntTo2Byte(int data) {
        byte[] byteArray = new byte[2];
        byteArray[0] = (byte) (data >> 8);
        byteArray[1] = (byte) data;
        return byteArray;
    }

    /**
     * @param data
     * @return
     */
    public static byte[] chaiFenDataIntTo2Byte2(int data) {
        byte[] byteArray = new byte[2];
        byteArray[0] = (byte) data;
        byteArray[1] = (byte) (data >> 8);
        return byteArray;
    }

    /**
     * 将byte数组转成十六进制形式的字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
    }

    /**
     * 取字节的后5位[4:0]表示斜率
     *
     * @param xoctByte
     * @return
     */
    public static int getXoctInt(byte xoctByte) {

        //或上 & 0x1F 取后五位
        int result = xoctByte & 0x1F;
        return result;

    }

    /**
     * 将斜率整型0-8 添加到字节的[4:0]位上去
     *
     * @param xoctInt
     * @return
     */
    public static byte setIntXoctToByte(byte xoctByte, int xoctInt) {

        //先取int的后五位保留
        byte byteInt = (byte) xoctInt;
        byteInt = (byte) (byteInt & 0x1F);//取后五位

        //保留原先byte的前两位
        xoctByte = (byte) (xoctByte & 0xC0);

        //将前两位与后五位拼接形成一个新的，按需求要的byte

        xoctByte = (byte) (xoctByte | byteInt);

        return xoctByte;

    }

    /**
     * 取字节的前两位[7:6]表示类型
     *
     * @param xoctByte
     * @return
     */
    public static int getTypeInt(byte xoctByte) {

        int result = (xoctByte >> 6) & 0x03;
        return result;

    }

    /**
     * 将斜率整型0-3 添加到字节的[7:06]位上去
     *
     * @param xoctInt
     * @return
     */
    public static byte setIntTypeToByte(byte xoctByte, int xoctInt) {


        byte byteInt = (byte) xoctInt;
        byteInt = (byte) ((byteInt & 0x03) << 6);

        //保留原先byte的后五位
        xoctByte = (byte) (xoctByte & 0x1F);

        //将前两位与后五位拼接形成一个新的，按需求要的byte

        xoctByte = (byte) (xoctByte | byteInt);

        return xoctByte;

    }

    /**
     * int转换为小端byte[]（高位放在高地址中）
     *
     * @param iValue
     * @return
     */
    public static byte[] Int2Bytes_LE(int iValue) {
        byte[] rst = new byte[4];
        // 先写int的最后一个字节
        rst[0] = (byte) (iValue & 0xFF);
        // int 倒数第二个字节
        rst[1] = (byte) ((iValue & 0xFF00) >> 8);
        // int 倒数第三个字节
        rst[2] = (byte) ((iValue & 0xFF0000) >> 16);
        // int 第一个字节
        rst[3] = (byte) ((iValue & 0xFF000000) >> 24);
        return rst;
    }

    /**
     * int转换为小端byte[2]（高位放在高地址中）
     *
     * @param iValue
     * @return
     */
    public static byte[] Int2Bytes2_LE(int iValue) {
        byte[] rst = new byte[2];
        // 先写int的最后一个字节
        rst[0] = (byte) (iValue & 0xFF);
        // int 倒数第二个字节
        rst[1] = (byte) ((iValue & 0xFF00) >> 8);
        return rst;
    }


    /**
     * 转换byte数组为int（小端）
     *
     * @return
     * @note 数组长度至少为4，按小端方式转换,即传入的bytes是小端的，按这个规律组织成int
     */
    public static int bytes2Int_LE(byte[] bytes) {
        if (bytes.length < 4)
            return -1;
        int iRst = (bytes[0] & 0xFF);
        iRst |= (bytes[1] & 0xFF) << 8;
        iRst |= (bytes[2] & 0xFF) << 16;
        iRst |= (bytes[3] & 0xFF) << 24;
        return iRst;
    }


    /**
     * 以大端模式将byte[]转成int
     */
    public static int bytesToIntBig(byte[] src, int index) {
        int value;
        value = (int) (((src[index] & 0xFF) << 24)
                | ((src[1 + index] & 0xFF) << 16)
                | ((src[2 + index] & 0xFF) << 8)
                | (src[3 + index] & 0xFF));
        return value;
    }

    public static int bytesToIntBig(byte[] src) {
        return bytesToIntBig(src, 0);
    }

    /**
     * 以大端模式将int转成byte[]
     */
    public static byte[] intToBytesBig(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    public static byte[] longToBytes(long l) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) (l & 0xFF);
            l >>= 8;
        }
        return result;

    }

    public static long bytesToLong(byte[] b) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            result <<= 8;
            result |= (b[i] & 0xFF);
        }
        return result;
    }
}