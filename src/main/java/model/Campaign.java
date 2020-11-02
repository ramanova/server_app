package model;

public class Campaign {
    private Integer id;
    private String name;
    private String data;    // some data
    private Cap cap;

    public Campaign(Integer id, String name, String data, Cap cap) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.cap = cap;
    }

    private class Cap {
        private Integer maxCountPerUser;
        private Integer maxCount;

        public Cap(Integer maxCountPerUser, Integer maxCount) {
            this.maxCountPerUser = maxCountPerUser;
            this.maxCount = maxCount;
        }

        public Integer getMaxCountPerUser() {
            return maxCountPerUser;
        }

        public void setMaxCountPerUser(Integer maxCountPerUser) {
            this.maxCountPerUser = maxCountPerUser;
        }

        public Integer getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(Integer maxCount) {
            this.maxCount = maxCount;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


}
