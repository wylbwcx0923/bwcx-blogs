package com.sugon.slurm.account.user.entity;

import java.util.Map;

/**
 * @author wangyuliang
 * @date 2020年09月03日14:30:22
 * slurm和gold的VO类
 */
public class SlurmData {
    private String Cluster,//集群
            Account,//账号
            User,//用户
            Partition,//分区
            Share,//共享
            Priority,//优先级
            GrpJobs,//最大运行作业数
            GrpSubmit,//最大提交作业数
            GrpWall,
            GrpTRESMins,
            MaxJobs,//最大作业数
            MaxTRES,
            MaxTRESPerNode,
            MaxSubmit,
            MaxWall,
            MaxTRESMins,
            QOS,
            GrpTRESRunMins;

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
