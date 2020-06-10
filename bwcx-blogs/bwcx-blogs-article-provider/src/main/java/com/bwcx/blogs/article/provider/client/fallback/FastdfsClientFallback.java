package com.bwcx.blogs.article.provider.client.fallback;

import com.bwcx.blogs.article.provider.client.FastdfsClient;
import com.bwcx.blogs.commons.entity.BWCXResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FastdfsClientFallback implements FastdfsClient {

    private BWCXResult bwcxResult = new BWCXResult(false, 500, "网络异常,请重试");

    @Override
    public BWCXResult uploadFile(MultipartFile file) {
        return bwcxResult;
    }

    @Override
    public BWCXResult deleteByPath(String filePathName) {
        return bwcxResult;
    }
}
