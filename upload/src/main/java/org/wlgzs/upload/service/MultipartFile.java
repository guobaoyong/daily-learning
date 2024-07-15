package org.wlgzs.upload.service;

import org.springframework.core.io.InputStreamSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zsh
 * @company wlgzs
 * @create 2018-12-15 16:19
 * @Describe
 */
public interface MultipartFile extends InputStreamSource {
    String getName();
    String getOriginalFilename();
    String getContentType();
    boolean isEmpty();
    long getSize();
    byte[] getBytes() throws IOException;
    InputStream getInputStream() throws IOException;
    void transferTo(File dest) throws IOException, IllegalStateException;
}
