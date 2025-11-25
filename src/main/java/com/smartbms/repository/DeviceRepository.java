package com.smartbms.repository;

import com.smartbms.model.SmartDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<SmartDevice, Long> {
    // اینجا متدهای خاص دیتابیس قرار می‌گیرند، فعلا خالی کافیست
}
