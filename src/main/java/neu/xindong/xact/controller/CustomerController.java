package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.request.AccountRegisterRequest;
import neu.xindong.xact.entity.Customer;
import neu.xindong.xact.entity.FollowAccount;
import neu.xindong.xact.service.BankService;
import neu.xindong.xact.service.CustomerService;
import neu.xindong.xact.service.FollowAccountService;
import neu.xindong.xact.service.PrimeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import static neu.xindong.xact.util.RegisterUtil.createFollowAccount;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "用户接口", description = "定义用户接口")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BankService bankService;
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
    public HttpResponse<Customer> getCustomerById(@RequestParam Integer id) {
        try {
            Customer customer = customerService.findCustomerById(id);
            return HttpResponse.success(customer);
        } catch (Exception e) {
            return HttpResponse.failureWhenAccessDB();
        }

    }

    @GetMapping("/getLikeId")
    @Operation(summary = "根据id模糊查询用户", description = "返回满足模糊匹配原则的id对应用户")
    public HttpResponse<List<Customer>> getCustomerLikeId(@RequestParam Integer id) {
        try {
            return HttpResponse.success(customerService.findCustomerLikeId(id));
        } catch (Exception e) {
            return HttpResponse.failureWhenAccessDB();
        }
    }

    @GetMapping("/generateCustomerId")
    @Operation(summary = "生成客户代码",
            description = "返回客户代码")
    public HttpResponse<Integer> generateCustomerId() {
        try {
            var lastCustomer = customerService.findLastCustomerOrderById();
            if (lastCustomer == null) {
                //10000001 ->lastCustomer;
                return HttpResponse.success(44444444);
            } else {
                return HttpResponse.success(lastCustomer.getId() + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }

    @PostMapping("/save")
    @Operation(summary = "保存客户",
            description = "输入客户的信息，提交到服务器，返回提交操作的结果")

    public HttpResponse<Object> saveCustomer(
            @RequestBody AccountRegisterRequest request) {
        try {
            // 使用流式编程创建 FollowAccount 列表
            List<FollowAccount> followAccounts = request.getMarket().stream()
                    .map(market -> FollowAccount.builder()
                            .primeAccountId(request.getPrimeAccount().getId()) // 假设这里需要客户ID作为主账户ID
                            .market(market)
                            .updateTime(new Date())
                            // 设置当前时间为更新时间
                            // 假设id是自动生成的
                            .id(createFollowAccount(
                                    market, request.getCustomer().getCuacctCls()
                            ))
                            // balanceTotal和balanceUsable在这里是useless，根据实际情况设置
                            .balanceTotal(0.0)
                            .balanceUsable(0.0)
                            .build())
                    .collect(Collectors.toList());
            //确保一致
            request.getBank().setCustomerId(request.getCustomer().getId());
            request.getPrimeAccount().setId(request.getCustomer().getId());
            customerService.save(request.getCustomer());
            bankService.save(request.getBank());
            primeAccountService.save(request.getPrimeAccount());
            followAccountService.saveBatch(followAccounts);
            return HttpResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }


}
