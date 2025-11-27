package com.smartbms.repository;

import com.smartbms.model.SmartDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<SmartDevice, Long> {

    // ✅ شمارش تعداد دستگاه‌های آنلاین (یا آفلاین)
    long countByOnline(boolean online);

    // ✅ شمارش تعداد دستگاه‌ها بر اساس سطح هشدار (مثلاً CRITICAL)
    long countByAlertLevel(String alertLevel);

    // ✅ پیدا کردن لیست دستگاه‌ها بر اساس نوع (اختیاری، ولی کاربردی)
    List<SmartDevice> findByDeviceType(String deviceType);
}
