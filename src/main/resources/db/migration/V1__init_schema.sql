-- 创建一个示例表
CREATE TABLE IF NOT EXISTS example (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 可以在此添加更多表或初始数据
