# 数据库表设计
sys_user:admin admin  账号密码都是admin
**数据库名：** xindong_tranxact

MySQL在Windows平台大小写不敏感

设计数据库时，保持老师给的字典的字段名的大小写一致。

2个借鉴材料
- 公共数据
- 实验指导书

## 一些笔记
3. 枚举上交 深交；资产账户类别；资产账户状态;订单类型（10个（后端实体）
6. 资产账户类别（散户 中户 等）怎么确定的？
7. market ： 数据存入redis缓存
8. 撤单详情（T 撤单；F 正常）改为int表示布尔值
9. 我们这里知道一个委托可以多次成交，那么委托和成交如何实现呢？
10. 做委托的时候显示的可用余额主账户表的资金余额

## 数据表设计正文
### 1. customer

客户信息（客户代码：31355654(固定位数)等同于资产账户代码）

| 字段名              | 字段类型         | 约束          | 字段描述                            |
|------------------|--------------|-------------|---------------------------------|
| customer_name    | varchar(255) | NOT NULL    | 客户名称                            |
| id_type          | varchar(10)  | NOT NULL    | 证件类型 （00 身份证 01 护照 02 军官证）      |
| id_number        | varchar(255) | NOT NULL    | 证件号码                            |
| cuacct_cls       | int          | NOT NULL    | 资产账户类别 （0 散户；1 中户；2 大户；3 机构 ）   |
| cuacct_status    | int          | NOT NULL    | 资产账户状态 （0 正常； 1 冻结； 8 异常； 9 注销） |
| id            ~~ | int          | PRIMARY KEY | 客户代码（自动生成）~~                    |

### 2. stock

证券信息

| 字段名        | 字段类型         | 约束          | 字段描述                 |
|------------|--------------|-------------|----------------------|
| stock_name | varchar(255) | NOT NULL    | 证券名称                 |
| id         | varchar(10)  | PRIMARY KEY | 证券代码                 |
| market     | int          | NOT NULL    | 交易市场 （0 深交所；1 上交所）   |
| stk_cls    | varchar(10)  | NOT NULL    | 证券类别 （S0股票）          |
| stamp      | decimal(.3)  | NOT NULL    | 印花税率                 |
| currency   | varchar(10)  | NOT NULL    | 币种（ 0 人民币；1 港币；2 美元） |


### 3. market_info  (Redis)

市场信息

| 字段名                        | 字段类型        | 约束       | 字段描述   |
|----------------------------|-------------|----------|--------|
| stock_id                   | varchar(10) | KEY      | 证券代码   |
| current_price              | decimal(.2) | NOT NULL | 当前价格   |
| yesterday_collection_price | decimal(.2) | NOT NULL | 昨收价    |
| limit_up_price             | decimal(.2) | NOT NULL | 涨停价格   |
| limit_down_price           | decimal(.2) | NOT NULL | 跌停价格   |
| market_time                | timestamp   | NOT NULL | 当前时间   |
| available_quantity         | int         |          | 最大可买数量 |

### 4. commission

佣金信息

| 字段名              | 字段类型        | 约束          | 字段描述                          |
|------------------|-------------|-------------|-------------------------------|
| id               | int         | PRIMARY KEY | 佣金id                          |
| cuacct_cls       | int         | NOT NULL    | 资产账户类别 （0 散户；1 中户；2 大户；3 机构 ） |
| market           | int         | NOT NULL    | 交易市场 （0 深交所；1 上交所）            |
| stk_cls          | varchar(10) | NOT NULL    | 证券类别 （S0 股票）                  |
| rate             | decimal(.4) | NOT NULL    | 佣金费率                          |


### 5. bank

银行信息

| 字段名         | 字段类型        | 约束          | 字段描述       |
|-------------|-------------|-------------|------------|
| bank_name   | varchar(10) |             | 银行名称       |
| account     | varchar     | NOT NULL    | 银行账号       |
| password    | int         | NOT NULL    | 密码         |
| customer_id | int         |             | 客户代码（自动生成） |
| id          | int         | PRIMARY KEY | 银行id       |

### 6. prime_account

资金账号（主账户） （资金余额=所有附账户资金余额相加）（可用余额=所有附账户可用余额相加）

| 字段名            | 字段类型           | 约束          | 字段描述                          |
|----------------|----------------|-------------|-------------------------------|
| id             | int            | PRIMARY KEY | 资金账号                          |
| balance_total  | DECIMAL(20,4)) | NOT NULL    | 资金余额                          |
| balance_usable | DECIMAL(20,4)) | NOT NULL    | 可用余额                          |
| cuacct_cls     | int            | NOT NULL    | 资产账户类别 （0 散户；1 中户；2 大户；3 机构 ） |
| update_time    | timestamp      | NOT NULL    | 更新时间                          |
| password       | int            | NOT NULL    | 密码                            |


### 7. follow_account

交易所证券账户（从账户缩写）

| 字段名              | 字段类型          | 约束          | 字段描述               |
|------------------|---------------|-------------|--------------------|
| id               | varchar       | PRIMARY KEY | 交易所证券账户            |
| prime_account_id | int           | NOT NULL    | 资金账号               |
| balance          | DECIMAL(20,4) | NOT NULL    | 资金余额               |
| market           | int           | NOT NULL    | 交易市场 （0 深交所；1 上交所） |
| update_time      | timestamp     | NOT NULL    | 更新时间               |


### 8. order_info

委托信息
（#委托金额=委托数量*委托价格）(#成交金额=成交数量*成交价格)(#冻结金额=委托金额)（#解冻金额=委托金额-成交金额）
(#币种=stock.currency) 

| 字段名               | 字段类型        | 约束          | 字段描述              |
|-------------------|-------------|-------------|-------------------|
| id                | int         | PRIMARY KEY | 订单ID              |
| unit              | int         |             | 交易单元              |
| prime_account_id  | int         | NOT NULL    | 资金账号              |
| follow_account_id | varchar(10) | NOT NULL    | 交易所证券账户           |
| stk_cls           | varchar(10) | NOT NULL    | 证券类别 （S0 股票）      |
| rate              | decimal(.4) | NOT NULL    | 佣金费率              |
| trd_id            | char        | NOT NULL    | 买卖类型  (B 买入；S 卖出) |
| stock_id          | varchar(10) | NOT NULL    | 证券代码              |
| order_time        | timestamp   | NOT NULL    | 订单时间              |
| order_status      | char        | NOT NULL    | 订单类型（10个          |
| order_amount      | int         |             | 委托数量              |
| order_price       | decimal(.4) |             | 委托价格              |
| deal_amount       | int         |             | 成交数量              |
| deal_price        | decimal(.4) |             | 成交价格              |
| is_withdraw       | int         |             | 撤单详情（1 撤单；0 正常）   |
| withdraw_amount   | int         |             | 撤单数量              |



### 9. position

持仓信息（股份可用=股份余额-冻结数量+解冻数量）（#股份冻结数量=委托表的委托数量）（#股份解冻数量=委托表的委托数量-委托表的成交数量）



| 字段名               | 字段类型           | 约束          | 字段描述               |
|-------------------|----------------|-------------|--------------------|
| id                | int            | PRIMARY KEY | 持仓ID               |
| prime_account_id  | int            |             | 资金账号               |
| follow_account_id | varchar(10)    | NOT NULL    | 交易所证券账户            |
| stock_id          | varchar(10)    | NOT NULL    | 证券代码               |
| market            | int            | NOT NULL    | 交易市场 （0 深交所；1 上交所） |
| update_time       | timestamp      | NOT NULL    | 更新时间               |
| share_total       | int            |             | 股份余额               |
| share_usable      | int            |             | 股份可用               |

### 10. transaction

成交信息（同一笔委托，成交编号不一样）
(#成交金额=成交数量*成交价格)


| 字段名               | 字段类型        | 约束          | 字段描述            |
|-------------------|-------------|-------------|-----------------|
| id                | int         | PRIMARY KEY | 成交ID            |
| order_id          | int         |             | 委托编号            |
| unit              | int         |             | 交易单元            |
| prime_account_id  | int         | NOT NULL    | 资金账号            |
| follow_account_id | varchar(10) | NOT NULL    | 交易所证券账户         |
| trd_id            | char        |             | 交易类别(B 买入；S 卖出) |
| amount            | int         |             | 成交数量            |
| price             | decimal(.4) |             | 成交价格            |
| transact_time     | timestamp   | NOT NULL    | 成交时间            |


### 11. sys_user

管理员信息

| 字段名      | 字段类型         | 约束          | 字段描述 |
|----------|--------------|-------------|------|
| id       | int          | PRIMARY KEY | 用户ID |
| username | varchar(255) |             | 用户名  |
| password | varchar(255) | NOT NULL    | 密码   |

### 12. trade_unit

交易单元：存五个，到时候随机分配给委托。eg.23296

| 字段名      | 字段类型         | 约束          | 字段描述   |
|----------|--------------|-------------|--------|
| id       | int          | PRIMARY KEY | 交易单元ID |
