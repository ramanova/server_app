package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Campaign {
    private static final AtomicInteger COUNTER = new AtomicInteger();

    private int id;
    private String name;
    private String data;    // some data
    private Cap cap;
    private int impressions = 0;

    public Campaign(String name, String data, Cap cap) {
        this.id = COUNTER.getAndIncrement();
        this.name = name;
        this.data = data;
        this.cap = cap;
    }

    public static class Cap {
        private int maxCountPerUser;
        private int maxCount;

        public Cap(int maxCountPerUser, int maxCount) {
            this.maxCountPerUser = maxCountPerUser;
            this.maxCount = maxCount;
        }

        public int getMaxCountPerUser() {
            return maxCountPerUser;
        }

        public void setMaxCountPerUser(int maxCountPerUser) {
            this.maxCountPerUser = maxCountPerUser;
        }

        public int getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Cap getCap() {
        return cap;
    }

    public void setCap(Cap cap) {
        this.cap = cap;
    }

    public void decrementCap() {
        this.cap.setMaxCountPerUser(this.cap.getMaxCountPerUser() - 1);
        this.cap.setMaxCount(this.cap.getMaxCount() - 1);
    }

    public int getImpressions() {
        return impressions;
    }

    public void incrementImpressions() {
        impressions++;
    }
}
