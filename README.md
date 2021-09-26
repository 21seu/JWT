# JWT
## ![img](https://cdn.nlark.com/yuque/0/2021/png/12759906/1632579520287-5f4d031a-ff5d-4db7-b41c-e2a7aa021e49.png)

## <font color='green'>1. 什么是JWT</font>

> JSON Web Token (JWT) is an open standard ([RFC 7519](https://tools.ietf.org/html/rfc7519)) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the **HMAC** algorithm) or a public/private key pair using **RSA** or **ECDSA**.
>
> JSON Web令牌（JWT）是一种开放标准（RFC 7519），它定义了一种紧凑且自包含的方式，用于在各方之间作为JSON对象安全地传输信息。此信息可以验证和信任，因为它是经过数字签名的。JWT可以使用秘密（使用HMAC算法）或使用RSA或ECDSA的公钥/私钥对进行签名
>
> JWT简称JSON Web Token，也就是通过JSON形式作为Web应用中的令牌，用于在各方之间安全地将信息作为JSON对象传输。在数据传输过程中还可以完成数据加密、签名等相关操作



## <font color='green'>2.JWT能做什么</font>

```markdown
# 1、授权
这是JWT最常见的使用方案。一旦用户登录，每个后续请求将包括JWT，从而允许用户访问该令牌允许的路由、服务和资源。单点登录是当今广泛
使用JWT的一项功能，因为它的开销很小并且可以在不同的域种轻松使用。

# 2、信息交换
JSON Web Token是在各方之间安全地传输信息的好方法。因为可以对JWT进行签名（例如使用公钥/私钥对），所以您可以确保发件人是他们所说
的人。此外，由于签名时使用标头和有效负载计算的，因此您还可以验证内容是否遭到篡改。
```

## <font color='green'>3. 为什么是JWT</font>

#### <font color='green'>基于传统的Session认证</font>

```markdown
# 1、认证方式
我们知道，http协议本身是一种无状态的协议，而这就意味着如果用户向我们的应用提供了用户名和密码来进行用户认证，那么下一次请求时，用户
还要再一次进行用户认证才行，因为根据http协议，我们并不能知道是哪个用户发出的请求，所以为了让我们的应用能够识别是哪个用户发出的请求
我们只能在服务器储存一份用户登录的信息，这份登录信息会在响应时传递给浏览器，告诉其保存为cookie，以便下次请求时发送给我们的应用，这
样我们的应用就能识别求情来此哪个用户了，这就是传统的session认证。

# 2、认证流程
```

![img](https://cdn.nlark.com/yuque/0/2021/jpeg/12759906/1632581132630-21c6adbb-b750-4c39-ba3c-2ec1544b0ca1.jpeg)

```markdown
# 3、暴露问题
1.每个用户经过我们的应用认证之后，我们的应用都要在服务端做一次记录，以方便用户下次请求的鉴别，通常而言session都是保存在内存中，而
随着认证用户的增多，服务端的开销会明显增大

2.用户认证之后没服务端做认证记录，如果认证的记录被保存在内存中的话，这意味着用户下次请求还必须要请求在这台服务器上，这样才能拿到授
权的资源，这样在分布式的应用上，相应的限制了负载均衡的能力。这也意味着限制了应用的扩展能力。

3、因为时基于cookie来识进行用户识别的，cookie如果被截获，用户就会很容易受到跨站请求伪造的攻击

4、在前后端分离的系统就更痛苦了：
也就是说前后端分离在应用解耦后，增加了部署的复杂性。通常用户一次请求就要转发多次。如果用session每次写到的sessionid到服务器，服务
器还要查询用户信息。同时如果用户很多。这些信息存储在服务器内存中，给服务器增加负担，还有就是CSRF（跨站伪造请求攻击）攻击，session
是基于cookie进行用户识别的，cookie如果被截获，用户就会很容易受到跨站请求伪造的攻击。还有就是sessionid是一个特征值，表达的信息不够
丰富。不容易扩展，而且如果你后端应用是多节点部署，那么就需要实现session共享机制，不方便集群应用。
```

![img](https://cdn.nlark.com/yuque/0/2021/jpeg/12759906/1632582820818-8a5f5f98-5fc0-4164-9000-25f60b13dbb5.jpeg)

#### 基于JWT认证

![img](https://cdn.nlark.com/yuque/0/2021/png/12759906/1632583037103-2903ac0b-dff6-4fb6-9ffe-b1445cf68b08.png)

```markdown
# 1.认证流程
首先，前端通过Web表单将自己的用户名和密码发送到后端的接口。这一过程一般是一个HTTP POST请求。
建议的方式是通过SSL加密的传输（https协议），从而避免敏感信息被嗅探。

后端核对用户名和密码成功后，将用户的id等其他信息作为JWT Payload（负载），
将其与头部分别进行Base64编码拼接后签名，形成一个JWT（Token）。形成的JWT就是一个形同lll.zzz.xxx的字符串。

后端将JWT字符串作为登录成功的返回结果返回给前端。前端可以将返回的结果保存在localStorage或sessionStorage上，
退出登录时前端删除保存的JWT即可。

前端在每次请求时将JWT放入HTTP Header中的Authorization位。（解决XSS和XSRF问题）。

后端检查是否存在，如存在验证JWT的有效性。例如：检查签名是否正确；检查Token是否过期；检查Token的接收方是否是自己（可选）。

验证通过后后端使用JWT中包含的用户信息进行其他逻辑操作，返回相应结果。

# 2.jwt优势
- 简洁（Compact）： 可以通过URL，POST参数或者在HTTP header发送，因为数据量小，传输速度也很快。
- 自包含（Self-contained）：负载中包含了所有用户所需要的信息，避免了多次查询数据库。
- 因为Token是以JSON加密的形式保存在客户端的，所以JWT是跨语言的，原则上任何违背形式都支持。
- 不需要在服务端保存会话信息，特别适用于分布式微服务。
```

## 4. JWT的结构是什么？

```markdown
# 1、令牌组成
- 1，标头（Header）
- 2，有效载荷（Payload）
- 3，签名（Signature）
- 因此，JWT通常如下所示：xxxxx.yyyyy.zzzzz Header.Payload.Signature

# 2、Header
- 标头通常由两部分组成：令牌的类型（即JWT）和所使用的签名算法，例如：HMAC SHA256和RSA。它会使用Base64编码组成JWT结构的第一部分。
- 注意：Base64是一种编码，也就是说，它是可以被翻译回原来的样子来的。它并不是一种加密过程。
{
	"alg"：“HS256”，
	“typ”：“JWT”
}

# 3、Payload
- 令牌的第二部分是有效负载，其中包含声明。声明是有关实体（通常是用户）和其他数据的声明。同样的，它会使用Base64编码组成JWT结构的第二部分
{
	"sub"：“1234567890”，
	“name”：“John Doe”，
	“admin”：true
}

# 4、Signature
- 前面两部分都是使用Base64进行编码的，即前端可以解开知道里面的信息。Signature需要使用编码后的header和payload以及我们提供的一个密钥，然后使用header中指定的签名算法（HS256）进行签名。签名的作用是保证JWT没有被篡改过
- 如：
		HMACSHA256（base64UrlEncode（header）+"."+base64UrlEncode(payload)，secret）；
# 签名目的
最后一步签名的过程，实际上是对头部以及负载内容进行签名，防止内容被篡改。如果有人对头部以及负载的内容解码之后进行修改，再进行编码，
最后加上之前的签名组合形成新的JWT的话，那么服务器端会判断出新的头部和负载形式的签名和JWT附带上的签名是不一样的。如果要对新的头部
和负载进行签名，在不知道服务器加密时用的密钥的话，得出来的签名也是不一样的。

# 信息安全问题
在这里大家一定会问一个问题：Base64是一种编码，是可逆的，那么我的信息不就被暴露了吗？
是的。所以，在JWT中，不应该在负载里面加入任何敏感的数据。在上面的例子中，我们传输的是用户的User ID。
这个值实际上不是什么敏感内容，一般情况下被知道也是安全的。但是像密码这样的内容就不能被放在JWT中了。
如果将用户的密码放在JWT中，那么怀有恶意的第三方通过Base64解码就能很快地知道你的密码了。
因此JWT适合用于向Web应用传递一些非敏感信息。JWT还经常用于设计用户认证和授权系统，甚至实现Web应用的单点登录。
```

![img](https://cdn.nlark.com/yuque/0/2021/png/12759906/1632666925462-21a615cf-874e-4cde-b01d-4f56256dc47f.png)

```markdown
# 5、放在一起
输出是三个由点分隔的Base64-URL字符串，可以在HTML和HTTP环境中轻松传递这些字符串，与基于XML的标准（例如SAML）相比，它更紧凑。
简洁（Compact）
可以通过URL，POST参数或者在HTTP header发送，因为数据量小，传输速度快
自包含（Self-contained）
负载中包含了所有用户所需要的信息，避免了多次查询数据库
```

![img](https://cdn.nlark.com/yuque/0/2021/png/12759906/1632667127036-92b57f39-4470-45e1-8c3d-6295e099ea13.png)



## 5. 使用JWT

### 创建项目，导入依赖

```xml
<dependency>
   <groupId>com.auth0</groupId>
   <artifactId>java-jwt</artifactId>
   <version>3.4.0</version>
</dependency>
```

### 生成token

```java
@Test
    public void contextLoads() {
        Map<String, Object> map = new HashMap<String, Object>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 20);
        String token = JWT.create()
                .withHeader(map) //head 这里header可以不写 不写默认也是它
                .withClaim("userId", 21) //payload
                .withExpiresAt(instance.getTime()) //指定令牌的过期时间
                .withClaim("username", "zhangsan")
                .sign(Algorithm.HMAC256("!Q2W#E$RW"));//签名

        /**
         * eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.
         * eyJleHAiOjE2MzI2NjgwMzMsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.
         * eeS4z6JxnLfH337te59gKM7kC4Qw92E0Xp5vQL6aTcA
         */
        System.out.println(token);
    }
```

### 根据令牌解析数据

```java
@Test
    public void test() {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!Q2W#E$RW")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzI2NjgwMzMsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.eeS4z6JxnLfH337te59gKM7kC4Qw92E0Xp5vQL6aTcA");
        System.out.println(verify.getClaim("userId").asInt());
        System.out.println(verify.getClaim("username").asString());
        System.out.println(verify.getExpiresAt());
    }
```

### 常见异常信息

- SignatureVerificationException：签名不一致异常
- TokenExpiredException：令牌过期异常

- AlgorithmMissmatchException：算法不匹配异常
- InvalidClaimException：失效的payload异常
