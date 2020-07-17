

## 依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <modules>
        <module>luna-commons-file</module>
        <module>luna-commons-common</module>
        <module>luna-commons-baidu</module>
        <module>luna-commons-ali</module>
        <module>luna-commons-tencent</module>
        <module>luna-commons-message</module>
        <module>luna-commons-api</module>
        <module>luna-commons-media</module>
    </modules>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.2.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.luna</groupId>
    <artifactId>luna-commons</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <java.version>1.8</java.version>
        <!-- github server corresponds to entry in ~/.m2/settings.xml -->
        <github.global.server>github</github.global.server>
    </properties>

    <repositories>
        <repository>
            <id>luna-commons-mvn-repo</id>
            <url>https://raw.github.com/czy1024/luna-commons/mvn-repo-luna-commons/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>

    <dependencyManagement>
        <dependencies>
            <!-- Mysql驱动包 -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.19</version>
            </dependency>

            <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-text -->
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-text</artifactId>
                <version>1.8</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-collections4 -->
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-collections4</artifactId>
                <version>4.3</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/commons-validator/commons-validator -->
            <dependency>
                <groupId>commons-validator</groupId>
                <artifactId>commons-validator</artifactId>
                <version>1.6</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
                <version>3.9</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
            <dependency>
                <groupId>org.apache.httpcomponents</groupId>
                <artifactId>httpclient</artifactId>
                <version>4.5.11</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
            <dependency>
                <groupId>commons-io</groupId>
                <artifactId>commons-io</artifactId>
                <version>2.6</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/commons-net/commons-net -->
            <dependency>
                <groupId>commons-net</groupId>
                <artifactId>commons-net</artifactId>
                <version>3.6</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
            <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>27.0.1-jre</version>
            </dependency>
            <!-- JSON工具类 -->
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-databind</artifactId>
                <version>2.10.3</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.72</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>2.1.1</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.13</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
<!--            <exclusions>-->
<!--                <exclusion>-->
<!--                    <groupId>com.vaadin.external.google</groupId>-->
<!--                    <artifactId>android-json</artifactId>-->
<!--                </exclusion>-->
<!--            </exclusions>-->
            <scope>test</scope>
        </dependency>
    </dependencies>

    <distributionManagement>
        <repository>
            <id>luna.repo</id>
            <name>Temporary Staging Repository</name>
            <url>file://${project.build.directory}/mvn-repo</url>
        </repository>
    </distributionManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>

            <plugin>
                <artifactId>maven-deploy-plugin</artifactId>
                <version>2.8.1</version>
                <configuration>
                    <altDeploymentRepository>internal.repo::default::file://${project.build.directory}/mvn-repo
                    </altDeploymentRepository>
                </configuration>
            </plugin>

            <plugin>
                <groupId>com.github.github</groupId>
                <artifactId>site-maven-plugin</artifactId>
                <version>0.12</version>
                <configuration>
                    <message>Maven artifacts for ${project.version}</message>  <!-- git commit message -->
                    <noJekyll>true</noJekyll>                                  <!-- disable webpage processing -->
                    <outputDirectory>${project.build.directory}/mvn-repo
                    </outputDirectory> <!-- matches distribution management repository url above -->
                    <branch>refs/heads/mvn-repo-luna-commons
                    </branch>                       <!-- remote branch name -->
                    <includes>
                        <include>**/*</include>
                    </includes>
                    <repositoryName>luna-commons</repositoryName>      <!-- github repo name -->
                    <repositoryOwner>czy1024</repositoryOwner>    <!-- github username  -->
                </configuration>
                <executions>
                    <!-- run site-maven-plugin's 'site' target as part of the build's normal 'deploy' phase -->
                    <execution>
                        <goals>
                            <goal>site</goal>
                        </goals>
                        <phase>deploy</phase>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>

```

## 更新日志

### 1.0--SNAPSHOT
+ 增加JavaCv 视频处理, 包含图片合成视频和视频截取图片 

+ 增加百度天气返回,一周热点搜索,词语匹配

+ 增加百度文本比对纠错Api

+ 改变百度Key获取为定时任务

+ 加入支付宝黑盒测试包括查询订单,付款连接等

+ 引入环境配置文件,免去可能密钥泄露风险

+ 增加百度人体识别

+ 增加百度语音合成

+ 增加腾讯地图 ip 经纬度返回位置信息

+ 增加百度Ocr文字位置 通用版和精确版

+ 增加ffmpeg图像处理 截帧 合成 视频截取 视频截图

+ 增加百度物体识别,链接百度百科,自动识别名人,著名建筑物等

+ 增加勾画人脸矩形框结合百度人脸检测Api

+ 增加百度人证审核

+ 增加学小易答案搜索Api

+ 增加百度Ocr 人脸识别 人脸检测 人脸比对 活体检测 Api调用