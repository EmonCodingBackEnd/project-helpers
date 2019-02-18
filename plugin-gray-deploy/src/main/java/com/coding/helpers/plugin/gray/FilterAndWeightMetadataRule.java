package com.coding.helpers.plugin.gray;

import com.coding.helpers.plugin.gray.request.rule.FilterRequestRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Slf4j
public class FilterAndWeightMetadataRule extends ZoneAvoidanceRule {

    private static final String META_DATA_KEY_WEIGHT = "weight";

    private Random random = new Random();

    @Override
    public Server choose(Object key) {
        List<Server> serverList =
                this.getPredicate().getEligibleServers(this.getLoadBalancer().getAllServers(), key);
        if (CollectionUtils.isEmpty(serverList)) {
            return null;
        }
        FilterRequestRule rule = CoreHeaderInterceptor.rule.get();
        Server server;
        if (rule != null) {
            server = choose(rule.filter(serverList));
        } else {
            server = super.choose(key);
        }

        if (server != null) {
            if (log.isDebugEnabled()) {
                log.debug(
                        "remote server info: appName={},instanceId={},host={},port={}",
                        server.getMetaInfo().getAppName(),
                        server.getMetaInfo().getInstanceId(),
                        server.getHost(),
                        server.getHostPort());
            }
        }
        return server;
    }

    private Server choose(Collection<Server> _serverList) {
        if (CollectionUtils.isEmpty(_serverList)) {
            return null;
        }
        List<Server> serverList = new ArrayList<>(_serverList);
        int totalWeight = 0;
        List<Integer> weights = new ArrayList<>();
        for (Server server : serverList) {
            DiscoveryEnabledServer _server = (DiscoveryEnabledServer) server;
            Map<String, String> metadata = _server.getInstanceInfo().getMetadata();
            String strWeight = metadata.get(META_DATA_KEY_WEIGHT);
            int weight = 100;
            try {
                weight = Integer.parseInt(strWeight);
            } catch (NumberFormatException e) {
            }
            totalWeight += weight;
            weights.add(totalWeight);
        }
        int randomWeight = this.random.nextInt(totalWeight);
        int n = 0;
        for (Integer weight : weights) {
            if (weight >= randomWeight) {
                break;
            } else {
                n++;
            }
        }
        return serverList.get(n);
    }
}
