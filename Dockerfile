# Sử dụng một image có chứa JDK để build và chạy ứng dụng
FROM openjdk:17-jdk-alpine

# Thiết lập biến môi trường để container nhận diện đúng timezone (tùy chọn)
ENV TZ=Asia/Ho_Chi_Minh

# Tạo thư mục chứa ứng dụng trong container
WORKDIR /app

# Copy file jar từ thư mục target vào container
COPY target/music-backend-0.0.1-SNAPSHOT.jar app.jar

# Chỉ định cổng chạy cho container
EXPOSE 8088

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
