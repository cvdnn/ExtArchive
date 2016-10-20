package com.capt.util
/**
 * Created by handy on 16-10-19.
 */

public final class TextUtils {

    private static final String TAG = "TextUtils"

    private static final byte MIX_XOR = 0x5A
    private static final def MIX_MASK = [0x15, 0x07, 0x05, 0x11, 0x09, 0x0A, 0x13, 0x06] as byte[]
    private static final int MIX_GROUP = MIX_MASK.length

    public static final String SEPARATOR_COMMA = ','

/**
 * 默认加密
 *
 * @param text
 * @return
 */
    public static String toFake(String text) {
        String result = ''

        if (Assert.notEmpty(text)) {
            byte[] b = text.getBytes();
            for (int i = 0; i < b.length; i++) {
                b[i] = (byte) ((b[i] ^ MIX_XOR) + MIX_MASK[i % MIX_GROUP]);
            }

            result = b.encodeBase64().toString();
        }

        return result;
    }

/**
 * 默认解密
 *
 * @param text
 * @return
 */
    public static String fromFake(String text) {
        String result = '';

        if (Assert.notEmpty(text)) {
            byte[] des = text.decodeBase64();
            if (Assert.notEmpty(des)) {
                for (int i = 0; i < des.length; i++) {
                    des[i] = (byte) ((des[i] - MIX_MASK[i % MIX_GROUP]) ^ MIX_XOR);
                }

                result = new String(new String(des));
            }

        }

        return result;
    }

/**
 * 救值, 空字符串返回null
 *
 * @param value
 * @return
 */
    public static String toString(String value) {

        return Assert.notEmpty(value) ? value : "null";
    }

    public static int getCharsLength(CharSequence text) {

        return Assert.notEmpty(text) ? text.length() : 0;
    }

    public static int getLength(Object... objs) {
        int length = 0;
        if (Assert.notEmpty(objs)) {
            for (Object o : objs) {
                if (o != null) {
                    length += getCharsLength(o.toString());
                }
            }

        }

        return length;
    }

/**
 * 字符串按照分割符分组
 *
 * @param text
 * @return
 */
    public static String[] blockSort(String text) {

        return blockSort(text, SEPARATOR_COMMA);
    }

/**
 * 字符串按照分割符分组
 *
 * @param text
 * @param boundary
 * @return
 */
    public static String[] blockSort(String text, String boundary) {
        String[] result = null;
        if (Assert.notEmpty(text)) {
            String temp = text.replace(" ", "");
            if (Assert.notEmpty(temp)) {
                result = temp.split(Assert.notEmpty(boundary) ? boundary : SEPARATOR_COMMA);
            }
        }

        return result;
    }
}
