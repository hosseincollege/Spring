package com.smartbms.service;

import com.smartbms.model.DashboardData;
import com.smartbms.model.SmartDevice;
import com.smartbms.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    // 1. گرفتن تمام دستگاه‌ها
    public List<SmartDevice> getAllDevices() {
        return deviceRepository.findAll();
    }

    // 2. ساخت داده‌های کامل برای داشبورد
    public DashboardData getDashboardData() {
        long total = deviceRepository.count();
        long online = deviceRepository.countByOnline(true);
        long critical = deviceRepository.countByAlertLevel("CRITICAL");
        
        // زمان جاری سرور
        String serverTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        // لیست همه دستگاه‌ها برای جدول پایین داشبورد
        List<SmartDevice> devices = deviceRepository.findAll();

        return new DashboardData(total, online, critical, serverTime, devices);
    }

    // 3. تغییر وضعیت روشن/خاموش یک دستگاه (Toggle)
    public SmartDevice toggleDeviceStatus(Long id) {
        Optional<SmartDevice> optionalDevice = deviceRepository.findById(id);
        if (optionalDevice.isPresent()) {
            SmartDevice device = optionalDevice.get();
            // معکوس کردن وضعیت آنلاین بودن به عنوان شبیه‌سازی روشن/خاموش
            device.setOnline(!device.isOnline());
            
            // بروزرسانی زمان آخرین فعالیت
            device.setLastActive(LocalDateTime.now());
            
            return deviceRepository.save(device);
        }
        return null; // یا پرتاب خطا
    }

    // 4. متد اولیه برای پر کردن دیتابیس با داده‌های تستی (Seed Data)
    // این متد فقط یکبار صدا زده می‌شود تا دیتابیس خالی نباشد
    public void initMockData() {
        if (deviceRepository.count() == 0) {
            createMockDevice("Thermostat Living Room", "Thermostat", "24.5 C", "NORMAL", true);
            createMockDevice("Main Entrance Camera", "Camera", "Recording", "NORMAL", true);
            createMockDevice("Kitchen Smoke Detector", "Sensor", "0 PPM", "NORMAL", true);
            createMockDevice("Server Room HVAC", "HVAC", "Error 503", "CRITICAL", false);
            createMockDevice("Lobby Light", "Light", "ON", "NORMAL", true);
        }
    }

    private void createMockDevice(String name, String type, String value, String alert, boolean online) {
        SmartDevice d = new SmartDevice();
        d.setDeviceName(name);
        d.setDeviceType(type);
        d.setLatestTelemetryValue(value);
        d.setAlertLevel(alert);
        d.setOnline(online);
        d.setLastActive(LocalDateTime.now());
        deviceRepository.save(d);
    }
}
