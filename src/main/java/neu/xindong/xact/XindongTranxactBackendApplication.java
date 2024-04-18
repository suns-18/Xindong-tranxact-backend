package neu.xindong.xact;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("neu.xindong.xact.dao")
public class XindongTranxactBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(XindongTranxactBackendApplication.class, args);
    }

}
