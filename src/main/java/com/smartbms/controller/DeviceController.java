package com.smartbms.controller;

import com.smartbms.model.SmartDevice;
import com.smartbms.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/devices")
@CrossOrigin(origins = "*") // اجازه دسترسی به Vue
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    // 1. گرفتن اطلاعات داشبورد (لیست دستگاه‌ها + آمار ساده)
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardData() {
        List<SmartDevice> devices = deviceRepository.findAll();
        
        long onlineCount = devices.stream().filter(SmartDevice::isOnline).count();
        long criticalCount = devices.stream().filter(d -> "CRITICAL".equals(d.getAlertLevel())).count();

        Map<String, Object> response = new HashMap<>();
        response.put("totalDevices", devices.size());
        response.put("onlineDevices", onlineCount);
        response.put("criticalAlerts", criticalCount);
        response.put("devices", devices);

        return ResponseEntity.ok(response);
    }

    // 2. سناریوی هتل (پر کردن دیتابیس با داده‌های تستی)
    @PostMapping("/scenario/hotel")
    public ResponseEntity<String> runHotelScenario() {
        // ابتدا دیتابیس را پاک کن تا تکراری نشود
        deviceRepository.deleteAll();

        createDevice("سنسور دما - اتاق سرور", "Sensor", "42.5 C", "WARNING", true);
        createDevice("چیلر مرکزی", "Actuator", "ON", "NORMAL", true);
        createDevice("روشنایی لابی", "Light", "OFF", "NORMAL", false);
        createDevice("سیستم اعلام حریق", "Sensor", "OK", "NORMAL", true);
        createDevice("دوربین پارکینگ", "Camera", "Recording", "NORMAL", true);
        createDevice("ترموستات اتاق ۱۰۱", "Thermostat", "24.0 C", "NORMAL", true);

        return ResponseEntity.ok("Hotel Scenario Loaded Successfully!");
    }

    // متد کمکی برای ساخت دستگاه
    private void createDevice(String name, String type, String telemetry, String alert, boolean online) {
        SmartDevice device = new SmartDevice();
        device.setDeviceName(name);
        device.setDeviceType(type);
        device.setLatestTelemetryValue(telemetry);
        device.setAlertLevel(alert);
        device.setOnline(online);
        device.setLastActive(LocalDateTime.now());
        deviceRepository.save(device);
    }
}
