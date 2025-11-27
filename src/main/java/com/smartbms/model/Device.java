package com.smartbms.models;

public class Device {
    private Long id;
    private String deviceName;
    private String deviceType;       // مثل: Thermostat, Light, Camera
    private boolean online;          // وضعیت اتصال
    private String alertLevel;       // مقادیر: SAFE, WARNING, CRITICAL
    private String latestTelemetryValue; // مثل: "24.5 C" یا "ON"

    // سازنده پیش‌فرض (Default Constructor)
    public Device() {
    }

    // سازنده کامل (All-args Constructor)
    public Device(Long id, String deviceName, String deviceType, boolean online, String alertLevel, String latestTelemetryValue) {
        this.id = id;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.online = online;
        this.alertLevel = alertLevel;
        this.latestTelemetryValue = latestTelemetryValue;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

    public boolean isOnline() { return online; }
    public void setOnline(boolean online) { this.online = online; }

    public String getAlertLevel() { return alertLevel; }
    public void setAlertLevel(String alertLevel) { this.alertLevel = alertLevel; }

    public String getLatestTelemetryValue() { return latestTelemetryValue; }
    public void setLatestTelemetryValue(String latestTelemetryValue) { this.latestTelemetryValue = latestTelemetryValue; }
}
