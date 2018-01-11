package com.codedocker.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private  BookFeignClient bookFeignClient;

    private HashMap<Long, User> users;

    public UserController() {
        users = new HashMap<Long, User>();
        int count = 100;
        while (count > 0) {
            User user = new User();
            user.setId(Long.valueOf(count));
            user.setAge(10 + (int)(Math.random()*10)); //10 - 20
            user.setUsername("user"+ count);
            users.put(Long.valueOf(count), user);
            count--;
        }
    }

    /**
     * 查询user
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        User user = users.get(id);
        if (user == null) {
            return new User();
        }
        return user;
    }

    @GetMapping("/author/{id}")
    public Author getAuthor(@PathVariable Long id) {
        Map<String,Object> map = bookFeignClient.getStatus();
        //User user = findById(id);
        Author author = new Author();
        author.setId(1L);
        author.setUsername("123");
        author.setAge(12);
        //author.setBooks();
        System.out.println(map.get("status"));
        return author;
    }

    /**
     * 本地服务实例的信息
     * @return
     */
    @GetMapping("/info")
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }
}

