Activiti-Demo
=======

[![Activiti](https://img.shields.io/badge/activiti-v6.0.0-blue)](https://github.com/Activiti/Activiti)
[![Maven Central](https://img.shields.io/badge/maven--central-v3.5.6-yellowgreen)](https://github.com/apache/maven) [![ASL 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/Activiti/Activiti/blob/master/LICENSE.txt)

**__activiti学习案例__**

[参考书](https://www.activiti.org/userguide/)

Configuring Eclipse or 
-------------------

* 下载Eclipse的Activiti BPMN 2.0 designer插件

	* 点击eclipse上方工具栏的Help，选择Install New Software
	* 点击add,填写location: 【http://activiti.org/designer/update/】 然后执行下一步到安装完成
	* 重启eclipse完成插件安装 
	* 点击Window -> Perferences -> Activiti -> Save Action -> 勾选 Create process  definition image when saving the diagram

* 测试程序使用默认的配置类来创建ProcessEngine,配置见 AbstractProcessTest 类