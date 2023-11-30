# MidTermJava - SPRING COMMERCE
Họ tên sinh viên thực hiện: Đinh Ngọc Thủy Tiên
MSSV: 52100124
Cửa hàng bán sách - Book Spring Commerce![ui](https://github.com/TienPerry/MidTermJava/assets/93875187/2bd69a84-c1dd-4b27-a792-245707bab9cc)

- Code Structure
- ERD
- How to launch the application
- Postman Snapshots
- References

# Code Structure

- src/main/java:
    - com.example.configuration: chứa các lớp liên quan đến bảo mật ứng dụng, bao gồm cấu hình để xác thực và phân quyền.
    - com.example.controller: chứa các lớp chịu trách nhiệm xử lý các yêu cầu HTTP đến, xác định điểm cuối RESTful và xử lý dữ liệu.
    - com.example.model: chứa các mô hình hoặc thực thể dữ liệu cho ứng dụng của bạn.
    - com.example.repository: xử lý việc truy cập và tương tác dữ liệu với cơ sở dữ liệu.
    - com.example.service: triển khai logic nghiệp vụ và đóng vai trò trung gian giữa bộ điều khiển và kho lưu trữ.
    - com.example.MidtermApplication.java: chứa mã nguồn và tài nguyên chính cho ứng dụng của bạn.

- src/main/resources:
    - static: chứa các file như css, js và image.
    - templates: chứa các file HTML.
    - application.properties: file thuộc tính dùng để cấu hình cài đặt Spring Boot.

# ERD


# How to launch the application
- Import database 

- How to launch the application
1. Clone the project
2. Open the project in IntelliJ IDEA
3. Select option "Run"
4. Access http://localhost:8080 to use application


# Postman Snapshots

1. Đăng nhập
- Admin


- Khách hàng


2. Đăng kí


3. Sản phẩm
- Tìm kiếm sản phẩm theo tên


- Tìm kiếm sản phẩm theo danh mục


- Thêm sản phẩm


- Sửa sản phẩm


- Xóa sản phẩm


4. Danh mục
- Thêm danh mục

- Xóa danh mục

- Sửa danh mục


# References
1. https://docs.spring.io/spring-security/site/docs/5.0.0.RELEASE/reference/htmlsingle/#getting-started
2. https://stackjava.com/spring/so-sanh-phan-biet-crudrepository-voi-jparepository-trong-spring-data.html
3. https://www.codejava.net/frameworks/spring-boot/spring-data-jpa-filter-search-examples
4. https://www.baeldung.com/spring-security-httpsecurity-vs-websecurity
