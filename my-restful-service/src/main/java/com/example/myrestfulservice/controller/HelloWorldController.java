package com.example.myrestfulservice.controller;

import com.example.myrestfulservice.bean.Author;
import com.example.myrestfulservice.bean.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;
    private LocaleResolver localeResolver;

    @Autowired
    public HelloWorldController(MessageSource messageSource, LocaleResolver localeResolver) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    // HTTP METHDO    -> GET
    // URI (Endpoint) -> hello-world
    // Java method (RequestMapping)  -> GetMapping

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("hello-world-bean");
    }

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable(value = "name") String name) {
        String msg = String.format("Hello World, %s", name);
        return new HelloWorldBean(msg);
    }

//    @GetMapping("/hello-world-bean/path-variable/{address}")
//    public HelloWorldBean helloWorldBean2(@PathVariable(value = "address") String address) {
//        String msg = String.format("My address is %s", address);
//        return new HelloWorldBean(msg);
//    }

    // http://localhost:8080/books/{bookId}/authors/{author}
    @GetMapping(path = "/books/{bookId}/authors/{authorId}")
    public Author getAuthorDetailByBookId(@PathVariable(value = "bookId") String bookId,
                                          @PathVariable(value = "authorId") String authorId) {
        return Author.builder()
                .bookId(bookId)
                .id(authorId)
                .name("Dowon Lee")
                .build();
    }

    @GetMapping(path = "/hello-internationalized")
    public String helloInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
                                         HttpServletRequest request) {
        if (locale == null) {
            locale = localeResolver.resolveLocale(request);
        }

        return messageSource.getMessage("greeting.message", null, locale);
    }
}
