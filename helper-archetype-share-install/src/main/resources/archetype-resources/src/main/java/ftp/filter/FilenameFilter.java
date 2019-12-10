#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ftp.filter;

public interface FilenameFilter {

    boolean accept(String name);
}
