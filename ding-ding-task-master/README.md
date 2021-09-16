# DingDingTask

#### 介绍
# 本项目仅仅用于学习交流，不得用于商业用途和非法目的
Android实现钉钉自动打卡。只需要一台不用的手机放在办公室，每天自动钉钉打卡，从此不再担心缺卡了

#### 软件架构
AlarmManager设定闹钟——>闹钟发送广播通知——>广播接受器接到通知——>点亮屏幕
——>启动钉钉——>AccessibilityService无障碍服务监听钉钉打卡页面——>
获取钉钉页面元素分析找到对应按钮节点——>模拟单击按钮动作执行对应操作（如找到“考勤签到”按钮执行单击操作）——>返回主页

##### 为了增加保活率，实现了开机自动打开本应用功能。建议在早上闹钟打卡之前，设定手机自动开机，然后打开本应用，本应用根据设定的闹钟启动钉钉应用程序


#### 安装教程

1.  下载
2.  编译打包


#### 使用说明

1.  启动无障碍服务
2.  授予应用自启动权限

#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
