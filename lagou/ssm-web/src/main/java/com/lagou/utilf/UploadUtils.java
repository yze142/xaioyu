package com.lagou.utilf;

import com.lagou.domain.ResponseResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class UploadUtils {

    public ResponseResult fileUpload( MultipartFile file, HttpServletRequest request) throws IOException {

//获取项目部署路径 / apache-tomcat-8.5.56 webapps ssm_web
        String realPath = request.getServletContext().getRealPath("/");//获取路径，截取字符串
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));
//获取源文件名字
        String originalFilename = file.getOriginalFilename();
        //生成新文件名字
        //currentTimeMillis根据当前时间生成一个新名字 后面的substring就是截取文件名字 。后面的文件后缀
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //最后就是文件上传，首先给要指定的路径apache-tomcat-8.5.56 webapps upload
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);
        //如果不存在就生成一个目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:" + filePath);
        }
        /*//图片实现真正的上传
         *
         * */
        file.transferTo(filePath);
        //将文件名和文件路径返回，进行响应
        HashMap<String, String> map = new HashMap<>();
        map.put("FileName", newFileName);
        //文件路径和文件名
        map.put("FilePath", " http://localhost:8080/upload/" + newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200, "上传成功", map);
//上传成功响应一个数据
        return responseResult;
    }

}
