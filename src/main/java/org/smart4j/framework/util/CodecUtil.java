package org.smart4j.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class CodecUtil {
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Logger.error(CodecUtil.class, "encode url failure", e);
            throw new RuntimeException(e);
        }

        return target;
    }

    public static String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Logger.error(CodecUtil.class, "decode url failure", e);
            throw new RuntimeException(e);
        }

        return target;
    }
}
