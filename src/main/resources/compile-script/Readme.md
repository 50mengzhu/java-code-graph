# g4文件使用方法 

## g4文件
g4文件是专门为antlr4准备的语法分析以及词法分析文件。antlr4提供了语言编译的框架，
如果需要创建一门属于我们自己的语言，那么直接可以定位好g4文件，并生成对应的语言包
即可（目前支持的语言可以查看antlr4官方github库 https://github.com/antlr/grammars-v4 ）
<p>
其中antlr4和g4的github地址为

```java 
g4     https://github.com/antlr/grammars-v4/tree/master/java/java8
antlr4 https://github.com/antlr/antlr4
```
我们需求为对 java项目进行词法分析以及语法分析，此类成熟语言g4早已为我们准备好，
因此我们直接获取即可
https://github.com/antlr/grammars-v4/tree/master/java

存在两个g4文件，
- lexer结尾的文件为词法分析器
- parser结尾的语法分析


## 使用方法
从g4的github仓库中获取对应的g4文件存放在工程当中。
### 下载idea插件
antlr v4 grammar plugin 搜索下载

### 配置g4文件生成规则
1. 插件下载完成之后，右键g4文件。选择 `configure ANTLR...` 
配置语言和生成的包选项，配置完保存
2. 右键g4文件，选择 `Generate ANTLR Recognizer`即可