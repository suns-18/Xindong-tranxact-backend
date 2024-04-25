package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.Stock;

import java.util.List;

public interface StockService extends IService<Stock> {

    List<Stock>findAll();

    List<Stock>findStockLikeId(String id);
}
