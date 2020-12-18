## sw-backend

Software 练手项目，记录与维护面试题目，顺便看下开源代码

## 练习与思考

```java
第1题：Serializable 有几个方法？分别作用是什么？
第2题：HashMap和ArrayList的初始化容量分别为多大？
第3题：Arrays.asList()返回的集合，能够删除元素吗？
第4题：Object几个方法，重载算一个。
```


## 保留官方 Readme

![active4j](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/logo.png "active4j")


Active4j-boot是基于SpingBoot2.0轻量级的java快速开发框架。以Spring Framework为核心容器，Spring MVC为模型视图控制器，Mybatis Plus为数据访问层， Apache Shiro为权限授权层, Redis为分布式缓存，Quartz为分布式集群调度,layui作为前端框架并进行前后端分离的开源框架。



------------

# 项目介绍
 - Active4j是基于SpingBoot2.0轻量级的java快速开发框架。以Spring Framework为核心容器，Spring MVC为模型视图控制器，Mybatis Plus为数据访问层， Apache Shiro为权限授权层, Redis为分布式缓存，Quartz为分布式集群调度，layui作为前端框架并进行前后端分离的开源框架。
 - Active4j目前内置了部门管理、用户管理、角色管理、菜单管理、数据数据字典、定时任务、常用系统监控等基础功能，并内置了文件上传下载、导入导出、短信功能、邮件发送等常用工具，整合了layui前端常用组件。
 - Active4j定位于企业快速开发平台建设，代码全部开源，持续更新，共同维护。Active4j可以应用在任何J2EE的项目开发中，尤其适合企业信息管理系统（MIS），企业办公系统（OA），客户关系管理系统（CRM），内容管理系统（CMS）等。

# 技术文档
- 讨论加群：qq群①：203802692   qq群②：773872959
- 演示地址：[http://www.active4j.com:9003/demo](http://www.active4j.com:9003/demo "http://www.active4j.com:9003/demo")
- 官方网站：[www.active4j.com](http://www.active4j.com "www.active4j.com")
- 文档地址：[http://www.active4j.com/doc.html](http://www.active4j.com/doc.html "http://www.active4j.com/doc.html")

# 生态系统
|  版本 |  地址 |
| ------------ | ------------ |
|  前后端分离版本github |  [https://github.com/yunchaoyun/active4j](https://github.com/yunchaoyun/active4j "https://github.com/yunchaoyun/active4j") |
| 前后端分离版本gitee  |  [https://gitee.com/active4j/active4j](https://gitee.com/active4j/active4j "https://gitee.com/active4j/active4j") |
|  boot单体版本github | [https://github.com/yunchaoyun/active4j-boot](https://github.com/yunchaoyun/active4j-boot "https://github.com/yunchaoyun/active4j-boot")  |
| boot单体版本gitee  | [https://gitee.com/active4j/active4j-boot](https://gitee.com/active4j/active4j-boot "https://gitee.com/active4j/active4j-boot") |
|jsp版本github|[https://github.com/yunchaoyun/active4j-jsp](https://github.com/yunchaoyun/active4j-jsp "https://github.com/yunchaoyun/active4j-jsp")|
|jsp版本gitee|[https://gitee.com/active4j/active4j-jsp](https://gitee.com/active4j/active4j-jsp "https://gitee.com/active4j/active4j-jsp")|
| 工作流版本github | [https://github.com/yunchaoyun/active4j-flow](https://github.com/yunchaoyun/active4j-flow "https://github.com/yunchaoyun/active4j-flow") |
| 工作流版本gitee | [https://gitee.com/active4j/active4j-flow](https://gitee.com/active4j/active4j-flow "https://gitee.com/active4j/active4j-flow")|
| OA办公系统github|[https://github.com/yunchaoyun/active4j-oa](https://github.com/yunchaoyun/active4j-oa "https://github.com/yunchaoyun/active4j-oa")|
|OA办公系统gitee|[https://gitee.com/active4j/active4j-oa](https://gitee.com/active4j/active4j-oa "https://gitee.com/active4j/active4j-oa")|

# 项目特点
- 开箱即用，节省开发时间，提高开发效率
- 代码全部开源，持续更新，共同维护
- 基于SpringBoot，简化了大量项目配置和maven依赖，让您更专注于业务开发
- 友好的代码结构及注释，便于阅读及二次开发
- 使用分包分层设计，工程拆分，分为dao，service，Controller，view层，层次清楚，低耦合，高内聚。
- 支持分布式部署，session集成了redis
- 灵活的权限控制, 整合shiro，可控制到页面或按钮，满足绝大部分的权限需求,优化权限注解方便权限配置
- 日志记录采用aop(LogAop类)方式，可对用户所有操作进行记录
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入swagger文档支持，方便编写API接口文档
- 项目采用前后端分离架构，提升效率
- 前端组件丰富，集成layuiadmin，基本涵盖了所有前端开发需求
- 前端页面简洁优美，支持移动端
- 数据统计报表：丰富的报表统计功能
- 支持多种浏览器: Google, 火狐, IE,360等


# 组织结构

```html
active4j
├── active4j-common  公有工具类工程
    - com.active4j.common.cache 本地缓存
    - com.active4j.common.context.util  容器工具
    - com.active4j.common.func  常用功能组件
    - com.active4j.common.redis 集成redis
    - com.active4j.common.threadpool 集成线程池
    - com.active4j.common.util  常用工具包
    - com.active4j.common.web.config  springmvc配置
├── active4j-entity  实体工程
    - com.active4j.entity.base 基础实体
    - com.active4j.entity.commcon 公用实体
    - com.active4j.entity.func 系统常用组件功能实体
    - com.active4j.entity.system 系统管理模块实体
├── active4j-dao     dao层
    - com.active4j.dao.config 数据源配置
    - com.active4j.dao.func 系统常用组件功能
    - com.active4j.dao.system.dao  系统管理模块
    - com.active4j.dao.system.dao.sql sql文件
├── active4j-service service层
    - com.active4j.service.func 系统常用组件功能服务
    - com.active4j.service.monitor 系统监控模块服务
    - com.active4j.service.system 系统管理模块服务
    - com.active4j.service.system.util 系统管理工具包
├── active4j-web     controller层
    - com.active4j.web.core 核心配置包，包括shiro、springmvc、swagger2等
    - com.active4j.web.func 系统常用组件功能控制器
    - com.active4j.web.monitor.controller 监控模块控制器
    - com.active4j.web.system.controller 系统管理模块控制器
    - com.active4j.web.system.wrapper 响应结果集包装工具包
    - com.active4j.web.common.controller 公共控制器包
├── active4j-ui      基于layui的前端UI，前后端分离


```


# 技术选型
### 服务端
| 技术 | 说明 | 官网  |
| ------------ | ------------ | ------------ |
|Spring Boot2|核心框架|[https://spring.io/projects/spring-boot/](https://spring.io/projects/spring-boot/ "https://spring.io/projects/spring-boot/")|
|Spring MVC|视图框架|[http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc "http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc")|
|Apache Shiro|权限框架|[http://shiro.apache.org/](http://shiro.apache.org/ "http://shiro.apache.org/")|
|MyBatis|持久层框架|[http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html "http://www.mybatis.org/mybatis-3/zh/index.html")|
|MyBatis-Plus|MyBatis增强工具|[https://mp.baomidou.com/](https://mp.baomidou.com/ "https://mp.baomidou.com/")|
|Alibaba Druid|数据库连接池|[https://github.com/alibaba/druid](https://github.com/alibaba/druid "https://github.com/alibaba/druid")|
|Redis|分布式缓存数据库|[https://redis.io/](https://redis.io/ "https://redis.io/")|
|Quartz|作业调度框架|[http://www.quartz-scheduler.org/](http://www.quartz-scheduler.org/ "http://www.quartz-scheduler.org/")|
|ActiveMQ|消息队列|[http://activemq.apache.org/](http://activemq.apache.org/ "http://activemq.apache.org/")|
|SLF4J|日志组件|[http://www.slf4j.org/](http://www.slf4j.org/ "http://www.slf4j.org/")|
|QcloudCOS|腾讯云存储|[https://www.qcloud.com/product/cos](https://www.qcloud.com/product/cos "https://www.qcloud.com/product/cos")|
|Maven|项目构建管理|[http://maven.apache.org/](http://maven.apache.org/ "http://maven.apache.org/")|
|swagger2|文档生成工具|[https://swagger.io/](https://swagger.io/ "https://swagger.io/")|
|Apache Commons|工具类|[http://commons.apache.org/](http://commons.apache.org/ "http://commons.apache.org/")|
|fastjson|JSON解析库|[https://github.com/alibaba/fastjson](https://github.com/alibaba/fastjson "https://github.com/alibaba/fastjson")|
### 前端
|技术|名称|官网|
| ------------ | ------------ | ------------ |
|jQuery|js库|[http://jquery.com/](http://jquery.com/ "http://jquery.com/")|
|layui.table|数据表格|[https://www.layui.com/doc/modules/table.html](https://www.layui.com/doc/modules/table.html "https://www.layui.com/doc/modules/table.html")|
|bootstrap-treeview|树形结构|[http://www.treejs.cn/v3/](http://www.treejs.cn/v3/ "http://www.treejs.cn/v3/")|
|layui|前端框架|[https://www.layui.com/](https://www.layui.com/ "https://www.layui.com/")|
|layui.layer|弹出组件|[https://www.layui.com/doc/modules/layer.html](https://www.layui.com/doc/modules/layer.html "https://www.layui.com/doc/modules/layer.html")|
|layui.treeTable|树形表格|[https://fly.layui.com/jie/30625/](https://fly.layui.com/jie/30625/ "https://fly.layui.com/jie/30625/")|


