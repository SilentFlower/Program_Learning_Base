# HTTP协议
> [原博文](https://www.cnblogs.com/an-wen/p/11180076.html)
## HTTP协议简介
> HyperText Transfer Protocol   

超文本传输协议是一种应用于分布式、协作式和超媒体信息系统的应用协议。HTTP是万维网的数据通信的基础。

## HTTP协议概述
HTTP是一个客户端终端`用户`和服务器端`网站`进行请求和应答的标准`TCP`。
通过网页浏览器、网络爬虫或者其他工具，客户端发送HTTP请求到服务器指定的端口`默认80`。
我们称这个客户端为用户代理程序`user agent`。应答的服务器存储资源(例如HTML和视频图像)。
我们称这个应答服务器为源服务器`origin server`。
在用户代理和源服务器中间存在许多中间层,比如代理服务器、网关或者隧道。

尽管TCP/IP协议是互联网最流行的应用，HTTP协议中，并没有规定必须使用它。
HTTP可以在任何互联网协议上，或者其他网络上实现。HTTP假定其下层协议提供可靠
的传输。任何能够提供这种保证的协议都可以被它使用。因此HTTP使用TCP作为传输层。

## HTTP工作原理
HTTP协议定义Web客户端如何从Web服务器请求Web页面，以及服务器如何把Web页面
传回给客户端。HTTP协议采用请求/响应模型。

客户端向服务器传送一个请求报文。请求报文包含请求的`方法` `URL`
`协议版本` `请求头部` `请求数据`。服务器以一个状态行文响应。响应包括 `协议版本` `成功或者错误的代码`
`服务器的信息` `响应头部和响应数据`

>以下为HTTP请求的基本步骤：

1.客户端连接到Web服务器      
一个HTTP客户端通常是浏览器，与Web服务器的HTTP端口`80`建立一个TCP套接字连接。

2.发送HTTP请求  
通过TCP套接字，客户端向Web服务器发送一个文本的请求报文，一个报文请求由`请求行` `请求头` 
`空行` `请求数据`组成

3.服务器接收并返回HTTP响应    
Web解析请求，定位请求资源。服务器将资源副本写到TCP套接字，由客户端读取。一个响应由`状态行` 
`响应头部` `空行` `响应数据`4部分组成

4.释放连接的TCP连接
若connection模式为close，则服务器主机关闭TCP连接，客户端被动关闭连接。释放TCP连接。
若模式为keepalive,则会继续保持一段时间，在该段时间可以继续接收请求

5.客户端浏览器解析HTML内容    
客户端浏览器首先确认解析态行，查看表明请求是否成功的状态代码。
然后解析每一个响应的头，响应头告知如下为若干字节的HTML文档和字符集。客户端浏览器读取响应数据的HTML，根据
HTML的语法进行格式化，并在浏览器显示。

>简化描述             

- 在浏览器的地址栏输入URL(如何为域名)，向DNS服务器请求解析相应地IP地址。
- 浏览器与该地址默认80端口建立TCP连接。
- 浏览器发出读取文件的HTTP请求`URL后面的路径`，该请求报文作为TCP三次握手的**第三个报文**的数据发送给服务器。
- 服务器对浏览器做出响应，返回对应的HTML文本给浏览器
- 浏览器对该HTML进行解析并显示

>图解

![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/http_01.png)
**HTTP协议是基于TCP/IP协议上的应用层协议**
**基于 请求-响应的模式**

![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/http_02.png)
HTTP是一种不保存状态，即无状态`stateless`协议。HTTP协议本身不对请求和响应之间的
通信状态进行保存。也就是HTTP不会对做持久化处理。

![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/http_03.png)

使用HTTP协议每当有新的请求发送时，就会有对应的新响应产生。
协议本身并不保存之前的一切的请求和响应报文的信息。这样是为了快速的处理大量事务，
确保协议的可伸缩性。

但随着Web不断发展，因无状态而导致业务处理变得棘手。
如用户登录到一家购物网站，即使他跳到其他站在返回来时，也能继续
保持登录状态。HTTP/1.1虽然是无协议状态，但是为了实现保持登录的功能，于是引入了Cookie技术。
使用Cookie再用HTTP协议就可以实现管理状态。

无连接，无连接的含义是限制每次连接只处理一个请求。服务器处理完客户的请求，并受到客户的应答后就会断开连接。
采用此方式可以节省传输时间，并且可以提高并发性能。HTTP/1.1并不是立即断开而是等几秒，等用户是否有后续的操作。

## HTTP的请求方法
一共八种方法
>GET
>
向指定的资源发出"显示请求"。使用GET用于读取数据，而不应当用于产生"副作用"的操作中。

>HEAD
>
与GET方法一样。只不过服务器将不传回资源的本文部分。
使用该方法可以在不必传输全部内容的情况下，就可以获取其中"关于"该资源的信息。

>POST
>
向指定资源提供数据，请求服务器进行处理(例如表单的提交或者文件的上传)。数据被包含在请求本文中。这个请求可能会创建
新的资源或者修改现有资源

>PUT
>
向指定资源位置上传其最新的内容

>DELETE
>
请求服务器删除Request-URI所标识的资源

>TRACE
>
回显服务器的请求，主要用于诊断或测试

>OPTIONS
>
这个方法使服务器传回该资源所支持的所有HTTP请求方法。同样可以用于测试。

>CONNECT
>
HTTP/101协议中预留给连接改成管道方式的代理服务器。通常用于SSL加密服务器的连接。

#### POST和GET区别
- GET提交的数据放在URL之后，可以看到。POST方法把提交的数据放在HTTP包的请求中。
- GET提交的数据大小有限制（浏览器对URL长度有限制），而POST方法提交无

## HTTP状态码  
所有HTTP响应的第一行都是状态行，依次是HTTP版本号，3位数字组成的状态码。

状态码类型
- 1xx消息---请求已被服务器接收，继续处理
- 2xx成功----请求已经成功被服务器接收、理解和接收
- 3xx重定向----需要后续操作才能完成此步请求
- 4xx请求错误----请求含有词法错误或者无法执行
- 5xx服务器错误---服务器在处理摸个正确请求的时候发生错误

一般开发者`200 OK` `404 Not Found`来描述

## URL
超文本传输协议HTTP的统一资源定位符将从因特网获取信息的五个基本元素包括在一个简单的地址中：
- 传输协议
- 层级URL标志符号`//`
- 访问资源需要的凭证信息(可省略)
- 服务器通常为域名
- 端口号HTTP默认80可省
- 路径(以/字符区别路径中的每一个目录名称)
- 查询 GET模式以?为起点&隔开，以=为分开参数名称和数据，通常UTF-8编码避免冲突
- 片段 以#为起点

举例http://www.bilibili.com/video/bv17x411w7KC/?spm_id_from=333.788.b_636f6d6d656e74.14

## HTTP请求格式
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/http_04.jfif)

具体情况
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/http_05.png)
Context-Length的长度为请求数据里面的长度
请求头中有一组键值对user-agent通常用来爬虫模仿浏览器进行活动所需要的。

## HTTP响应格式
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/http_06.jfif)

具体情况
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/http_07.png)
