# Spring boot Verify Email
## 1. Sơ đồ quan hệ giữa các thực thể
![verify_email_app](https://github.com/nqthinh27/spring-security/assets/80058686/4f352069-d44f-4fdd-b794-c75fa1c02543)
## 2. Mô tả luồng chức năng
![n-office-Page-2](https://github.com/nqthinh27/spring-security/assets/80058686/99522a49-d192-4960-ad61-08faa6c822b7)
## 3. Hướng dẫn setup database
### Cấu hình để kết nối với MySQL
  - Tìm đến file src/main/resource/application.properties, thay mật khẩu và tài khoản tại
  ```java
  spring.datasource.username=root
  spring.datasource.password=thinh27122001
  ```
  thành mật khẩu và tài khoản của mình
  - Kết nối vào MySQL và chạy file src/main/resource/db_migration/database_init.sql để khởi tạo database.
## 4. Kết quả khi chạy code
  - ### Đăng ký tài khoản
  ![image](https://github.com/nqthinh27/spring-security/assets/80058686/329c1cbb-3c6f-47e4-8ff1-07acfef34ff6)
  - ### Email thông báo
  ![image](https://github.com/nqthinh27/spring-security/assets/80058686/3a3970da-4b00-43d5-90d0-acfce66afefc)
  - ### Người dùng kích hoạt tài khoản
  ![image](https://github.com/nqthinh27/spring-security/assets/80058686/281a3f19-cd85-4b83-b6ba-5af37c7457f9)
