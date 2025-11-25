package com.smartbms.model;

import jakarta.persistence.*; // اگر خطا داد، به جای jakarta بنویسید javax
import java.time.LocalDateTime;

@Entity
@Table(name = "smart_devices")
public class SmartDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceName;
    private String deviceType;
    private String latestTelemetryValue;
    private String alertLevel; // NORMAL, WARNING, CRITICAL
    private boolean online;
    private LocalDateTime lastActive;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }

    public String getLatestTelemetryValue() { return latestTelemetryValue; }
    public void setLatestTelemetryValue(String latestTelemetryValue) { this.latestTelemetryValue = latestTelemetryValue; }

    public String getAlertLevel() { return alertLevel; }
    public void setAlertLevel(String alertLevel) { this.alertLevel = alertLevel; }

    public boolean isOnline() { return online; }
    public void setOnline(boolean online) { this.online = online; }

    public LocalDateTime getLastActive() { return lastActive; }
    public void setLastActive(LocalDateTime lastActive) { this.lastActive = lastActive; }
}
