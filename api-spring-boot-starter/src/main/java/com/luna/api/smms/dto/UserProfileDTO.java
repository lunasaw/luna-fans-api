package com.luna.api.smms.dto;

/**
 * @author luna@mac
 * @className UserProfileDTO.java
 * @description TODO
 * @createTime 2021年03月28日 14:23:00
 */
public class UserProfileDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * Email.
     */
    private String email;

    /**
     * User Group Name.
     */
    private String role;

    /**
     * User Group Expire Date.
     */
    private String groupExpire;

    /**
     * Email Verification（0 for not verified， 1 for verified）.
     */
    private String emailVerified;

    /**
     * Disk Usage.
     */
    private String diskUsage;

    /**
     * Disk RAW Usage（Byte
     */
    private String diskUsageRaw;

    /**
     * Disk Limit.
     */
    private String diskLimit;

    /**
     * Disk RAW Limit（Byte）.
     */
    private String diskLimitRaw;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGroupExpire() {
        return groupExpire;
    }

    public void setGroupExpire(String groupExpire) {
        this.groupExpire = groupExpire;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(String diskUsage) {
        this.diskUsage = diskUsage;
    }

    public String getDiskUsageRaw() {
        return diskUsageRaw;
    }

    public void setDiskUsageRaw(String diskUsageRaw) {
        this.diskUsageRaw = diskUsageRaw;
    }

    public String getDiskLimit() {
        return diskLimit;
    }

    public void setDiskLimit(String diskLimit) {
        this.diskLimit = diskLimit;
    }

    public String getDiskLimitRaw() {
        return diskLimitRaw;
    }

    public void setDiskLimitRaw(String diskLimitRaw) {
        this.diskLimitRaw = diskLimitRaw;
    }
}
