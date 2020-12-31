package com.bwcx.slurm;

public class SlurmGoldVO {
    //账号名称(SLURM和gold已同步)
    private String account;
    //最大运行作业数
    private String grpJobs;
    //CPU最大核心数
    private String grpTRES;
    //用户
    private String users;
    //预约机时
    private String reserved;
    //可用机时
    private String available;

    public SlurmGoldVO() {
    }

    public SlurmGoldVO(String account, String grpJobs, String grpTRES, String users, String reserved, String available) {
        this.account = account;
        this.grpJobs = grpJobs;
        this.grpTRES = grpTRES;
        this.users = users;
        this.reserved = reserved;
        this.available = available;
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

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "SlurmGoldVO{" +
                "account='" + account + '\'' +
                ", grpJobs='" + grpJobs + '\'' +
                ", grpTRES='" + grpTRES + '\'' +
                ", users='" + users + '\'' +
                ", reserved='" + reserved + '\'' +
                ", available='" + available + '\'' +
                '}';
    }
}

