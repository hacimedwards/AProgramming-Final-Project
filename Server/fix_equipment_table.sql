-- Complete schema verification and fix script
-- Run this on your MySQL database (approj)

-- 1. Check and fix equipment table
ALTER TABLE equipment MODIFY COLUMN equipment_id VARCHAR(50) NOT NULL;

-- 2. Verify the schema
DESCRIBE equipment;
DESCRIBE reservation;
DESCRIBE user;
DESCRIBE lab;

