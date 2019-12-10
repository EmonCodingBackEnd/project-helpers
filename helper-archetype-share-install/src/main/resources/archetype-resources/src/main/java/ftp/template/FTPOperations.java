#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
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
package ${package}.ftp.template;

import ${package}.ftp.config.ServerConfig;
import ${package}.ftp.exception.FTPException;
import ${package}.ftp.param.DeleteParam;
import ${package}.ftp.param.DownloadParam;
import ${package}.ftp.param.ListParam;
import ${package}.ftp.param.UploadParam;
import ${package}.ftp.result.DeleteResult;
import ${package}.ftp.result.DownloadResult;
import ${package}.ftp.result.ListResult;
import ${package}.ftp.result.UploadResult;

/**
 * FTP操作.
 *
 * <p>创建时间: <font style="color:${symbol_pound}00FFFF">20180622 20:22</font><br>
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
