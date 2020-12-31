package com.dawning.gridview.app.jobscheduler.commonresource.dataandmethod.export.data;

import java.util.Map;

/**
 * @author wangyuliang
 * @date 2020年09月03日14:30:22
 * slurm和gold的VO类
 */
public class SlurmData {
    /**
     * 集群
     */
    private String Cluster;
    /**
     * 账号名
     */
    private String Account;
    /**
     * 绑定用户
     */
    private String User;
    /**
     * 优先级
     */
    private String Partition;

    private String Share;

    private String Priority;
    /**
     * 最大运行作业
     */
    private String GrpJobs;
    /**
     * 最大提交作业数
     */
    private String GrpSubmit;

    private String GrpWall;
    private String GrpTRESMins;
    private String MaxJobs;
    private String MaxTRES;
    private String MaxTRESPerNode;
    private String MaxSubmit;
    private String MaxWall;
    private String MaxTRESMins;
    private String QOS;
    private String GrpTRESRunMins;
    /**
     * assoc 资源对象
     */
    private Map<String, String> grpTRES;

    public String getCluster() {
        return Cluster;
    }

    public void setCluster(String cluster) {
        Cluster = cluster;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPartition() {
        return Partition;
    }

    public void setPartition(String partition) {
        Partition = partition;
    }

    public String getShare() {
        return Share;
    }

    public void setShare(String share) {
        Share = share;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getGrpJobs() {
        return GrpJobs;
    }

    public void setGrpJobs(String grpJobs) {
        GrpJobs = grpJobs;
    }


    public String getGrpSubmit() {
        return GrpSubmit;
    }

    public void setGrpSubmit(String grpSubmit) {
        GrpSubmit = grpSubmit;
    }

    public String getGrpWall() {
        return GrpWall;
    }

    public void setGrpWall(String grpWall) {
        GrpWall = grpWall;
    }

    public String getGrpTRESMins() {
        return GrpTRESMins;
    }

    public void setGrpTRESMins(String grpTRESMins) {
        GrpTRESMins = grpTRESMins;
    }

    public String getMaxJobs() {
        return MaxJobs;
    }

    public void setMaxJobs(String maxJobs) {
        MaxJobs = maxJobs;
    }

    public String getMaxTRES() {
        return MaxTRES;
    }

    public void setMaxTRES(String maxTRES) {
        MaxTRES = maxTRES;
    }

    public String getMaxTRESPerNode() {
        return MaxTRESPerNode;
    }

    public void setMaxTRESPerNode(String maxTRESPerNode) {
        MaxTRESPerNode = maxTRESPerNode;
    }

    public String getMaxSubmit() {
        return MaxSubmit;
    }

    public void setMaxSubmit(String maxSubmit) {
        MaxSubmit = maxSubmit;
    }

    public String getMaxWall() {
        return MaxWall;
    }

    public void setMaxWall(String maxWall) {
        MaxWall = maxWall;
    }

    public String getMaxTRESMins() {
        return MaxTRESMins;
    }

    public void setMaxTRESMins(String maxTRESMins) {
        MaxTRESMins = maxTRESMins;
    }

    public String getQOS() {
        return QOS;
    }

    public void setQOS(String QOS) {
        this.QOS = QOS;
    }

    public String getGrpTRESRunMins() {
        return GrpTRESRunMins;
    }

    public void setGrpTRESRunMins(String grpTRESRunMins) {
        GrpTRESRunMins = grpTRESRunMins;
    }

    public Map<String, String> getGrpTRES() {
        return grpTRES;
    }

    public void setGrpTRES(Map<String, String> grpTRES) {
        this.grpTRES = grpTRES;
    }
}
