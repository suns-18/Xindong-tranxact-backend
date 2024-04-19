package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.TradeUnitDao;
import neu.xindong.xact.entity.TradeUnit;
import neu.xindong.xact.service.TradeUnitService;

import java.util.List;

public class TradeUnitServiceImpl extends ServiceImpl<TradeUnitDao, TradeUnit>
        implements TradeUnitService {
    @Override
    public List<TradeUnit> findAll() {
        return query().list();
    }
}
