package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ImageVO upload(MultipartFile file);
}
