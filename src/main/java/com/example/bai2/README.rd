Việc lặp lại model.addAttribute("categories", ...) vi phạm nguyên tắc: DRY (Don't Repeat Yourself)

Nếu hệ thống có 20 trang đều cần danh sách categories:
Phải sửa 20 chỗ khi dữ liệu thay đổi
Dễ quên update, bug không đồng nhất UI
Code cồng kềnh, khó bảo trì, khó đọc