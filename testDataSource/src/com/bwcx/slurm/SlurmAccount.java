package com.bwcx.slurm;

public class SlurmAccount {
    //账号名称
    private String account;
    //最大运行作业数
    private String grpJobs;
    //CPU最大核心数
    private String grpTRES;
    //用户
    private String users;

    public SlurmAccount(String account, String grpJobs, String grpTRES, String users) {
        this.account = account;
        this.grpJobs = grpJobs;
        this.grpTRES = grpTRES;
        this.users = users;
    }

    public SlurmAccount() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGrpJobs() {
        return grpJobs;
    }

    public void setGrpJobs(String grpJobs) {
        this.grpJobs = grpJobs;
    }

    public String getGrpTRES() {
        return grpTRES;
    }

    public void setGrpTRES(String grpTRES) {
        this.grpTRES = grpTRES;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SlurmAccount{" +
                "account='" + account + '\'' +
                ", users='" + users + '\'' +
                '}';
    }
}
