#####  使用SpringBoot + WebSocket 完成了服务推的技点以及IM即使通讯
> 先说下主要实现的功能(UI界面可能不是很友好,但是页面布局还是合理的,这个验证了一句话:麻雀虽小，但五脏俱全.):
###### 用户可以和自己的好友进行聊天
这里有个小Bug需要注意下，就是用户进行关闭的时候有可能会出现Error异常，但是项目里面没有处理这个异常,很尴尬 - -. 这里显示附上自己的截图.
首次进入聊天页面的时候，俩天窗口是不会进行显示的，如果要显示聊天窗口，那么我们就要点击我的好友列表里面的名字进行聊天窗口的显示.
张三和李四进行聊天的简单示意图
![image-001](http://javaweb.cn-bj.ufileos.com/%E5%BC%A0%E4%B8%89.png?UCloudPublicKey=dYXxi9C4oL_ydCXr-WpM15hsbq6yZayb_mmvRqva&Signature=eIJW%2F%2FvOqB75v1%2BxLUk9gjsz14Y%3D&Expires=1595604951)

