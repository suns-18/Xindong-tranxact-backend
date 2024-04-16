# 数据库表设计
MySQL在Windows平台大小写不敏感.
设计数据库时，保持老师给的字典的字段名的大小写一致。
2个借鉴材料：
公共数据
实验指导书
## 1. customer

客户信息

| 字段名           | 字段类型         | 约束          | 字段描述                            |
|---------------|--------------|-------------|---------------------------------|
| id   ？？       | int   ？？     | PRIMARY KEY | 客户id                            |
| name          | varchar(255) | NOT NULL    | 客户名称                            |
| ID_TYPE       | varchar(10)  | NOT NULL    | 证件类型 （00 身份证 01 护照 02 军官证）      |
| id_number     | varchar(255) | NOT NULL    | 证件号码                            |
| CUACCT_CLS    | int          | NOT NULL    | 资产账户类别 （0 散户；1 中户；2 大户；3 机构 ）   |
| CUACCT_STATUS | int          | NOT NULL    | 资产账户状态 （0 正常； 1 冻结； 8 异常； 9 注销） |
| currency ？？   | varchar(10)  | NOT NULL    | 币种                              |
| code          | int   ？？     | NOT NULL    | 客户代码（自动生成）                      |

## 2. stock

证券信息

| 字段名       | 字段类型          | 约束           | 字段描述               |
|-----------|---------------|--------------|--------------------|
| id        | int   ？？      | PRIMARY KEY  | 证券id               |
| name      | varchar(255)  | NOT NULL     | 证券名称               |
| code      | varchar(10)   | NOT NULL     | 证券代码               |
| MARKET    | int           | NOT NULL     | 交易市场 （0 深交所；1 上交所） |
| STK_CLS   | varchar(10)   | NOT NULL     | 证券类别 （S0 股票）       |
| stamp     | decimal(.3)   | NOT NULL     | 印花税率               |


## 3. market

市场信息

| 字段名                        | 字段类型          | 约束            | 字段描述  |
|----------------------------|---------------|---------------|-------|
| current_price              | decimal(.2)   | NOT NULL      | 当前价格  |
| yesterday_collection_price | decimal(.2)   | NOT NULL      | 昨收价   |
| stock_code                 | varchar(10)   | NOT NULL      | 证券代码  |
| limit_up_price             | decimal(.2)   | NOT NULL      | 涨停价格  |
| limit_down_price           | decimal(.2)   | NOT NULL      | 跌停价格  |
| ??                         |               |   PRIMARY KEY |       |

## 4. commission

佣金信息

| 字段名        | 字段类型        | 约束            | 字段描述                          |
|------------|-------------|---------------|-------------------------------|
| CUACCT_CLS | int         | NOT NULL      | 资产账户类别 （0 散户；1 中户；2 大户；3 机构 ） |
| MARKET     | int         | NOT NULL      | 交易市场 （0 深交所；1 上交所）            |
| STK_CLS    | varchar(10) | NOT NULL      | 证券类别 （S0 股票）                  |
| rate       | decimal(.4) | NOT NULL      | 佣金费率                          |
| ??         |             |   PRIMARY KEY |                               |

## 5. account

账户信息

| 字段名         | 字段类型           | 约束          | 字段描述   |
|-------------|----------------|-------------|--------|
| id          | int   ??       | PRIMARY KEY | 资产账户ID |
| customer_id | int   ??       | NOT NULL    | 客户ID   |
| balance     | DECIMAL(20,4)) | NOT NULL    | 资金余额   |

## 6. order

订单信息

| 字段名        | 字段类型        | 约束          | 字段描述               |
|------------|-------------|-------------|--------------------|
| id         | int   ??    | PRIMARY KEY | 订单ID               |
| account_id | int   ??       | PRIMARY KEY | 资产账户ID |
| STK_CLS    | varchar(10) | NOT NULL    | 证券类别 （S0 股票）       |
| rate       | decimal(.4) | NOT NULL    | 佣金费率               |
