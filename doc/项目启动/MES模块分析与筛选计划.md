# MES 模块分析与筛选计划

## 背景

用户希望实现 `H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes` 目录下的 MES 系统，需要判断哪些模块是不需要的或可以简化的。

## 一、MES 模型目录结构概览

```
H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\
├── 产品/
│   ├── PRD-汽车整车装配MES系统.md
│   └── UserStory-汽车整车装配MES系统.md
├── 研发/
│   ├── 系统架构设计.md
│   ├── 数据库设计文档.md
│   └── API设计文档.md
├── 00-MES系统需求总览.md
├── 01-生产工单管理模块.md (P0)
├── 02-工艺路线管理模块.md (P0)
├── 03-工作站管理模块.md (P0)
├── 04-装配作业执行模块.md (P0)
├── 05-物料管理模块.md (P0)
├── 06-质量管理模块.md (P1)
├── 07-生产追溯模块.md (P0)
├── 08-生产看板模块.md (P1)
├── 09-设备集成模块.md (P1)
├── 10-移动终端模块.md (P0)
├── 11-ERP系统需求与架构设计.md (P0)
└── wise-dazzling-scroll.md
```

## 二、用户确认的实施方案

用户选择：
- ✅ 工艺路线管理：**保留完整功能**（MES 独立管理工艺路线）
- ✅ 质量管理：**保留完整功能**（MES 独立管理质量流程）
- 📦 实施范围：**仅 P0 核心模块**

### 第一阶段：P0 核心模块实现

```
yudao-module-mes/
├── yudao-module-mes-api          # API 接口定义
└── yudao-module-mes-biz          # 业务实现
    ├── mes-work-order           # 生产工单管理
    ├── mes-routing              # 工艺路线管理（完整功能）
    ├── mes-workstation          # 工作站管理
    ├── mes-assembly-execution   # 装配作业执行
    ├── mes-material             # 物料管理（线边库存+消耗）
    ├── mes-quality              # 质量管理（完整功能）
    ├── mes-traceability         # 生产追溯
    └── mes-mobile               # 移动终端接口
```

### P0 模块清单（本次实施）

| 序号 | 模块名称 | 说明 | 数据表 |
|------|----------|------|--------|
| 1 | 生产工单管理 | 工单创建、下发、进度、关闭 | mes_work_order |
| 2 | 工艺路线管理 | 工艺创建、工序配置、版本管理 | mes_routing, mes_operation, mes_operation_material |
| 3 | 工作站管理 | 工位信息、类型管理 | mes_workstation |
| 4 | 装配作业执行 | 扫码作业、工序报工、关键件绑定 | mes_operation_record, mes_key_part_bind |
| 5 | 物料管理 | 线边库存、物料消耗、缺料预警 | mes_line_stock, mes_material_consume |
| 6 | 质量管理 | 过程检验、终检、不合格处理 | mes_quality_record, mes_defect_handle |
| 7 | 生产追溯 | VIN正向追溯、关键件反向追溯 | 复用作业记录表 |
| 8 | 移动终端 | UniApp移动端接口 | - |

### 基础数据表（系统管理）

| 表名 | 说明 |
|------|------|
| mes_workshop | 车间表 |
| mes_production_line | 产线表 |
| mes_team | 班组表 |
| mes_shift | 班次表 |
| mes_product | 产品表 |
| mes_product_bom | 产品BOM表 |

### 第二阶段：P1 扩展模块（后续迭代）

| 模块 | 说明 |
|------|------|
| 生产看板 | 实时展示生产状态、产量统计、异常报警 |
| 设备集成 | 扭矩枪/PLC/扫描枪等设备数据采集 |

## 三、与现有项目的集成点

### 现有 ERP 模块（yudao-module-erp）

已实现的功能：
- 产品管理（产品信息、分类、单位）
- 采购管理
- 销售管理
- 库存管理

### MES 与 ERP 的数据交互

```
ERP → MES (可选对接):
├── 物料主数据同步
└── 产品信息同步

MES 独立管理（用户选择保留完整功能）:
├── 工艺路线 - 完整管理
├── 质量管理 - 完整管理
├── 生产计划/工单 - 独立管理
└── 物料消耗 - 线边库存管理
```

## 四、实施计划

### 关键文件路径

| 文件 | 用途 |
|------|------|
| `H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\00-MES系统需求总览.md` | 需求总览 |
| `H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\研发\数据库设计文档.md` | 数据库设计 |
| `H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\研发\API设计文档.md` | API 设计 |
| `H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\01~10-各模块.md` | 模块详细需求 |

### 实施步骤

1. 创建 `yudao-module-mes` 模块结构
2. 实现基础数据表（车间、产线、班组、班次、产品、BOM）
3. 按顺序实现 P0 模块
4. 开发移动终端接口
5. 与现有框架集成测试

---

## 五、P0 模块多 Agent Team 执行命令

### 创建 MES 开发团队

```
创建团队，实现 MES 系统 P0 核心模块

团队名称: mes-p0-team
团队描述: 实现 MES 系统 8 个 P0 核心模块
```

### 团队成员配置（建议 4 个 Agent 并行）

| Agent | 角色 | 负责模块 | 参考 Skill 文档 |
|-------|------|----------|----------------|
| **mes-base-agent** | 基础模块开发 | 基础数据 + 工作站管理 | skill-system.yaml, skill-infra.yaml |
| **mes-work-agent** | 作业模块开发 | 工单管理 + 装配作业执行 | skill-erp.yaml |
| **mes-material-agent** | 物料模块开发 | 工艺路线 + 物料管理 | skill-erp.yaml |
| **mes-trace-agent** | 追溯模块开发 | 生产追溯 + 移动终端 | skill-erp.yaml, skill-iot.yaml |

### 各 Agent 执行命令

#### 1. mes-base-agent（基础模块）

```markdown
参考 Skill 文档 skills/modules/system/skill-system.yaml 和 skills/modules/infra/skill-infra.yaml，
实现 MES 系统基础模块：

## 任务清单
1. 创建 yudao-module-mes 模块结构（参考 new-module.md）
2. 实现基础数据表：
   - mes_workshop（车间表）
   - mes_production_line（产线表）
   - mes_team（班组表）
   - mes_shift（班次表）
   - mes_product（产品表）
   - mes_product_bom（产品BOM表）
3. 实现工作站管理模块：
   - mes_workstation（工作站表）
   - 工作站 CRUD 接口

## 参考需求文档
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\00-MES系统需求总览.md
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\03-工作站管理模块.md
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\研发\数据库设计文档.md

## 代码规范
- 遵循项目现有的代码规范
- 使用统一响应格式 CommonResult
- 添加必要的权限控制
- 继承 TenantBaseDO 支持多租户
```

#### 2. mes-work-agent（作业模块）

```markdown
参考 Skill 文档 skills/modules/erp/skill-erp.yaml，
实现 MES 系统作业模块：

## 任务清单
1. 实现生产工单管理模块：
   - mes_work_order（生产工单表）
   - 工单创建、下发、进度、关闭接口
   - 状态流转：待下发 -> 已下发 -> 生产中 -> 已完成
2. 实现装配作业执行模块：
   - mes_operation_record（作业记录表）
   - mes_key_part_bind（关键件绑定表）
   - 扫码识别接口（VIN码/工单码/物料码）
   - 工序报工接口
   - 关键件绑定接口

## 参考需求文档
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\01-生产工单管理模块.md
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\04-装配作业执行模块.md
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\研发\API设计文档.md

## 业务规则
- VIN码格式：17位字母数字组合
- 工单码格式：WO+日期+流水（14位）
- 工单生效后不可删除
```

#### 3. mes-material-agent（物料模块）

```markdown
参考 Skill 文档 skills/modules/erp/skill-erp.yaml，
实现 MES 系统物料模块：

## 任务清单
1. 实现工艺路线管理模块：
   - mes_routing（工艺路线表）
   - mes_operation（工序定义表）
   - mes_operation_material（工序物料表）
   - 工艺路线版本管理
2. 实现物料管理模块：
   - mes_line_stock（线边库存表）
   - mes_material_consume（物料消耗记录表）
   - 线边库存查询接口
   - 物料消耗记录接口
   - 缺料预警接口

## 参考需求文档
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\02-工艺路线管理模块.md
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\05-物料管理模块.md

## 业务规则
- 工艺路线生效后不可修改，需创建新版本
- 物料消耗自动扣减线边库存
- 库存低于安全库存时自动触发预警
```

#### 4. mes-trace-agent（追溯模块）

```markdown
参考 Skill 文档 skills/modules/erp/skill-erp.yaml 和 skills/modules/iot/skill-iot.yaml，
实现 MES 系统追溯模块：

## 任务清单
1. 实现生产追溯模块：
   - VIN 正向追溯接口（车辆信息、工单、作业记录、关键件绑定、质量记录）
   - 关键件反向追溯接口（零部件信息、绑定车辆VIN、绑定时间/工位/操作员）
2. 实现移动终端模块：
   - 登录认证接口
   - 扫码作业接口
   - 关键件扫描接口
   - 离线作业缓存机制

## 参考需求文档
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\07-生产追溯模块.md
- H:\Documents\software-dev-ai-workflow\5.0-系统模型\mes\车企模型\10-移动终端模块.md

## 业务规则
- 追溯数据永久保存，不可删除
- 移动端支持微信小程序/H5/APP
```

### 执行流程

```
1. 使用 TeamCreate 创建团队: mes-p0-team
2. 创建任务列表:
   - Task 1: 基础模块开发 (mes-base-agent)
   - Task 2: 作业模块开发 (mes-work-agent)
   - Task 3: 物料模块开发 (mes-material-agent)
   - Task 4: 追溯模块开发 (mes-trace-agent)
3. 启动 4 个 Agent 并行开发
4. 定期检查进度，协调依赖关系
5. 完成后进行集成测试
```

---

## 六、最终结论

- ✅ 所有模块文档保留（不删除）
- ✅ 本次实施范围：P0 核心模块（8个模块）
- ✅ 保留完整功能：工艺路线管理、质量管理
- 📦 后续迭代：P1 模块（生产看板、设备集成）