package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.Commission;

public interface CommissionService extends IService<Commission> {
    Commission findCommissionByCuacctclsAndMarket(Integer cuacctCls);
}
