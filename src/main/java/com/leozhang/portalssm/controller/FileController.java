package com.leozhang.portalssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.leozhang.portalssm.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {
    /**
     * path : public file directory
     */

    public static String fileFolder = "G:\\public\\";

    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file")MultipartFile multipartFile) throws IOException {

        String baseFileName = multipartFile.getOriginalFilename();
        assert baseFileName != null;
        String ext = baseFileName.substring(baseFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString()+ext;
        File f = new File(fileFolder,newFileName);

        multipartFile.transferTo(f);
        JSONObject data = new JSONObject();
        data.put("url","public/"+newFileName);
        data.put("name",newFileName);
        return Result.end(200,data,"good",0);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("url")String url){
//        System.out.println(url);
        String baseFileName = url.substring(url.lastIndexOf("/")+1);
        String path = fileFolder+baseFileName;
        File f = new File(path);
        if (f.exists()){
            f.delete();
//            System.out.println(1);
        }
//        System.out.println(111111111);
        return Result.end(200,null,"good",0);
    }

}
