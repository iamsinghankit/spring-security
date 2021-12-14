package com.singhankit.springsecurity.controller;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author _singhankit
 */
@RestController
public class FooController {

    @GetMapping("/foo")
//    @Async
    public String hello(    ) {
            Runnable run=()-> System.out.println(SecurityContextHolder.getContext().getAuthentication());
            //Will work without inheritableThreadLocal
//        DelegatingSecurityContextRunnable dr=new DelegatingSecurityContextRunnable(run);

        ExecutorService service= Executors.newSingleThreadExecutor();
        DelegatingSecurityContextExecutorService ds=new DelegatingSecurityContextExecutorService(service);
//        service.execute(dr);
        ds.execute(run);
//        service.shutdown();
        ds.shutdown();;
//        System.out.println(authentication);
        return "FOO! ";
    }
}
