package com.luna.file.httpd;

import com.google.common.collect.Lists;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.FileException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.jackrabbit.webdav.DavConstants;
import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.MultiStatus;
import org.apache.jackrabbit.webdav.MultiStatusResponse;
import org.apache.jackrabbit.webdav.client.methods.*;
import org.apache.jackrabbit.webdav.lock.Scope;
import org.apache.jackrabbit.webdav.lock.Type;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class HttpdFileUtilByJack {

    /**
     * 新建文件夹
     * 
     * @param httpClient
     * @param httpPath
     */
    public static Integer newDirectory(HttpClient httpClient, String httpPath) {
        try {
            DavMethod mkCol = new MkColMethod(httpPath);
            httpClient.executeMethod(mkCol);
            // 获取执行返回结果
            return mkCol.getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                ResultCode.MSG_ERROR_SYSTEM_EXCEPTION + e.getMessage());
        }
    }

    /**
     * 上传文件
     * 
     * @param httpClient
     * @param filePath 文件路径
     * @param httpPath 上传路径
     */
    public static Integer upload(HttpClient httpClient, String filePath, String httpPath) {
        try {
            // Put Method - 上传本地文件
            PutMethod put = new PutMethod(httpPath);
            RequestEntity requestEntity = new InputStreamRequestEntity(new FileInputStream(filePath));
            put.setRequestEntity(requestEntity);
            httpClient.executeMethod(put);
            return put.getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                ResultCode.MSG_ERROR_SYSTEM_EXCEPTION + e.getMessage());
        }
    }

    /**
     * 远程复制文件
     * 
     * @param httpClient
     * @param uri 其实地址
     * @param destinationUri 目的地址
     * @param overwrite 是否覆盖
     */
    public static Integer copyFileRemote(HttpClient httpClient, String uri, String destinationUri, boolean overwrite) {
        try {
            DavMethod copy = new CopyMethod(uri, destinationUri, overwrite);
            httpClient.executeMethod(copy);
            return copy.getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                ResultCode.MSG_ERROR_SYSTEM_EXCEPTION + e.getMessage());
        }
    }

    /**
     * 重命名文件,也可移动文件
     * 
     * @param httpClient
     * @param oldPath 旧地址
     * @param newPath 新地址
     * @return
     */
    public static Integer renameFile(HttpClient httpClient, String oldPath, String newPath) {
        try {
            DavMethod move = new MoveMethod(oldPath, newPath, true);
            httpClient.executeMethod(move);
            return move.getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                ResultCode.MSG_ERROR_SYSTEM_EXCEPTION + e.getMessage());
        }
    }

    /**
     * 文件枷锁
     * 
     * @param httpClient
     * @param filePath 文件路径
     * @param owner 拥有者
     * @param timeout 锁定时间
     * @return 解锁token
     */
    public static String clockFile(HttpClient httpClient, String filePath, String owner, long timeout) {
        try {
            LockMethod lock = new LockMethod(filePath, Scope.SHARED, Type.WRITE, owner,
                timeout, false);
            httpClient.executeMethod(lock);
            String lockToken = lock.getLockToken();
            return lockToken;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                ResultCode.MSG_ERROR_SYSTEM_EXCEPTION + e.getMessage());
        }
    }

    /**
     * 解锁文件
     * 
     * @param httpClient
     * @param filePath 文件路径
     * @param lockToken 解锁token
     * @return
     */
    public static Integer unlockFile(HttpClient httpClient, String filePath, String lockToken) {
        try {
            DavMethod unlock = new UnLockMethod(filePath, lockToken);
            httpClient.executeMethod(unlock);
            return unlock.getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                ResultCode.MSG_ERROR_SYSTEM_EXCEPTION + e.getMessage());
        }
    }

    public static Integer copyFileRemote(HttpClient httpClient, String uri, String destinationUri) {
        return copyFileRemote(httpClient, uri, destinationUri, true);
    }

    /**
     * 遍历文件夹
     * 
     * @param httpClient
     * @param folderPath 文件夹
     */
    public static void findFile(HttpClient httpClient, String folderPath) {
        ArrayList<String> files = Lists.newArrayList();
        try {
            DavMethod find = new PropFindMethod(folderPath, DavConstants.PROPFIND_ALL_PROP, DavConstants.DEPTH_1);
            httpClient.executeMethod(find);
            MultiStatus multiStatus = find.getResponseBodyAsMultiStatus();
            MultiStatusResponse[] responses = multiStatus.getResponses();
            for (MultiStatusResponse respons : responses) {
                files.add(respons.getHref());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                ResultCode.MSG_ERROR_SYSTEM_EXCEPTION + e.getMessage());
        } catch (DavException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                ResultCode.MSG_ERROR_SYSTEM_EXCEPTION + e.getMessage());
        }
    }

    /**
     * 删除文件夹
     * 
     * @param httpClient
     * @param path
     * @return
     */
    public static Integer deleteFile(HttpClient httpClient, String path) {
        try {
            DavMethod delete = new DeleteMethod(path);
            httpClient.executeMethod(delete);
            return delete.getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION,
                ResultCode.MSG_ERROR_SYSTEM_EXCEPTION + e.getMessage());
        }
    }
}