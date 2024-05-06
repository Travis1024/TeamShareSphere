package org.travis.media.constants;

/**
 * @ClassName MediaConstant
 * @Description MediaConstant
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/5
 */
public class MediaConstant {
    /**
     * 音视频临时文件名称（不包含后缀）
     */
    public static final String MEDIA_FILE_MIDDLE_NAME = "middle";
    /**
     * 音视频临时文件名称（不包含后缀）
     */
    public static final String MEDIA_FILE_TARGET_NAME = "target";

    /**
     * 加密秘钥文件名称（包含后缀）
     */
    public static final String KEY_FILE_NAME = "encrypt.key";

    /**
     * 加密秘钥 INFO 文件名称（包含后缀）
     */
    public static final String KEY_INFO_FILE_NAME = "encrypt.keyinfo";

    /**
     * M3U8 文件名称
     */
    public static final String M3U8_FILE_NAME = "output.m3u8";

    /**
     * key 秘钥获取链接
     */
    public static final String KEY_FILE_GET_URL = "http://#{gatewayAddr}/file/mediaInfo/getKeyFile?fileId=#{fileId}&token=#{token}";

    /**
     * m3u8 文件中 ts 文件前缀
     */
    public static final String TS_URL_PREFIX = "http://#{minioAddr}/#{minioFolder}/";
}
