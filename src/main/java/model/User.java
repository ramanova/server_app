package model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger COUNTER = new AtomicInteger();

    private int id;
    private Map<Integer, Integer> campaignIdToSeenCount = new ConcurrentHashMap<>();

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Map<Integer, Integer> getUserCampaigns() {
        return campaignIdToSeenCount;
    }

}
