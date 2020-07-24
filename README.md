#####  使用SpringBoot + WebSocket 完成了服务推的技点以及IM即使通讯
> 先说下主要实现的功能(UI界面可能不是很友好,但是页面布局还是合理的,这个验证了一句话:麻雀虽小，但五脏俱全.):
###### 用户可以和自己的好友进行聊天
这里有个小Bug需要注意下，就是用户进行关闭的时候有可能会出现Error异常，但是项目里面没有处理这个异常,很尴尬 - -. 这里显示附上自己的截图.
首次进入聊天页面的时候，俩天窗口是不会进行显示的，如果要显示聊天窗口，那么我们就要点击我的好友列表里面的名字进行聊天窗口的显示.
张三和李四进行聊天的简单示意图
![image-001](http://javaweb.cn-bj.ufileos.com/%E5%BC%A0%E4%B8%89.png?UCloudPublicKey=dYXxi9C4oL_ydCXr-WpM15hsbq6yZayb_mmvRqva&Signature=eIJW%2F%2FvOqB75v1%2BxLUk9gjsz14Y%3D&Expires=1595604951)

李四:
![image-002](http://javaweb.cn-bj.ufileos.com/%E6%9D%8E%E5%9B%9B.png?UCloudPublicKey=dYXxi9C4oL_ydCXr-WpM15hsbq6yZayb_mmvRqva&Signature=58fI4QUGEKpPSDv%2BTw43LKSE2PM%3D&Expires=1595605120)

双方可以互相给自己发送消息！ 并且giant消息还可以逻辑上的进行保存，该逻辑上的保存就是：加入说张三给李四发送消息，李四没有打开聊天窗口的页面，张三发送完了过了1个小时之后，李四再去打开这个聊天窗口的页面就会发现张三发送给自己的消息仍然存在.

###### 服务推技术点实现
> 实现原理： 我是新建了一个管理员的页面并且重新创建了一个WebSocket对象来进行服务推, 而在该WebSocket所连接的后台WebSocket接口中是调用了用户WebSocket接口中维护的一个数据结构onLineUsers的一个Map数据结构. 该结构里面存储的都是当前在线的用户. 并且以该用户的名字为Key. 这里只是限于实验性质的用用户名的名字作为Key，在开发环境中可以使用类似于UUID、数据库主键、redis自增能全局唯一表示一个用户的Key来作为Key.
服务推的截图：
> 管理员在管理员界面进行发布全屏广播，然后各个在线用户在页面下方显示该广播.

管理员连接后台成功
![image-003](http://javaweb.cn-bj.ufileos.com/admin.png?UCloudPublicKey=dYXxi9C4oL_ydCXr-WpM15hsbq6yZayb_mmvRqva&Signature=WoMpBsjMfz0NpPnkSnki9D%2FyYSU%3D&Expires=1595605759)
管理员发送消息
![image-0004](http://javaweb.cn-bj.ufileos.com/adminSendServerMessage.png?UCloudPublicKey=dYXxi9C4oL_ydCXr-WpM15hsbq6yZayb_mmvRqva&Signature=AeDFOStM2fiMfvMmYqfYyqGY364%3D&Expires=1595605818)
用户接受到来自管理员的全频道广播
![image-004](http://javaweb.cn-bj.ufileos.com/serverput.png?UCloudPublicKey=dYXxi9C4oL_ydCXr-WpM15hsbq6yZayb_mmvRqva&Signature=e997qKmRBAMU6vJpvA2h7WDENx0%3D&Expires=1595605856)

##### 以上就是这个小Demo的全部功能了，没有说明一些一眼就能看出来的基本功能. 
后续会不继续完善该Demo的，以及增加点对面群聊功能以及发送文件和图片等.! 
