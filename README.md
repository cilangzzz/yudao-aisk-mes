# 汽车整车装配MES系统

## 项目简介

本MES系统是由 [yudao-aisk-mes](https://github.com/cilangzzz/yudao-aisk-mes) 的MES模型方案自动生成。

> 模型方案来源：`software-dev-ai-workflow\5.0-系统模型\mes\车企模型`

**基础系统实现**：由 [yudao-skill-pro](https://github.com/cilangzzz/yudao-skill-pro) 提供开发技能规范、设计模式和代码模板，包括：

- **设计规范**：数据库设计、实体类设计、API设计、CRUD代码生成规范
- **模块技能**：系统管理、基础设施、支付、会员、商城、CRM、ERP、工作流、AI、IoT等12个核心模块
- **设计模式**：工厂模式、策略模式、模板方法模式
- **使用样例**：快速入门、新模块开发、模块扩展、重构指南

基于芋道框架(yudao-aisk-mes) SaaS平台开发，专注于汽车整车装配制造的执行管理系统。

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.7 | 后端框架 |
| MyBatis Plus | - | ORM框架 |
| Redis | - | 缓存 |
| MySQL | - | 数据库 |
| Flowable | - | 工作流引擎 |
| UniApp | - | 移动端多端支持 |

## 业务流程

```
生产计划接收 → 物料准备配送 → 装配执行作业 → 质量检验确认 → 入库登记完成
     ↓              ↓              ↓              ↓              ↓
  工单生成      线边库存管理    工位作业记录    过程检验/终检    VIN绑定/入库
```

## 模块清单

| 序号 | 模块名称 | 优先级 | 说明 |
|------|----------|--------|------|
| 1 | 生产工单管理 | P0 | 工单创建、派工、进度跟踪 |
| 2 | 工艺路线管理 | P0 | 工序定义、工艺版本管理 |
| 3 | 工作站管理 | P0 | 工位配置、设备绑定 |
| 4 | 装配作业执行 | P0 | 作业记录、关键件绑定 |
| 5 | 物料管理 | P0 | 线边库存、物料消耗 |
| 6 | 质量管理 | P1 | 过程检验、不合格处理 |
| 7 | 生产追溯 | P0 | VIN追溯、装配历史查询 |
| 8 | 生产看板 | P1 | 实时监控、统计报表 |
| 9 | 设备集成 | P1 | PLC、扭矩枪、扫码枪集成 |
| 10 | 移动终端 | P0 | 现场作业移动端支持 |
| 11 | ERP系统对接 | P0 | 计划层系统集成 |

## 项目结构

```
yudao-aisk-mes/
├── yudao-module-mes/          # MES核心模块
├── yudao-module-erp/          # ERP模块
├── yudao-module-iot/          # IoT设备集成模块
├── yudao-module-bpm/          # 工作流模块
├── yudao-framework/           # 框架核心
├── sql/                       # 数据库脚本
├── docs/                      # 文档
└── script/                    # 部署脚本
```

## 设备集成

| 设备类型 | 集成方式 |
|----------|----------|
| PLC | IoT模块对接 |
| 扭矩枪/拧紧机 | 数据采集接口 |
| 条码扫描枪 | 移动终端集成 |

## 快速开始

1. 导入数据库脚本：`sql/mes/`
2. 配置数据库连接和Redis
3. 启动后端服务
4. 访问管理后台配置MES模块

## 相关资源

- [MES模型方案](https://github.com/cilangzzz/yudao-aisk-mes) - 业务需求模型
- [基础系统实现](https://github.com/cilangzzz/yudao-skill-pro) - 开发技能规范与代码模板
- [芋道框架原项目](https://github.com/YunaiV/ruoyi-vue-pro)

## License

本项目遵循 MIT 许可证。