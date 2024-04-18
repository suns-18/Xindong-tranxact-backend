package neu.xindong.xact.service;

import com.baomidou.mybatisplus.extension.service.IService;
import neu.xindong.xact.entity.Bank;

import java.util.List;

public interface BankService extends IService<Bank> {
    List<Bank> findBankByCustomerId(Integer customerId);
}
