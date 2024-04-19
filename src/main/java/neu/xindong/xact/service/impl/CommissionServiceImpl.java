package neu.xindong.xact.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import neu.xindong.xact.dao.CommissionDao;
import neu.xindong.xact.entity.Commission;
import neu.xindong.xact.service.CommissionService;
import org.springframework.stereotype.Service;

@Service
public class CommissionServiceImpl extends ServiceImpl<CommissionDao, Commission>
        implements CommissionService {

    @Override
    public Commission findCommissionByCuacctclsAndMarket(Integer cuacctCls) {
        return query().eq("cuacct_cls",cuacctCls).list().get(0);
    }
}
