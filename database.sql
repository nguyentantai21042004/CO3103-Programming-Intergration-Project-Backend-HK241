-- Bảng User
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,           -- ID tự động tăng
    email VARCHAR(100) UNIQUE NOT NULL,             -- Email duy nhất, dùng để đăng nhập
    username VARCHAR(50) UNIQUE NOT NULL,           -- Tên người dùng duy nhất
    password VARCHAR(255) NOT NULL,                 -- Mật khẩu đã mã hóa
    profile_image VARCHAR(255),                     -- Ảnh đại diện (tùy chọn)
    country VARCHAR(50),                            -- Quốc gia (tùy chọn)
    date_of_birth DATE,                             -- Ngày sinh (tùy chọn)
    is_active BOOLEAN DEFAULT TRUE,                 -- Trạng thái tài khoản (true = kích hoạt)
    role_id BIGINT,                                 -- Liên kết đến bảng roles
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo tài khoản (mặc định là hiện tại)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Thời gian cập nhật
);

-- Thêm khóa ngoại cho role_id
ALTER TABLE users
ADD CONSTRAINT fk_role_id
FOREIGN KEY (role_id) REFERENCES roles(id)
ON DELETE SET NULL; -- Nếu role bị xóa, role_id sẽ bị đặt về NULL

-- Bảng Role
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- ID của vai trò
    name VARCHAR(50) NOT NULL UNIQUE           -- Tên của vai trò (ví dụ: ROLE_ADMIN, ROLE_USER)
);
