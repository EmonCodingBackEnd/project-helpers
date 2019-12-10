#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ftp.param;

import ${package}.ftp.filter.FilenameFilter;
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
