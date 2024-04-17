# 数据库表设计
MySQL在Windows平台大小写不敏感。
设计数据库时，保持老师给的字典的字段名的大小写一致。

2个借鉴材料：
- 公共数据
- 实验指导书

1. 小写

2. 客户代码：31355654(固定位数)

3. 枚举上交 深交；资产账户类别；资产账户状态;订单类型（10个（后端实体）

4. 币种：同一账户对应一种币，外币交易时自动按汇率转换？ 存疑 是否可以在客户开户时确认币种？

5. 客户表属性有银行账户吗？

6. 资产账户类别（散户 中户 等）怎么确定的？

7. market ： 数据存入redis缓存

8. 撤单详情（T 撤单；F 正常）改为int表示布尔值

9. 我们这里知道一个委托可以多次成交，那么委托和成交如何实现呢？是否是在委托表里加入成交字段显示是否成交呢还是单独建立一个委托-成交联系表？

## 1. customer

客户信息

| 字段名           | 字段类型         | 约束          | 字段描述                            |
|---------------|--------------|-------------|---------------------------------|
| name          | varchar(255) | NOT NULL    | 客户名称                            |
| id_type       | varchar(10)  | NOT NULL    | 证件类型 （00 身份证 01 护照 02 军官证）      |
| id_number     | varchar(255) | NOT NULL    | 证件号码                            |
| cuacct_cls    | int          | NOT NULL    | 资产账户类别 （0 散户；1 中户；2 大户；3 机构 ）   |
| cuacct_status | int          | NOT NULL    | 资产账户状态 （0 正常； 1 冻结； 8 异常； 9 注销） |
| currency ？？   | varchar(10)  | NOT NULL    | 币种（ 0 人民币；1 港币；2 美元）            |
| code          | int          | PRIMARY KEY | 客户代码（自动生成）                      |
|               |              |             |                                 |

## 2. stock

证券信息

| 字段名     | 字段类型         | 约束          | 字段描述               |
|---------|--------------|-------------|--------------------|
| id      | int   ？？     | PRIMARY KEY | 证券id               |
| name    | varchar(255) | NOT NULL    | 证券名称               |
| code    | varchar(10)  | PRIMARY KEY | 证券代码               |
| MARKET  | int          | NOT NULL    | 交易市场 （0 深交所；1 上交所） |
| STK_CLS | varchar(10)  | NOT NULL    | 证券类别 （S0 股票）       |
| stamp   | decimal(.3)  | NOT NULL    | 印花税率               |


## 3. market_info  (redis)

市场信息

| 字段名                        | 字段类型        | 约束       | 字段描述 |
|----------------------------|-------------|----------|------|
| current_price              | decimal(.2) | NOT NULL | 当前价格 |
| yesterday_collection_price | decimal(.2) | NOT NULL | 昨收价  |
| stock_code                 | varchar(10) | key      | 证券代码 |
| limit_up_price             | decimal(.2) | NOT NULL | 涨停价格 |
| limit_down_price           | decimal(.2) | NOT NULL | 跌停价格 |
| market_time                | timestamp   | NOT NULL | 当前时间 |

## 4. commission

佣金信息

| 字段名        | 字段类型        | 约束          | 字段描述                          |
|------------|-------------|-------------|-------------------------------|
| CUACCT_CLS | int         | NOT NULL    | 资产账户类别 （0 散户；1 中户；2 大户；3 机构 ） |
| market     | int         | NOT NULL    | 交易市场 （0 深交所；1 上交所）            |
| STK_CLS    | varchar(10) | NOT NULL    | 证券类别 （S0 股票）                  |
| rate       | decimal(.4) | NOT NULL    | 佣金费率                          |
| id         | int         | PRIMARY KEY | 佣金id                          |

## 5. account

账户信息

| 字段名           | 字段类型           | 约束          | 字段描述                          |
|---------------|----------------|-------------|-------------------------------|
| id            | int            | PRIMARY KEY | 资产账户ID                        |
| customer_code | int            | NOT NULL    | 客户ID                          |
| balance       | DECIMAL(20,4)) | NOT NULL    | 资金余额                          |
| market        | int            | NOT NULL    | 交易市场 （0 深交所；1 上交所）            |
| cuacct_cls    | int            | NOT NULL    | 资产账户类别 （0 散户；1 中户；2 大户；3 机构 ） |
| stock_code    | varchar(10)    | NOT NULL    | 证券代码                          |
| update_time   | timestamp      | NOT NULL    | 更新时间                          |

## 6. order_info

订单信息

| 字段名          | 字段类型        | 约束          | 字段描述              |
|--------------|-------------|-------------|-------------------|
| id           | int         | PRIMARY KEY | 订单ID              |
| account_id   | int         | NOT NULL    | 资产账户ID            |
|              |             | NOT NULL    |                   |
| stk_cls      | varchar(10) | NOT NULL    | 证券类别 （S0 股票）      |
| rate         | decimal(.4) | NOT NULL    | 佣金费率              |
| trd_id       | char        | NOT NULL    | 买卖类型  (B 买入；S 卖出) |
| stock_code   | varchar(10) | NOT NULL    | 证券代码              |
| order_time   | timestamp   | NOT NULL    | 订单时间              |
| order_status | char        | NOT NULL    | 订单类型（10个          |
| is_withdraw  | int         |             | 撤单详情（1 撤单；0 正常）   |
| order_amount | int         |             | 委托数量              |
|              |             |             |                   |
