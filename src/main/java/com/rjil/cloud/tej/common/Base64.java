package com.rjil.cloud.tej.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This class will encode the binary data into base 64 encoding format and
 * decode the string data into byte array.
 */

public class Base64 {

    private static String sString;
    /**
     * Represents base-64 transfer encoding character set.
     */
    private static final char[] CHAR_TABS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
            .toCharArray();

    /**
     * This method encodes given data into Base-64 encoding.
     *
     * @param data data in byte array format that is to be encoded
     * @return base-64 encoded data
     */
    public static String encode(byte[] data) {

        return encode(data, 0, data.length, null).toString();
    }

    /**
     * Encodes the part of the given byte array denoted by start and len to the
     * Base64 format. The encoded data is appended to the given StringBuffer. If
     * no StringBuffer is given, a new one is created automatically. The
     * StringBuffer is the return value of this method.
     *
     * @param data  data in byte array format that is to be encoded
     * @param start Start index from which data is to be encoded
     * @param len   Length of data to be encoded
     * @param buf   The encoded data is appended to this StringBuffer
     * @return StringBuffer that is represented
     */

    public static StringBuffer encode(byte[] data, int start, int len,
                                      StringBuffer buf) {

        if (buf == null)
            buf = new StringBuffer(data.length * 3 / 2);

        int end = len - 3;
        int i = start;
        int n = 0;

        while (i <= end) {
            int d = ((((int) data[i]) & 0x0ff) << 16)
                    | ((((int) data[i + 1]) & 0x0ff) << 8)
                    | (((int) data[i + 2]) & 0x0ff);

            buf.append(CHAR_TABS[(d >> 18) & 63]);
            buf.append(CHAR_TABS[(d >> 12) & 63]);
            buf.append(CHAR_TABS[(d >> 6) & 63]);
            buf.append(CHAR_TABS[d & 63]);

            i += 3;

            if (n++ >= 14) {
                n = 0;
                // buf.append("\r\n");
            }
        }

        if (i == start + len - 2) {
            int d = ((((int) data[i]) & 0x0ff) << 16)
                    | ((((int) data[i + 1]) & 255) << 8);

            buf.append(CHAR_TABS[(d >> 18) & 63]);
            buf.append(CHAR_TABS[(d >> 12) & 63]);
            buf.append(CHAR_TABS[(d >> 6) & 63]);
            buf.append("=");
        } else if (i == start + len - 1) {
            int d = (((int) data[i]) & 0x0ff) << 16;

            buf.append(CHAR_TABS[(d >> 18) & 63]);
            buf.append(CHAR_TABS[(d >> 12) & 63]);
            buf.append("==");
        }

        return buf;
    }

    /**
     * Decodes the given character which is into base-64 format
     *
     * @param c Character to be decoded
     * @return Decoded int
     */
    private static int decode(char c) {

        if (c >= 'A' && c <= 'Z')
            return ((int) c) - 65;
        else if (c >= 'a' && c <= 'z')
            return ((int) c) - 97 + 26;
        else if (c >= '0' && c <= '9')
            return ((int) c) - 48 + 26 + 26;
        else
            switch (c) {
                case '+':
                    return 62;
                case '/':
                    return 63;
                case '=':
                    return 0;
                default:
                    throw new RuntimeException("unexpected code: " + c
                            + "---X" + (byte) c + "index=="
                            + sString.indexOf(c));
            }
    }

    /**
     * Decodes the given Base64 encoded String to a new byte array. The byte
     * array holding the decoded data is returned.
     *
     * @param s String to be decoded
     * @return byte array representation of decoded String
     */
    public static byte[] decode(String s) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {

            decode(s, bos);

        } catch (Exception e) {
            throw new RuntimeException();
        }
        return bos.toByteArray();
    }

    /**
     * Decodes the given Base64 encoded String to a new byte array. The byte
     * array holding the decoded data is returned.
     *
     * @param s  String to be decoded
     * @param os OutputStream to which byte array representation of decoded
     *           String is written
     * @throws IOException if problem with OutputStream
     */

    public static void decode(String s, OutputStream os) throws IOException {

        int i = 0;
        sString = s;
        int len = s.length();

        while (true) {
            while (i < len && s.charAt(i) <= ' ')
                i++;

            if (i == len)
                break;

            int tri = (decode(s.charAt(i)) << 18)
                    + (decode(s.charAt(i + 1)) << 12)
                    + (decode(s.charAt(i + 2)) << 6)
                    + (decode(s.charAt(i + 3)));

            os.write((tri >> 16) & 255);
            if (s.charAt(i + 2) == '=')
                break;
            os.write((tri >> 8) & 255);
            if (s.charAt(i + 3) == '=')
                break;
            os.write(tri & 255);

            i += 4;
        }
    }

    /**
     * This method is used to encode a string, it internally calls encode method
     * with byte[] as argument.
     *
     * @param input byte array for photo
     * @return encoded string.
     */
    public static String b64encode(String input) {

        return Base64.encode(input.getBytes());
    }

    /**
     * This method is used to decode strings and returns decoded data in byte[]
     *
     * @param input string for photo
     * @return converted byte array
     */
    public static byte[] b64decode(String input) {

        return Base64.decode(input);
    }
}
