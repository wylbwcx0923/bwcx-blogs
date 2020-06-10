package bwcx.yhg.store.consumer.feign.service.fallback;

import bwcx.yhg.store.consumer.feign.service.FeignService;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceFallback implements FeignService {
    @Override
    public String sayHello(String name) {
        return "服务器挂掉了,熔断器启动了";
    }
}
