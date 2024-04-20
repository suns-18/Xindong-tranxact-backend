package neu.xindong.xact.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import neu.xindong.xact.dto.HttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sysUser")
@Tag(name = "系统用户接口", description = "定义系统用户接口，简单登陆后测试")
public class SysUserController {
    @GetMapping("/test")
    public HttpResponse<String> a(){
        return HttpResponse.success("okkkk");
    }

    @Operation(summary = "退出登陆",
            description = "退出登陆")
    @PostMapping("../auth/logout")
    public void logout(){

    }

}
