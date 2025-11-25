package com.smartbms;

import io.github.cdimascio.dotenv.Dotenv; // برای لود کردن فایل .env
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // برای تعریف Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry; // برای تنظیمات CORS
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // برای رابط WebMvcConfigurer
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BackendJavaApplication {

    private static final Logger logger = LoggerFactory.getLogger(BackendJavaApplication.class);

    public static void main(String[] args) {
        // 🚀 لود کردن فایل .env قبل از شروع Spring Application Context
        // این تضمین می‌کند که متغیرهای .env قبل از اینکه Spring Boot شروع به خواندن
        // application.properties کند، در System Properties قرار گرفته‌اند.
        try {
            Dotenv dotenv = Dotenv.load(); // فایل .env را از ریشه پروژه لود می‌کند
            dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue()));
            logger.info("✅ .env file loaded successfully!");
        } catch (Exception e) {
            logger.error("⚠️ Could not load .env file. Ensure it exists in the project root. " + e.getMessage());
            // ادامه اجرای برنامه حتی اگر .env لود نشود، با استفاده از مقادیر پیش‌فرض یا تنظیمات application.properties
        }

        logger.info("✅ BackendJava Application starting...");
        SpringApplication.run(BackendJavaApplication.class, args);
        logger.info("✅ BackendJava Application started successfully!");
        // نمایش پورت واقعی که از .env لود شده یا پیش‌فرض 8080
        logger.info("Listening on http://localhost:" + System.getProperty("SERVER_PORT", "8080") + " ← check this port!");
    }

    // 🌐 پیکربندی CORS برای اجازه دسترسی از فرانت‌اند
    // این متد یک Bean از نوع WebMvcConfigurer تعریف می‌کند که قوانین CORS را اعمال می‌کند.
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // دریافت آدرس‌های فرانت‌اند از System Properties (که از .env لود شده‌اند)
                String frontendUrls = System.getProperty("FRONTEND_URL");
                if (frontendUrls != null && !frontendUrls.isEmpty()) {
                    String[] origins = frontendUrls.split(","); // آدرس‌ها با کاما جدا شده‌اند
                    registry.addMapping("/**") // اعمال CORS برای تمامی endpointها
                            .allowedOrigins(origins) // آدرس‌های مجاز
                            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // متدهای HTTP مجاز
                            .allowedHeaders("*") // تمامی هدرها مجاز
                            .allowCredentials(true); // اجازه ارسال کوکی‌ها و اطلاعات احراز هویت
                    logger.info("✅ CORS configured for origins: " + String.join(", ", origins));
                } else {
                    logger.warn("⚠️ FRONTEND_URL not set in .env or system properties. CORS might be restrictive.");
                }
            }
        };
    }
}
