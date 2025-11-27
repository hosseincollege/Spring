package com.smartbms.model;

import java.util.List;

public class DashboardData {
    private long totalDevices;
    private long onlineDevices;
    private long criticalAlerts;
    private String serverTime;
    private List<SmartDevice> devices;

    // سازنده (Constructor)
    public DashboardData(long totalDevices, long onlineDevices, long criticalAlerts, String serverTime, List<SmartDevice> devices) {
        this.totalDevices = totalDevices;
        this.onlineDevices = onlineDevices;
        this.criticalAlerts = criticalAlerts;
        this.serverTime = serverTime;
        this.devices = devices;
    }

    // Getters and Setters
    public long getTotalDevices() {
        return totalDevices;
    }

    public void setTotalDevices(long totalDevices) {
        this.totalDevices = totalDevices;
    }

    public long getOnlineDevices() {
        return onlineDevices;
    }

    public void setOnlineDevices(long onlineDevices) {
        this.onlineDevices = onlineDevices;
    }

    public long getCriticalAlerts() {
        return criticalAlerts;
    }

    public void setCriticalAlerts(long criticalAlerts) {
        this.criticalAlerts = criticalAlerts;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public List<SmartDevice> getDevices() {
        return devices;
    }

    public void setDevices(List<SmartDevice> devices) {
        this.devices = devices;
    }
}
