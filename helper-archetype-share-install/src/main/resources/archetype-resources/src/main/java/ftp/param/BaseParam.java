#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ftp.param;

import lombok.Data;

@Data
public class BaseParam {

    // 文件要上传到的服务器目录
    private String remoteDirectory;
}
