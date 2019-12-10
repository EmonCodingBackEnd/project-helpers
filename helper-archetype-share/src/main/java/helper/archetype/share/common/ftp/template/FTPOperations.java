/*
 * 文件名称：FTPOperations.java
 * 系统名称：[系统名称]
 * 模块名称：FTP操作
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180622 20:22
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180622-01         Rushing0711     M201806222022 新建文件
 ********************************************************************************/
package helper.archetype.share.common.ftp.template;

import helper.archetype.share.common.ftp.config.ServerConfig;
import helper.archetype.share.common.ftp.exception.FTPException;
import helper.archetype.share.common.ftp.param.DeleteParam;
import helper.archetype.share.common.ftp.param.DownloadParam;
import helper.archetype.share.common.ftp.param.ListParam;
import helper.archetype.share.common.ftp.param.UploadParam;
import helper.archetype.share.common.ftp.result.DeleteResult;
import helper.archetype.share.common.ftp.result.DownloadResult;
import helper.archetype.share.common.ftp.result.ListResult;
import helper.archetype.share.common.ftp.result.UploadResult;

/**
 * FTP操作.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180622 20:22</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FTPOperations {

    UploadResult uploadFile(ServerConfig serverConfig, UploadParam uploadParam) throws FTPException;

    ListResult listFiles(ServerConfig serverConfig, ListParam listParam) throws FTPException;

    DownloadResult downloadFile(ServerConfig serverConfig, DownloadParam downloadParam)
            throws FTPException;

    <T> T downloadFile(ServerConfig serverConfig, FTPCallback<T> callback) throws FTPException;

    DeleteResult deleteFile(ServerConfig serverConfig, DeleteParam deleteParam) throws FTPException;
}
