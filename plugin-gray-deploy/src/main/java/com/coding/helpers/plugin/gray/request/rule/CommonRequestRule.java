package com.coding.helpers.plugin.gray.request.rule;

import com.coding.helpers.plugin.gray.constant.GrayConstants;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import lombok.Data;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.*;

@Data
public class CommonRequestRule implements FilterRequestRule {

    private Type type;

    private Set<String> tags;

    public CommonRequestRule() {}

    public CommonRequestRule(int type, String tags) {
        this.type = Type.index(type);
        if (!StringUtils.isEmpty(tags)) {
            this.tags =
                    new HashSet<>(Arrays.asList(tags.split(GrayConstants.META_DATA_KEY_TAG_SPLIT)));
        } else {
            this.tags = new HashSet<>();
        }
    }

    @Override
    public Collection<Server> filter(Collection<Server> serverList) {
        MultiValueMap<ServerKey, Server> serverMap = new LinkedMultiValueMap<>();
        for (Server server : serverList) {
            DiscoveryEnabledServer _server = (DiscoveryEnabledServer) server;
            serverMap.add(createServerKey(_server), _server);
        }
        switch (type) {
            case MATCH_FIRST:
                return matchFirstData(serverMap);
            case MATCH:
                return matchData(serverMap);
            case PARTIAL_MATCH:
                return partialMatchData(serverMap);
            case UNMATCH:
                return unMatchData(serverMap);
        }
        return null;
    }

    @Override
    public String toRule() {
        return type.index
                + GrayConstants.RULE_TYPE_TAG_SPLIT
                + StringUtils.collectionToDelimitedString(
                        tags, GrayConstants.META_DATA_KEY_TAG_SPLIT);
    }

    @Override
    public String toString() {
        return toRule();
    }

    private List<Server> matchFirstData(MultiValueMap<ServerKey, Server> serverMap) {
        List<Server> result = new ArrayList<>();
        for (ServerKey serverKey : serverMap.keySet()) {
            if (getTags().equals(serverKey.getTags())) {
                return serverMap.get(serverKey);
            } else {
                result.addAll(serverMap.get(serverKey));
            }
        }
        return result;
    }

    private List<Server> matchData(MultiValueMap<ServerKey, Server> serverMap) {
        for (ServerKey serverKey : serverMap.keySet()) {
            if (getTags().equals(serverKey.getTags())) {
                return (serverMap.get(serverKey));
            }
        }
        return null;
    }

    private List<Server> partialMatchData(MultiValueMap<ServerKey, Server> serverMap) {
        List<Server> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (ServerKey serverKey : serverMap.keySet()) {
            set.clear();
            set.addAll(getTags());
            set.retainAll(serverKey.getTags());
            // 交集>0
            if (set.size() > 0) {
                result.addAll(serverMap.get(serverKey));
            }
        }
        return result;
    }

    private List<Server> unMatchData(MultiValueMap<ServerKey, Server> serverMap) {
        List<Server> result = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (ServerKey serverKey : serverMap.keySet()) {
            set.clear();
            set.addAll(getTags());
            set.retainAll(serverKey.getTags());
            // 交集==0
            if (set.size() == 0) {
                result.addAll(serverMap.get(serverKey));
            }
        }
        return result;
    }

    private ServerKey createServerKey(DiscoveryEnabledServer server) {
        Map<String, String> metadata = server.getInstanceInfo().getMetadata();
        String label = metadata.get(GrayConstants.META_DATA_KEY_TAG);
        Set<String> set = new HashSet<>();
        if (!StringUtils.isEmpty(label)) {
            set = new HashSet<>(Arrays.asList(label.split(GrayConstants.META_DATA_KEY_TAG_SPLIT)));
        }
        return new ServerKey(set);
    }

    @Data
    class ServerKey {
        Set<String> tags;

        public ServerKey(Set<String> tags) {
            this.tags = tags;
        }
    }

    @Getter
    enum Type {
        MATCH_FIRST("匹配优先", 1),
        MATCH("完全匹配", 2),
        PARTIAL_MATCH("部分匹配", 3),
        UNMATCH("不匹配", 4),
        ;

        Type(String name, int index) {
            this.name = name;
            this.index = index;
        }

        private String name;
        private int index;

        public static Type index(int index) {
            for (Type type : Type.values()) {
                if (type.index == index) {
                    return type;
                }
            }
            return null;
        }
    }
}
