package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.dto.request.AccountRegisterRequest;
import neu.xindong.xact.entity.Customer;
import neu.xindong.xact.entity.FollowAccount;
import neu.xindong.xact.service.CustomerService;
import neu.xindong.xact.service.FollowAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "用户接口", description = "定义用户接口")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FollowAccountService followAccountService;
    @GetMapping("/getAll") @Operation(summary = "获取所有用户",description = "返回所有用户")
    public HttpResponse<List<Customer>> findAll(){
        try{
            List<Customer> customers=customerService.findAll();
            return HttpResponse.success(customers);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResponse.failureWhenAccessDB();
        }
    }


    @GetMapping("/getById")
    @Operation(summary = "根据id获取用户", description = "返回用户")
    public HttpResponse<Customer> getCustomerById(Integer id) {
        try {
            Customer customer=customerService.findCustomerById(id);
            return HttpResponse.success(customer);
        } catch (Exception e) {
            return HttpResponse.failure(0, "数据库访问错误");
        }

    }
    @PostMapping("/save")
    @Operation(summary = "保存客户",
            description = "输入客户的信息，提交到服务器，返回提交操作的结果")

    public HttpResponse<Object> saveCustomer(
            @RequestBody AccountRegisterRequest accountRegisterRequest) {
        try {
            int number = accountRegisterRequest.getFollowAccountList().size();
            // 使用AccountRegisterReq对象的数据创建Customer对象
            Customer customer = Customer.builder()
                    .customerName(accountRegisterRequest.getCustomerName())
                    .idType(accountRegisterRequest.getIdType())
                    .idNumber(accountRegisterRequest.getIdNumber())
                    .cuacctCls(accountRegisterRequest.getCuacctCls())
                    .cuacctStatus(accountRegisterRequest.getCuacctStatus())
                    .build();
            // 使用流式编程创建 FollowAccount 列表
            List<FollowAccount> followAccounts = accountRegisterRequest.getFollowAccountList().stream()
                    .map(followAccount -> FollowAccount.builder()
                            .primeAccountId(customer.getId()) // 假设需要复制的字段
                            .balanceTotal(followAccount.getBalanceTotal())
                            .balanceUsable(followAccount.getBalanceUsable())
                            .market(followAccount.getMarket())
                            .updateTime(followAccount.getUpdateTime())
                            .id(followAccount.getId())
                            .build())
                    .collect(Collectors.toList());
            followAccountService.saveBatch(followAccounts);
            customerService.save(customer);
            return HttpResponse.success();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResponse.failure(
                    0, "操作失败，数据库访问错误");
        }
    }


}
