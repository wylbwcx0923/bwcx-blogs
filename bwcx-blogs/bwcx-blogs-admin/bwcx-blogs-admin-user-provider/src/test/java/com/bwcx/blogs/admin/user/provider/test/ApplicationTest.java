package com.bwcx.blogs.admin.user.provider.test;

import bwcx.blogs.admin.user.provider.AdminUserProviderApplication;
import bwcx.blogs.admin.user.provider.mapper.AdminUserMapper;
import com.bwcx.blogs.commons.utils.HttpClientUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.UUID;

import static org.assertj.core.internal.bytebuddy.utility.StreamDrainer.DEFAULT_BUFFER_SIZE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminUserProviderApplication.class)
public class ApplicationTest {

    @Autowired
    private AdminUserMapper adminUserMapper;

}
