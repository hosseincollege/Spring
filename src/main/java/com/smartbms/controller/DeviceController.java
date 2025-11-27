package com.smartbms.controller;

import com.smartbms.model.DashboardData;
import com.smartbms.model.SmartDevice;
import com.smartbms.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices") // تمام آدرس‌ها با /devices شروع می‌شوند
// نکته: تنظیمات CORS در فایل اصلی برنامه انجام شده است، پس اینجا نیازی نیست.
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    // 1. اندپوینت برای داشبورد (آمار + لیست دستگاه‌ها)
    // آدرس نهایی: GET /devices/dashboard
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardData> getDashboardData() {
        DashboardData data = deviceService.getDashboardData();
        return ResponseEntity.ok(data);
    }

    // 2. اندپوینت برای تغییر وضعیت (Toggle)
    // آدرس نهایی: PUT /devices/{id}/toggle
    @PutMapping("/{id}/toggle")
    public ResponseEntity<SmartDevice> toggleDevice(@PathVariable Long id) {
        SmartDevice updatedDevice = deviceService.toggleDeviceStatus(id);
        if (updatedDevice != null) {
            return ResponseEntity.ok(updatedDevice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // 3. اندپوینت شبیه‌سازی سناریو (برای دکمه "شبیه‌سازی محیطی" در داشبورد)
    // آدرس نهایی: POST /devices/scenario/hotel
    @PostMapping("/scenario/hotel")
    public ResponseEntity<String> triggerScenario() {
        // فعلا فقط یک پیام موفقیت برمی‌گردانیم تا فرانت‌اند ارور ندهد
        // بعداً می‌توان منطق پیچیده‌تری اینجا گذاشت
        return ResponseEntity.ok("Hotel Scenario Triggered Successfully");
    }
}
