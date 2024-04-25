package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.OrderInfo;
import neu.xindong.xact.entity.Position;
import neu.xindong.xact.entity.Stock;
import neu.xindong.xact.entity.Transaction;

import java.util.List;

public interface PositionService extends IService<Position> {
    List<Position> findPositionByPrimeAccountId(Integer primeAccountId);

    Position findPositionByStockId(String stockId,Integer primeAccountId);


    //委托买  不实现
    //委托卖 冻结股票 可用=余额-委托数量 余额不变
    boolean reduceShareByOrder(Position position,OrderInfo orderInfo);

    //撤单买 不实现
    //撤单卖 可用=余额 余额不变
    boolean increaseShareByOrder(Position position);



    //成交买 余额=余额+成交 可用=可用+成交
    boolean increaseShareByDeal(Position position,Stock stock,OrderInfo orderInfo, Transaction transaction);


    //成交卖 余额=余额-成交 可用不变
    boolean reduceShareByDeal(Position position, Transaction transaction);
}
