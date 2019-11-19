-- 导入雇员数据
insert t_employee(id,name,password,email,role,manager_id) values(1, '王中军','123', 'wangzhognjun@163.com', 'boss', NULL);
insert t_employee(id,name,password,email,role,manager_id) values(2, '冯小刚经纪人','123','fengxiaogangManager@163.com', 'manager', 1);
insert t_employee(id,name,password,email,role,manager_id) values(3, '范冰冰经纪人','123','fanbingbingManager@163.com', 'manager', 1);
insert t_employee(id,name,password,email,role,manager_id) values(4, '冯小刚','123','fengxiaogang@163.com', 'user', 2);
insert t_employee(id,name,password,email,role,manager_id) values(5, '范冰冰','123','fanbingbing@163.com', 'user', 3);