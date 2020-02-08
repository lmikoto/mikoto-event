# Mikoto-Event

## 项目介绍
mikoto-event是一个可以在spring-boot项目中创建和消费事件的工具类。适用于对事件的创建和消费进行解耦的小型项目。

## 安装
通过maven引入
```xml
<dependency>
    <groupId>io.github.lmikoto</groupId>
    <artifactId>mikoto-event</artifactId>
    <version>1.0.0.RELEASE</version>
</dependency>
```
## 使用

### 启用
在启动类上加上`@EnableEventBus`注解
### 创建事件继承`BaseEvent`
```java
@Data
@AllArgsConstructor
public class UserRegEvent implements BaseEvent {

    private String userName;

}
```
### 创建事件消费
```java
@Component
@Slf4j
public class UserRegEventListener  extends EventAdapter<UserRegEvent> {

    public boolean process(UserRegEvent event) {
        log.info("user register username is {}",event.getUserName());
        return true;
    }
}
```
### 调用接口生成事件
`miko-event`提供了生成同步和异步两种事件的方法
```java
// 异步提交
EventBusUtils.submit(new UserRegEvent(userName));

// 同步执行
EventBusUtils.post(new UserRegEvent(userName));
```