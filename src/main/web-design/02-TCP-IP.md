# TCP/IP协议
TCP/IP不是简单的指TCP和IP两种协议。它包含了ICMP
、TCP和UDP、Telnet和FTP、以及HTTP等。
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/TCP-IP01.jpg)

TCP/IP在应用层有TFTP、HTTP、SNMP、FTP、SMTP、DNS、Telent
传输层有TCP、UDP
网络层有IP、ICMP、RIP、OSPF、BGP、IGMP

## TCP/IP基础
#### 1.TCP/IP的具体含义
TCP/IP一般泛指刚才讲到的这些协议，因此它也被称为TCP/IP为网际协议群。

互联网进行通信时,需要相应的网络协议，TCP/IP原本就是为了使用互联网开发而定制的协议族。
因此互联网的协议就是TCP/IP，
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/TCP-IP02.jpg)

#### 2.数据包
**包、帧、数据包、段、消息**
以上五个术语都用来表述数据的单位，大致区分为：
- 包可以说是全能型术语
- 帧用于表示数据链路层中包的单位
- 数据包是IP和UDP等网路层以上的分层中包的单位
- 段则是表示TCP数据流中的信息
- 消息是指应用协议中数据的单位

每个分层中，都会对所发送的数据附加一个首部，在这个首部包含了该层必要的信息，如发送的目标
地址以及协议的相关信息。通常，为协议提供的信息为包首部，所要发送的内容为数据。在下一层的角度看，从上一层收到的包全部认为为该层的数据
例子
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/TCP-IP03.jpg)

网络中传输的数据包由两个部分组成，一部分为协议索要用到的首部，另一部分是上一层传过来的数据。
首部的结构由协议的具体规范详细定义。在数据包的首部，明确标明了协议应该如何读取数据。

反过来说，看到首部，也能够了解该协议必要的信息以及索要处理的数据包。`包首部就像协议的脸`

#### 3.数据处理流程
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/TCP-IP04.jpg)
- 步骤一:应用程序处理    
首先应用程序会进行编码处理，这些编码相当于OSI的表示层功能;     
编码转化后，邮件不一定马上被发送出去，这种合适建立通信连接何时发送数据的管理功能,相当于OSI的会话层功能。

- 步骤二:TCP模块的处理      
TCP根据应用的指示，负责建立连接、发送数据以及断开连接。TCP提供将应用层发来的数据顺利发送至对端的可靠传输。
为了实现这一功能，需要在应用层数据的前端附加一个TCP首部。

- 步骤三:IP模块的处理   
IP将TCP传过来的TCP首部和TCP数据结合起来作为自己的数据，并在TCP首部的前端加上自己的IP首部。IP包生成后，参考路由控制表决定
接受此IP包的路由或主机。

- 步骤四:网络接口(以太网驱动)的处理        
从IP传过来的IP包对于以太网来说就是数据。给这些数据附加上以太网首部并进行发送处理，生成以太网数据包将通过物理层传输给接收端。

- 步骤五:网络接口(以太网驱动)的处理--接收            
主机收到以太网包后,首先从以太网包首部找到MAC地址判断是否为发送自己的包，若不是则丢弃数据。     
如果是发送给自己的包，则从以太网首部中的类型确实数据类型，在传给相应地模块，如IP、ARP。这里是IP

- 步骤六:IP模块的处理
IP模块接收到数据后也做了类似的处理。从包首部判断此IP地址是否为自己的IP地址匹配，如果匹配
则根据首部的协议类型将数据发送给对应的模块。如TCP、UDP。这里是TCP       
另外由于路由器的存在，接收端往往不是自己的地址，此时，需要借助路由控制表，在调查应该送往的主机或
路由器之后再进行转发数据。

- 步骤七:TCP模块的处理
在TCP模块处理中，首先会计算一下校验和判断数据是否被破坏、然后检查是否在按照序号接收数据。

- 步骤八:应用程序的处理
接收端应用程序会直接接收发送端发送的数据。通过解析数据，展示相应地内容。

## 传输层中的TCP和UDP
TCP/IP中有两个具有代表性的传输协议,分别是TCP和UDP

- TCP是面向连接的、可靠的流协议。流就是指不间断的数据结构，当应用程序采用TCP发送消息时，虽然可以保证发送的顺序，但
还是犹如没有任何间隔的数据流发送给接收端。       
TCP为提供可靠传输，实行”顺序控制“或”重发控制“的机制。此外还具备”流控制（流量控制）“
、拥塞控制、提高网络利用率等众多功能
- UDP是不具可靠性的数据报协议。细微的处理它会交给上层的应用去完成。在UDP情况下，虽然可以确保发送信息的大小，却不能保证消息一定会到达。
因此，应用优势会根据自己的需要进行重发控制。
- TCP和UDP的优缺点无法简单去作比较：TCP用于传输层有必要实现可靠传输的情况；而在一方面，UDP主要用于哪些高速传输和实时性有较高要求的通信和
广播通信。TCP和UDP应该根据自己的目的按需使用。

### 1.端口号   
数据链路和IP中的地址，分别指的是MAC地址和IP地址。    
前者用来识别同一链路中不同的计算机，后者用来识别TCP/IP网络中互联的主机和路由器。在传输层也有类似的
概念。--->端口号

#### 1.1根据端口号识别应用
一台计算机上同事可以运行多个程序。传输层协议正是利用这些端口号识别正在通信的应用程序，并准确讲数据传输。
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/TCP-UDP-01.jpg)

#### 1.2通过IP地址、端口号、协议号进行通信识别
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/TCP-UDP-02.jpg)

![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/TCP-UDP-03.jpg)

①和②的通信是在两台计算机上进行的。他们的目标端口号相同都是80.这里可以根据源端口号进行区分。

③和①的目标端口号和源端口号完全相同，但他们各自的源IP地址不同。

此外如果IP地址和端口号完全一样，我们还可以通过协议号来区别（TCP和UDP）

#### 1.3端口号的确定
- 标准既定的端口号:这种方法也叫静态方法。它是指每个应用程序都有特定的端口号、但并不是说能够随意
使用任何一个端口号、录入HTTP、FTP、TElent等广为使用的应用协议中所使用的端口号是固定的。这些端口号被称为
知名端口号，分布在0-1023之间。除了这些，还有一些端口号被正式注册，它们分布在1024-49151中，不过这些端口号可用于任何通信用途。

- 时序分配法：服务器有必要确定监听的端口号，但是接受服务的客户端没必要给确定的端口号。在这种方法下，客户端应用程序完全可以不
使用自己设置的端口号，而全权交给操作系统分配。动态分配的端口号范围在49152-65535之间。

#### 1.4端口号与协议
- 端口号由使用的传输层协议决定。因此，不同的传输层协议可以使用相同的端口号。
- 此外哪些知名端口号与传输层协议并不关系。只要端口一致分配同一种应用程序进行处理。

### 2.UDP
- UDP不提供复杂的控制机制，利用IP提供面向无连接的通信服务。
- 并且它是将应用程序发出来的数据在收到的那一刻，
立即按照原样发送到网络上的一种机制。
- 此外传输途中出现丢包UDP也不负责重发。
- 甚至当包的到达顺序出现乱序也没有纠正的功能
- 如果需要以上的细节控制，不得不交由采取UDP的应用程序去处理。
- UDP一般用于以下几个方法:1.包总量较少的通信(DNS、SNMP等)；2视频、音频等多媒体通信(及时通信)
3.限定于LAN等特定网络中的应用通信；4.广播通信(广播、多播)。

### 3.TCP
- TCP和UDP的区别相当大。它充分地实现了数据传输时各种控制功能，可以进行丢包时的重发控制，还可以对次序乱掉的分包进行顺序控制。
而这些在UDP中毒没有。
- 此外TCP作为一种面向有连接的协议，只有在确认通信对端存在时才会发送数据，从而可以控制
通信流量的浪费。
- 根据TCP的这些机制，在IP这种无连接的网络上也能够实现高可靠性的通信(主要通过检验和、序列号、确认应答
、重发控制、连接管理以及窗口控制等机制实现)。

#### 3.1三次握手(重点)
- TCP提供面向有连接的通信传输。面向有连接是指在数据通信开始之前先做好两端之间的准备工作

- 所谓的三次握手是指建立在一个TCP连接时需要客户端和服务端总共发送三个包以确认连接的建立。在socket编程
中，这一过程由客户端执行connect来触发。
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/TCP-UDP-04.jpg)

- 第一次握手:客户端将标志位SYN置为1，随机产生一个值seq=J,并将该数据包发送给服务器端，客户端进行SYN_SNET状态，等待服务器端确认。
- 第二次握手:服务器端收到数据包后由标志位SYN=1指定客户端请求建立连接，服务器端将SYN和ACK都置为1，ack=J+1,随机产生一个seq=K,并将
数据包发送给客户端以确定接收请求，服务器端进行SYN_RCVD状态。
- 第三次握手:客户端收到确定后,检查ack是否为J+1，ACK是否为1，如果正确
则将标志位ACK置为1，ack=K+1,并将该数据包发送给服务器端，服务器端检查ack是否为K+1，
ACK是否为1，如果正确则连接建立成功。客户端和服务器端进行ESTABLISHED状态，完成三次握手后之间可以开始传输数据了。


#### 3.2四次挥手(重点)
- 四次挥手即终止TCP连接，就是指断开一个TCP连接时，需要客户端和服务端总共发送4个包以确认连接的端口。在socket编程
中，这一过程由客户端或服务端任一方执行close来触发。

- 由于TCP连接时全双工的，因此，每个方法都必须要单独进行关闭，这一原则是当一方完成数据发送任务后，
发送一个FIN来终止这一方向的连接。      
收到一个FIN只是一味这这一方向上没有数据流动了，即不会再接收数据了，但是在这个TCP连接上仍然能够发送数据，
直到这一方向也发送了FIN。首先进行关闭的一方将执行主动关闭，而另一方则执行被动关闭。
![](https://raw.githubusercontent.com/SilentFlower/Program_Learning_Base/master/src/image/TCP-UDP-05.jpg)

- 中断连接端可以是客户端也可以是服务器端
- 第一次挥手:客户端发送一个FIN=M，用来关闭客户端到服务器端的数据传送，客户端进行FIN_WAIT_1状态。意思就是说我客户端没有数据要发送给你了，但是如果
你服务器还有数据没有发送完成，则不必急着关闭连接，可以继续发送数据。

- 第二次挥手:服务器端接收到FIN后，先发送ack=M+1,告诉客户端，你的请求我收到了，但是我还没准备好，请继续你等我的消息。这个时候客户端进入FIN_WAIT_2状态，继续等待服务器端的
FIN报文。

- 第三次挥手:当服务器端确定数据以及发送完成后，则向客户端发送FIN=N报文，告诉客户端好了，我在这边数据发送
完毕，准备好关闭连接了。服务器进行LAST_ACK状态。

- 第四次挥手:客户端受到了FIN=N的报文后，就知道可以关闭了,但是他还是不相信网络，怕服务器不知道要关闭，
所以发送了ack=N+1后进入TIME_WAIT状态，如果Server端没有收到ACK则可以重传。
服务器端收到ACK后就知道可以断开连接了。客户端等到了2MSL后依然没有收到回复，则证明服务器端以及关闭，客户端连接也可以关闭。完成四次挥手。

#### 3.3字段解释
- 序列号seq:占4个字节，用来标记数据段的顺序，TCP吧连接中发送的所有数据节都编上一个序号，
第一个字节的编号由本地随机产生；给字节编上序号后，就给每一个报文指派一个序号。

- 确认号ack；占4个字节，期待收到对象下一个报文段的第一个数据字节的序号；序列号表示报文段的携带数据的第一个字节的编号；
而确认号指的是期望接收到下一个字节的编号。

- 确认ACK:占1位，即ACK=1时，确认好字段才有效，ACK=0确认无效。

- 同步SYN:连接建立时用于同步序号。当SYN=1，ACK=0时表示:这是一个连接请求报文段。
若连接同意SYN=1,ACK=1。因此,SYN=1表示这是一个连接请求，在建立连接时才会置为1

- 终止FIN：用来释放一个连接。FIN=1时表示数据发送完毕，要求释放运输连接。

#### 3.4通过序列号来确认应答提高可靠性
- 在TCP中，当发送端的数据到达接收主机时，接收端主机会返回一个已收到消息的通知。
这个消息左脚确认应答ACK。当发送端将数据发出之后会等待对端的确认应答。如果有确认应答，说明数据
已经成功到达了对端。反之，数据丢失的可能性很大。
- 在一定时间没没有等待到确认应答，发送端就可以认为数据已经丢失，并进行重发。由此即使产生了丢包，仍然
保证数据能够达到对端，实现可靠传输。
- 未收到确认应答并不意味着数据一定丢失。也有可能数据对方已经收到，只是返回的确认应答在途中丢失。这种情况也会导致发送端误以为
数据没有到达木地方而重发数据。
- 此外，也有可能因为其他原因导致确认应答延迟到达，在源主机重发数据以后到达的情况也屡见不鲜。
- 对于目标主机来说，反复收到相同的数据时不可取了。为了对上层应用提供可靠的传输，目标主机必须放弃重复的
数据包。为此引入序列号。    
  
`序列号是按照顺序发送数据的每一个字节都标记上了号码的编号。接收端查询接收数据TCP首部中的序列号和数据长度
，将自己下一部应该接收的序列号作为确认应答返回去。通过序列号和确认应答号，TCP能够识别是否接收到数据，又能够判断是否需要接收，从而实现可靠传输`

#### 3.5重发超时的确认
- **重发超时是指在重复数据之前，等待确认应答来的特定时间间隔**。如果超过这个时间仍未收到确认应答，发送端将进行数据重发。最理想的是，
找到一个最小时间，它能保证“确认应答一定能在这个时间内返回”

- TCP要求不论处在何种网络环境下都要提高高性能通信，并且无论网络拥堵情况发生何种变化，都必须保持这一特性。

- 数据重发之后若接不到确认应答，则进行再次发送。此时，等待确认应答时间会被以2倍、4倍增长

- 此外，数据不会被反复重发。达到一次重发次数后如果没有确认应答返回，就会判断网络对端主机发生异常，关闭连接。

#### 3.6以段为单位发送数据
- 在建立TCP连接的同时，也可以确定发送数据包的单位，我们也可以称其为MSS。最理想的情况是，消息长度
正好是IP中不会被分片处理的数据长度
- TCP在传送大量数据时，是以MSS的大小将数据进行分割发送。进行重发时也是以MSS为单位。
- MSS在三次握手的时候，在两端主机之间被计算得出。两端的主机在发出建立连接的请求时，会在TCP首部写入
MSS选项，告诉对方自己的接口能够适应的MSS的大小。然后会在两者之间选择一个较小的值投入使用

#### 3.7利用窗口控制提高速度
再说

##IP
- IP是相当于OSI参考模型中的第三层----网络层