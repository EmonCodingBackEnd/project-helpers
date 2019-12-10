package helper.archetype.share.common.ftp.result;

import helper.archetype.share.common.ftp.config.ServerConfig;
import helper.archetype.share.common.ftp.param.DownloadParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DownloadResult {

    private ServerConfig serverConfig;
    private DownloadParam downloadParam;

    private List<DownloadResultItem> successList = new ArrayList<>();
    private List<DownloadResultItem> failureList = new ArrayList<>();

    public boolean hasFailure() {
        return !failureList.isEmpty();
    }

    public void addResultItem(DownloadResultItem item) {
        if (item.isSuccess()) {
            successList.add(item);
        } else {
            failureList.add(item);
        }
    }
}
