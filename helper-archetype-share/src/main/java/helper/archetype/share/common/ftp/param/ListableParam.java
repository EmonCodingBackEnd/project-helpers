package helper.archetype.share.common.ftp.param;

import helper.archetype.share.common.ftp.filter.FilenameFilter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.net.ftp.FTPFileFilter;

import java.util.regex.Pattern;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class ListableParam extends BaseParam {

    private String pattern = null;
    private Pattern filenamePattern = null;
    private FilenameFilter filenameFilter = null;
    private FTPFileFilter ftpFileFilter = null;
}
