package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * URL: /file/upload
     * 类型: post
     * 参数: file=字节信息
     * 返回值: SysResult(ImageVO)
     * 知识点:
     * SpringMVC针对与IO操作开发了MultipartFile
     * 底层实现就是常规IO流,简化了用户的操作过程.无需手动关流
     * SpringMVC中默认支持最大1M
     * 步骤:
     * 1.获取文件名称
     * 2.准备文件路径
     * 3.准备文件上传的全路径
     * 4.实现文件上传操作
     */

//    @PostMapping("/upload")
//    public SysResult upload(MultipartFile file) {
//        //获取文件名称 a.jpg
//        String filename = file.getOriginalFilename();
//        //准备文件目录
//        String dir = "H:\\JavaDevelop\\BackEndCode\\jT\\images";
//        File dirFile = new File(dir);
//        if (!dirFile.exists()) { //判断目录是否存在
//            dirFile.mkdirs(); //创建多级目录
//        }
//
//        String path = dir + "/" + filename;
//        try {
//            //实现文件上传
//            file.transferTo(new File(path)); //核心
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return SysResult.success();
//
//
//    }
    @PostMapping("/upload")
    public SysResult fileUpload(MultipartFile file) {

        ImageVO imageVO = fileService.upload(file);
        if (imageVO == null) {
            return SysResult.fail();
        }
        return SysResult.success(imageVO);
    }


}
