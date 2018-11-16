package com.pdd.ceshi.dbmanager;

public class RecordBean {
    private String time;
    private String equipMent;
    private String level;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEquipMent() {
        return equipMent;
    }

    public void setEquipMent(String equipMent) {
        this.equipMent = equipMent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "RecordBean{" +
                "time='" + time + '\'' +
                ", equipMent='" + equipMent + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
