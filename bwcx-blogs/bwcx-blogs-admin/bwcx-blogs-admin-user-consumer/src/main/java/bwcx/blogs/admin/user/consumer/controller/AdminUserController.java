package bwcx.blogs.admin.user.consumer.controller;

import bwcx.blogs.admin.user.consumer.client.AdminUserClient;
import bwcx.blogs.admin.user.consumer.entity.AdminUser;
import com.bwcx.blogs.commons.entity.BWCXResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bwcx/admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserClient adminUserClient;

    @GetMapping(value = "find/{id}")
    public BWCXResult findById(@PathVariable Integer id) {
        return adminUserClient.findById(id);
    }

    @PostMapping(value = "/register")
    public BWCXResult adminRegister(@RequestBody AdminUser adminUser) {
        return adminUserClient.adminRegister(adminUser);
    }

    @PostMapping(value = "/login")
    public BWCXResult adminLogin(@RequestParam(value = "username",required = false) String username
            , @RequestParam(value = "password",required = false) String password) {
        return adminUserClient.adminLogin(username, password);
    }

    @DeleteMapping(value = "/del/{id}")
    public BWCXResult delById(@PathVariable Integer id) {
        return adminUserClient.delById(id);
    }

    @PutMapping(value = "/update")
    public BWCXResult update(@RequestBody AdminUser adminUser) {
        return adminUserClient.update(adminUser);
    }

    @GetMapping(value = "page/{pageIndex}/{pageSize}")
    public BWCXResult getList(@PathVariable Integer pageIndex,
                              @PathVariable Integer pageSize) {
        return adminUserClient.getList(pageIndex, pageSize);
    }


}
