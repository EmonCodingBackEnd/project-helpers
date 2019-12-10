#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ftp.result;

import ${package}.ftp.config.ServerConfig;
import ${package}.ftp.param.DeleteParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DeleteResult {

    private ServerConfig serverConfig;
    private DeleteParam deleteParam;

    private List<DeleteResultItem> successList = new ArrayList<>();
    private List<DeleteResultItem> failureList = new ArrayList<>();

    public boolean hasFailure() {
        return !failureList.isEmpty();
    }

    public void addResultItem(DeleteResultItem item) {
        if (item.isSuccess()) {
            successList.add(item);
        } else {
            failureList.add(item);
        }
    }
}
