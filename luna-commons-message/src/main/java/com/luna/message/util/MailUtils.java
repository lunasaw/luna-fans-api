package com.luna.message.util;

import cn.hutool.extra.mail.MailAccount;
import com.luna.message.config.JavaMailConfigValue;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

/**
 * @Package: com.luna.message.util
 * @ClassName: mailUtils
 * @Author: luna
 * @CreateTime: 2020/8/11 14:57
 * @Description:
 */
public class MailUtils {

    /**
     * 转化发送的地址
     *
     * @param list
     * @return
     */
    public static InternetAddress[] getAddress(List<String> list) {
        InternetAddress[] addressesTo = new InternetAddress[list.size()];
        for (int i = 0; i < list.size(); i++) {
            try {
                addressesTo[i] = new InternetAddress(list.get(i));
            } catch (AddressException e) {
                e.printStackTrace();
            }
        }
        return addressesTo;
    }

    /**
     * 字符串转化多人收件地址 ',' 号分割
     * 
     * @param to
     * @return
     * @throws AddressException
     */
    public static InternetAddress[] getAddresses(String to) throws AddressException {
        InternetAddress[] addressesTo = null;
        if (to != null && to.trim().length() > 0) {
            String[] receiveList = to.split(",");
            int receiveCount = receiveList.length;
            if (receiveCount > 0) {
                addressesTo = new InternetAddress[receiveCount];
                for (int i = 0; i < receiveCount; i++) {
                    addressesTo[i] = new InternetAddress(receiveList[i]);
                }
            }
        }
        return addressesTo;
    }

    /**
     * 数组转化收件地址
     * 
     * @param to
     * @return
     * @throws AddressException
     */
    public static InternetAddress[] getAddresses(String[] to) throws AddressException {
        InternetAddress[] addressesTo = null;
        for (int i = 0; i < to.length; i++) {
            addressesTo[i] = new InternetAddress(to[i]);
        }
        return addressesTo;
    }

    public static String getToAddress(List<String> to) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : to) {
            stringBuilder.append(s + ",");
        }
        return stringBuilder.toString();
    }

    /**
     * Spring参数配置转为MailAccount配置
     * 
     * @param javaMailConfigValue
     * @return
     */
    public static MailAccount javaMailConfigValue2MailAccount(JavaMailConfigValue javaMailConfigValue) {
        if (javaMailConfigValue == null) {
            return null;
        }
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost(javaMailConfigValue.getHost());
        mailAccount.setPort(Integer.valueOf(javaMailConfigValue.getPort()));
        mailAccount.setAuth(Boolean.parseBoolean(javaMailConfigValue.getAuth()));
        mailAccount.setUser(javaMailConfigValue.getMailFrom());
        mailAccount.setPass(javaMailConfigValue.getPassword());
        mailAccount.setFrom(javaMailConfigValue.getMailFrom());
        mailAccount.setDebug(Boolean.parseBoolean(javaMailConfigValue.getMailDebug()));
        return mailAccount;
    }
}
