package helper.archetype.share.common.ftp.param;

import lombok.Data;

@Data
public class BaseParam {

    // 文件要上传到的服务器目录
    private String remoteDirectory;
}
