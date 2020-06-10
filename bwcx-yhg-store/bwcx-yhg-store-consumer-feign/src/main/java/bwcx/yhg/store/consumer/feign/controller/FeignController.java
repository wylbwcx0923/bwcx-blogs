package bwcx.yhg.store.consumer.feign.controller;


import bwcx.yhg.store.consumer.feign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    @GetMapping(value = "/hi/{name}")
    String sayHello(@PathVariable String name){
        return feignService.sayHello(name);
    }
}
