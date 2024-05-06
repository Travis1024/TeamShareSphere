package org.travis.media.service;

import io.minio.errors.*;
import org.travis.api.dto.file.FileInfoSlimDTO;
import org.travis.api.dto.file.MediaSuccessInfoDTO;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MediaService
 * @Description MediaService
 * @Author travis-wei
 * @Version v1.0
 * @Data 2024/5/4
 */
public interface MediaService {
    void handlerMedia(FileInfoSlimDTO fileInfoSlimDTO);
    MediaSuccessInfoDTO handlerKms(FileInfoSlimDTO fileInfoSlimDTO, String tempFileFolder) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
