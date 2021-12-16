package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Service
public class FileServiceImpl implements FileService {
    /**
     * 思路:
     * 1.校验文件是否为图片  校验图片类型
     * 2.防止文件为恶意程序  木马.exe.jpg
     * 3.分目录存储         按照时间维度划分
     * 4.防止文件重名       UUID
     *
     * @param file
     * @return
     */
    @Override
    public ImageVO upload(MultipartFile file) {
        //1.获取图片名称,全部转化为小写
        String filename = file.getOriginalFilename().toLowerCase();
        if (!filename.matches("^.+\\.(jpg|png|gif)$"))  //   在Java中 \\ 代表 一个 \
        {
            return null;
        }

        //2.通过校验宽度和高度判断是否为图片 bufferedImage图片包装对象
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width == 0 || height == 0) {
                return null;
            }
        } catch (Exception e) {
            //一般条件下为了不影响代码结构,将检查异常,转化为运行时异常
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return null;
    }
}
