# Spring Boot实战：Restful API的构建

实际上Restful本身不是一项什么高深的技术，而只是一种编程风格，或者说是一种设计风格。

## 1.Restful API设计

在传统的http接口设计中，我们一般只使用了get和post两个方法，然后用我们自己定义的词汇来表示不同的操作。

而Restful API的设计则通过HTTP的方法来表示CRUD相关的操作。

因此，除了get和post方法外，还会用到其他的HTTP方法，如PUT、DELETE、HEAD等，通过不同的HTTP方法来表示不同含义的操作。

例如：下面是我设计的一组对用户的增删改查的Restful API
```
接口URL     HTTP方法    接口说明
/user	    GET         查询用户列表
/user       POST        增加用户
/user/{id}  GET         查询用户
/user/{id}  DELETE      删除用户
/user/{id}  PUT         更新用户信息
```
这里可以看出，URL仅仅是标识资源的路劲，而具体的行为由HTTP方法来指定。

## 2.Restful API实现
现在我们再来看看如何实现上面的接口，其他就不多说，直接看代码：
```
@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = POST, produces = "application/json")
    public int addUser(@RequestBody User user) {
        int cnt = userService.insert(user);
        return cnt;
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = "application/json")
    public int deleteUser(@PathVariable Integer id) {
        int cnt = userService.delete(id);
        return cnt;
    }

    @RequestMapping(value = "/{id}", method = PUT, produces = "application/json")
    public int updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        int cnt = userService.update(user);
        return cnt;
    }

    @RequestMapping(value = "/{id}", method = GET, produces = "application/json")
    public User getUserById(@PathVariable Integer id) {
        User user = userService.searchById(id);
        return user;
    }

    @RequestMapping(value = "", method = GET, produces = "application/json")
    public List<User> getUsers() {
        List<User> users = userService.search();
        return users;
    }
}
```
我们再来分析一下这段代码：

　　（1）我们使用的是@RestController这个注解，而不是@Controller，不过这个注解同样不是Spring boot提供的，而是Spring MVC4中的提供的注解，表示一个支持Restful的控制器。

　　（2）这个类中有三个URL映射是相同的，即都是/user/{id}，这在@Controller标识的类中是不允许出现的。这里的可以通过method来进行区分，produces的作用是表示返回结果的类型是JSON。

　　（3）@PathVariable这个注解，也是Spring MVC提供的，其作用是表示该变量的值是从访问路径中获取。

　　所以看来看去，这个代码还是跟Spring boot没太多的关系，Spring boot也仅仅是提供自动配置的功能，这也是Spring boot用起来很舒服的一个很重要的原因，因为它的侵入性非常非常小，你基本感觉不到它的存在。

## 3.测试
　　代码写完了，怎么测试？除了GET的方法外，都不能直接通过浏览器来访问，当然，我们可以直接通过postman来发送各种http请求。

不过我还是比较支持通过单元测试类来测试各个方法。这里我们就通过Junit来测试各个方法：

    释：参考UserControllerTest
    　　因为要执行HTTP请求，所以这里使用了MockMvc，ArticleRestController通过注入的方式实例化，不能直接new，
    否则ArticleRestController就不能通过Spring IoC容器来管理，因而其依赖的其他类也无法正常注入。
    通过MockMvc我们就可以轻松的实现HTTP的DELETE/PUT/POST等方法了。

## 4.总结

　　本文讲解了如果通过Spring boot来实现Restful的API，其实大部分东西都是Spring和Spring MVC提供的，Spring boot只是提供自动配置的功能。

但是，正是这种自动配置，为我们减少了很多的开发和维护工作，使我们能更加简单、高效的实现一个web工程，从而让我们能够更加专注于业务本身的开发，而不需要去关心框架的东西。

##### 参考文献
###### [Spring Boot实战：Restful API的构建](https://www.cnblogs.com/paddix/p/8215245.html)