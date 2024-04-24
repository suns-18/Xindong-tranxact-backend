package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.request.AccountRegisterRequest;
import neu.xindong.xact.entity.Customer;
import neu.xindong.xact.entity.FollowAccount;
import neu.xindong.xact.entity.PrimeAccount;
import neu.xindong.xact.service.CustomerService;
import neu.xindong.xact.service.FollowAccountService;
import neu.xindong.xact.service.PrimeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static neu.xindong.xact.util.RegisterUtil.CreateCustomerId;
import static neu.xindong.xact.util.RegisterUtil.CreateFollowAccount;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "用户接口", description = "定义用户接口")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FollowAccountService followAccountService;
    @Autowired
    private PrimeAccountService primeAccountService;

    @GetMapping("/getAll")
    @Operation(summary = "获取所有用户", description = "返回所有用户")
    public HttpResponse<List<Customer>> findAll() {
        try {
            List<Customer> customers = customerService.findAll();
            return HttpResponse.success(customers);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }


    @GetMapping("/getById")
    @Operation(summary = "根据id获取用户", description = "返回指定id的用户")
    public HttpResponse<Customer> getCustomerById(Integer id) {
        try {
            Customer customer = customerService.findCustomerById(id);
            return HttpResponse.success(customer);
        } catch (Exception e) {
            return HttpResponse.failureWhenAccessDB();
        }

    }

    @GetMapping("/getLikeId")
    @Operation(summary = "根据id模糊查询用户", description = "返回满足模糊匹配原则的id对应用户")
    public HttpResponse<List<Customer>> getCustomerLikeId(Integer id) {
        try {
            return HttpResponse.success(customerService.findCustomerLikeId(id));
        } catch (Exception e) {
            return HttpResponse.failureWhenAccessDB();
        }
    }

    @PostMapping("/save")
    @Operation(summary = "保存客户",
            description = "输入客户的信息，提交到服务器，返回提交操作的结果")

    public HttpResponse<Object> saveCustomer(
            @RequestBody AccountRegisterRequest accountRegisterRequest) {
        try {

            // 使用AccountRegisterReq对象的数据创建Customer对象
            Customer customer = Customer.builder()
                    .id(CreateCustomerId())
                    .customerName(accountRegisterRequest.getCustomer().getCustomerName())
                    .idType(accountRegisterRequest.getCustomer().getIdType())
                    .idNumber(accountRegisterRequest.getCustomer().getIdNumber())
                    .cuacctCls(accountRegisterRequest.getCustomer().getCuacctCls())
                    .cuacctStatus(accountRegisterRequest.getCustomer().getCuacctStatus())
                    .build();
            PrimeAccount primeAccount = PrimeAccount.builder()
                    .id(customer.getId())
                    .balanceTotal(100000.0)//现在只是统一存入100000元
                    .build();
            // 使用流式编程创建 FollowAccount 列表
            List<FollowAccount> followAccounts = accountRegisterRequest.getFollowAccountList().stream()
                    .map(followAccount -> FollowAccount.builder()
                            .primeAccountId(customer.getId())
                            .market(followAccount.getMarket())
                            .updateTime(followAccount.getUpdateTime())
                            .id(CreateFollowAccount(followAccount.getMarket(), customer.getCuacctCls()))
                            .build())
                    .collect(Collectors.toList());
            followAccountService.saveBatch(followAccounts);
            customerService.save(customer);
            primeAccountService.save(primeAccount);
            return HttpResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }


}
