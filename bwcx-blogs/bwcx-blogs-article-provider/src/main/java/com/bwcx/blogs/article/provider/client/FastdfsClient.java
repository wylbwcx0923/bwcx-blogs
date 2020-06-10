package com.bwcx.blogs.article.provider.client;

import com.bwcx.blogs.article.provider.client.fallback.FastdfsClientFallback;
import com.bwcx.blogs.article.provider.config.FeignSupportConfig;
import com.bwcx.blogs.commons.entity.BWCXResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "bwcx-blogs-fastdfs", fallback = FastdfsClientFallback.class, configuration = FeignSupportConfig.class)
@Component
public interface FastdfsClient {

    /**
     * 文件上传
     */
    @RequestMapping(value = "/bwcx/fastdfs/uploadFile"
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
            , method = RequestMethod.POST)
    BWCXResult uploadFile(@RequestPart(value = "file") MultipartFile file);

    /**
     * 文件删除
     */
    @RequestMapping(value = "/bwcx/fastdfs/deleteByPath", method = RequestMethod.GET)
    BWCXResult deleteByPath(@RequestParam(value = "filePathName") String filePathName);

}
