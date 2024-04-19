package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import neu.xindong.xact.entity.Customer;
import neu.xindong.xact.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "用户接口", description = "定义用户接口")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAll")
    @Operation(summary = "获取所有用户",description = "返回所有用户")
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


}
