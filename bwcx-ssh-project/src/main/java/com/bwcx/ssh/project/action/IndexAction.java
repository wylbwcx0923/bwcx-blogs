package com.bwcx.ssh.project.action;

import com.bwcx.ssh.project.utils.ResponseUtil;
import com.bwcx.ssh.project.utils.TimeUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyuliang
 */
public class IndexAction extends ActionSupport {
    private HttpServletRequest request = ServletActionContext.getRequest();

    public void index() {
        String name = request.getParameter("name");
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("date", TimeUtil.date2String(new Date()));
        ResponseUtil.resopnseJson(map);
    }
}
