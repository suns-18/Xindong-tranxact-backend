package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.TradeUnit;

import java.util.List;

public interface TradeUnitService extends IService<TradeUnit> {
    List<TradeUnit>findAll();
}
