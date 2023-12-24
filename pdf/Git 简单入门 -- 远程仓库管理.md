$Git$ 简单入门 -- 远程仓库管理



我们以github为例，介绍本地仓库如何与github上仓库联动。

github官网: https://github.com/

值得注意的是，国内对GitHub限速，除魔法上网之外的方法有时候会出现短暂掉线，连接不上GitHub的情况，这是正常的，多刷新几次，或者等一会就可以了。

~~(魔法上网大家就各显神通吧，没法说)~~



首先注册一个账户，略。



#### 一、SSH配置

为了让本地仓库和github账号上的仓库之间建立连接，我们需要在本地配置ssh-key。

ssh简要来说是一种常见的安全连接协议。

我们在任意位置右击 -> Open Git Bash here, 输入命令。

```
ssh-keygen -t rsa -C "你的账户邮箱（写全，包括后面@...com）"     

（-t rsa 指的是采用RSA方式加密）
```

如果你了解ssh，请在下面的弹框中自行配置passphrase，否则不输入一路回车。

（我这边已经创建过了，这是截的一个网图）

![](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221195656905.png)

然后我们按照红线所示路径找到这个文件（ **记得打开显示隐藏文件** , agle是系统本地用户名）

用记事本打开文件，复制里面全部内容

![image-20231221194550105](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221194550105.png)

打开GitHub，点击头像，Settings -> SSH and GPG keys

点击右上角 New SSH key，然后进入这个界面。

![](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221201246910.png)

Titile随便写，Key框里粘贴复制内容。

完成后点击Add SSH key，然后输入密码验证一下。完成之后结果如下，然后你写的邮箱里应该会收到ssh-key有关的邮件。

![image-20231221201038074](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221201038074.png)

然后再次打开git bash

输入 ssh -T git@github.com

随后系统会询问我们是否要继续连接，输入yes然后回车，然后github的信息就被写进hosts文件了，这意味着我们和GitHub账号绑定了。

![image-20231221202545372](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221202545372.png)



#### 二、Github和本地仓库传输命令

##### 1.将本地仓库上传

我们需要首先自己在GitHub上创建一个Repository（后面用Repo代替，这是大家默认的简写）

头像 -> Your repositories -> 右上角 New

![image-20231221224316390](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221224316390.png)

库名和描述好说。

下面选择库的属性，公开或私有，字面意思。

**（以下选项可选可不选，如果选择GitHub会对应自动创建相应文件，但是为了在上传时不引起冲突，初学者最好都不要选）**

README file是关于你的项目的介绍，大项目一般都有，是markdown文件，可以勾选也可以不勾选，勾选后GitHub会为你创建Readme.md。

.gitignore文件指的是在git工具处理过程中**被忽略**的文件，这里包含一些模板，我们选择Java模板就可以了，它会帮我们过滤掉*.class, *.log, *.jar等等运行文件或Java配置文件。

license是使用许可证，我们使用GNU General Public License v3.0就足够了，其他的详情自行百度。



接下来我们需要让这个仓库和本地仓库建立联系。

首先复制Repo的SSH地址。

![image-20231221224828695](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221224828695.png)

在你本地写好的仓库位置打开Git Bash

输入以下命令

```
git remote add origin 复制的内容（右键Paste）

其中 git remote 命令是控制远程仓库的命令。

origin是默认远程仓库别名。就像一个三孩家庭孩子除了自己的名字还叫老大老二老三一样。

复制的内容是仓库的ssh

add 命令在当前本地库的位置加入了绑定的新远程库。
```

![image-20231221225939946](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221225939946.png)

输入完后没有任何反应表示添加成功。



我们使用以下命令来将本地库上传到云端（其实是本地分支和Github远程分支的合并，下面实际上是本地master分支和远程分支main（github默认分支）的合并）

```
git push -u origin master

如果报错请尝试 git push -u origin master -f 进行强制推送

如果推送成功但是出现差异过大无法PR：
在本地打开git bash，输入以下指令
git checkout master 
//切换到master分支
git branch main master 
//创建main分支为master子分支
git checkout main
//切换到main分支
git push -u origin main -f 
//强制推送main分支。（由于origin本身就是main分支，所以直接推送就会直接修改GitHub仓库中的内容）
```

![image-20231221230101771](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221230101771.png)



然后我们的GitHub上收到一条合并分支的消息，点Compare & pull request

![image-20231221230242742](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221230242742.png)

![image-20231221232900926](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221232900926.png)

一路确认，哪里亮了点哪里（如果遇到Conflicts，请按照前面分支内容处理）。最后结果是这样的

![](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221233050948.png)

回头看创建的库，ok，更新完毕。

![image-20231222000159508](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231222000159508.png)

##### 2.Copy别人的项目到本地

使用 git clone <PATH>命令。

点击项目右上角<>Code，切换到HTTPS，然后复制。

![image-20231221233520117](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221233520117.png)

然后在本地要存储的位置右击打开Git Bash

输入 git clone <鼠标右键Paste粘贴网址>

![image-20231221214219798](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221214219798.png)

![image-20231221214311679](C:\Users\YXY\AppData\Roaming\Typora\typora-user-images\image-20231221214311679.png)

然后这个项目就被下载下来了，非常简单。



GitHub本身也有很多有趣的操作，比如云复制fork，评论issues，提交修改申请Pull Requests等等，大家感兴趣可以自己搜索>_< ，其实所有的操作GitHub官方也有Help文档，只不过都是英文hhh。