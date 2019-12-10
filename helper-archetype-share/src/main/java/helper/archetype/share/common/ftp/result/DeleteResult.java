package helper.archetype.share.common.ftp.result;

import helper.archetype.share.common.ftp.config.ServerConfig;
import helper.archetype.share.common.ftp.param.DeleteParam;
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
