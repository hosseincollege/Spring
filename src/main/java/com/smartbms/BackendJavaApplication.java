package com.smartbms;

import com.smartbms.service.DeviceService;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendJavaApplication {

    private static final Logger logger = LoggerFactory.getLogger(BackendJavaApplication.class);

    @Autowired
    private DeviceService deviceService;

    public static void main(String[] args) {
        // 1. لود کردن فایل .env (اگر وجود داشته باشد)
        try {
            Dotenv dotenv = Dotenv.load();
            dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue()));
            logger.info("✅ .env file loaded successfully!");
        } catch (Exception e) {
            logger.warn("⚠️ .env file not found, skipping dotenv load (using system env or defaults).");
        }

        logger.info("✅ BackendJava Application starting...");
        SpringApplication.run(BackendJavaApplication.class, args);
        logger.info("✅ BackendJava Application started successfully!");
        logger.info("🚀 Server is running on port: " + System.getProperty("SERVER_PORT", "8080"));
    }

    // 2. تنظیمات CORS برای اتصال فرانت‌اند Vue به این بک‌اند
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String frontendUrls = System.getProperty("FRONTEND_URL", "http://localhost:8080,http://localhost:8081");
                
                registry.addMapping("/**")
                        .allowedOrigins(frontendUrls.split(","))
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
                
                logger.info("🌍 CORS configured for: " + frontendUrls);
            }
        };
    }

    // 3. اجرا کننده اولیه برای پر کردن دیتابیس (Seed Data)
    @Bean
    CommandLineRunner init(DeviceService service) {
        return args -> {
            logger.info("🌱 Checking database status...");
            service.initMockData();
            logger.info("✅ Database initialization check completed.");
        };
    }
}
