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
