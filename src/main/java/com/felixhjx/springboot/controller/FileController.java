package com.felixhjx.springboot.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.felixhjx.springboot.common.Result;
import com.felixhjx.springboot.entity.Files;
import com.felixhjx.springboot.entity.User;
import com.felixhjx.springboot.mapper.FilesMapper;
import com.felixhjx.springboot.service.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * File Up&Download interface
 */
@RestController
@RequestMapping("/file")
public class FileController {

    //传入配置文件中的路径
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Value("${server.ip}")
    private String serverIp;

    @Resource
    private FilesMapper filesMapper;

    //    根究Id删除用户信息
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Files files = filesMapper.selectById(id);
        files.setDelete(true);
        filesMapper.updateById(files);
        return Result.success();
    }


    @PostMapping("/del/batch")
    public Result delBatch(@RequestBody List<Integer> ids) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids);
        List<Files> files=filesMapper.selectList(queryWrapper);
        for(Files file:files){
            file.setDelete(true);
            filesMapper.updateById(file);
        }
        return Result.success();
    }


    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type= FileUtil.extName(originalFilename);
        long size = file.getSize();
        //储存磁盘
        File uploadParentFile = new File(fileUploadPath);

        //文件目录不存在就创建新目录
        if(!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }
        //定义上传文件的唯一标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID=uuid+StrUtil.DOT+type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        file.transferTo(uploadFile);

        String url="http://"+serverIp+":9090/file/"+fileUUID;
        //储存到数据库中
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setDelete(false);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);
        filesMapper.insert(saveFile);
        return url;
    }

    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        File uploadFile=new File(fileUploadPath+fileUUID);
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("content-Disposition","attachment;filename="+URLEncoder.encode(fileUUID,"UTF-8"));
        response.setContentType("application/octet-stream");

        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    /**
     * * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name){
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete",false);
        queryWrapper.orderByDesc("id");
        if (!"".equals(name)){
            queryWrapper.like("name",name);
        }

        return Result.success(filesMapper.selectPage(new Page<>(pageNum,pageSize),queryWrapper));
    }

    /**
     * 更新
     * * @param Files
     * @return Result
     */
    @PostMapping("/update")
    public Result save(@RequestBody Files files) {
        return Result.success(filesMapper.updateById(files));
    }
}
