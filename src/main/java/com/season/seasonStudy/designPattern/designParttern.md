# 设计模式的相关的知识

我们使用代理模式解决了上述问题，从静态代理的使用上来看，我们一般是这么做的。

1，代理类一般要持有一个被代理的对象的引用。<br>
2，对于我们不关心的方法，全部委托给被代理的对象处理。<br>
3，自己处理我们关心的方法。<br>


### 单例模式
只需要一个实例存在
eg. menager factory


### 静态代理
静态代理就是用一个新的对象来代替原有的对象，拥有大部分的功能，进行了部分修改，这样可以进行相应的部分功能了。<br>
就像是aop一样，但是aop只是进行了添加功能，但是代理远不止这些<br>
其实吧，代理并没有侵入代码，只是在外面套了一层。<br>
在原有对象的方法前后添加功能<br>
屏蔽原有对象的方法，可以编写新的方法<br>

### 动态代理