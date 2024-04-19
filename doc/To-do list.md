## 前端实现

1. 登陆
2. 开户
3. 委托
4. 扯淡

注意：bank删掉
## Dto（作用：直接呈现在前端）里加入：

## Service里加入：

## Control里加入：

## 视图
### 理清关系
四个事：
开户：
1. 操作员录入客户开户资料，要求：
a. 录入的信息

```typescript
interface AccountRegisterReq{
  "customer_name": string
  "id_type": number
  "id_number": number
  "cuacct_cls": number
  "cuacct_status": number = 0 
  "accounts": FollowAccount[]
}
interface FollowAccount{
    // entity.FollowAccount
}
```

b. 将录入的信息保存到后台，并根据规则自动生成客户代码；

c. 保存资料时自动为客户开设资金账户（主），并根据客户模板存入资金。

2. 委托
a. 操作员录入客户委托下单信息，要求：（非临柜-证券交易-普通交易）
```typescript
interface OrderReq{
    customerId: number
    order: Order
}
interface Order{
    trdId: string //(B- S-)
    stockId: number
    followAccountId: number
    orderPrice: double  // 价格 搜索stock之后自动加载数据
    orderAmount: number // 数量 成百成百增，最小100
}
```

b. 录入的委托信息符合证券交易规则，交易业务限制在买入、卖出；
将录入的信息保存到后台，并**自动扣减**客户相关费用或股份。

3. 撤单
a. 操作员查找对应客户的委托信息，并选择需要扯淡的委托，（非临柜-证券交易-委托撤单）
b. 录入的信息符合撤单相关交易规则；(股份或资金未解冻即可撤单)
c. 后台自动生成扯淡信息并保存，退回相关费用或股份，修改委托单状态。
d. 委托错了不能更改，交易成功就必须要认，没成功则可以撤单。

4. 成交
操作员录入委托成交信息[手动成交！]，要求：
a. 录入的信息符合成交相关交易规则；
b. 后台自动生成成交单信息并保存，并自动退回多扣费用，修改委托单状态。