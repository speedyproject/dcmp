## Distributed Configuration Management Platform

[![Build Status](https://travis-ci.org/speedyproject/dcmp.svg?branch=master)](https://travis-ci.org/speedyproject/dcmp)
[![Dependency Status](https://www.versioneye.com/user/projects/59a8c4c00fb24f003d09cf9b/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/59a8c4c00fb24f003d09cf9b)
[![codebeat badge](https://codebeat.co/badges/ae711829-ac75-4bfc-85b7-64e76e37d10a)](https://codebeat.co/projects/github-com-speedyproject-dcmp-master)
[![AUR](https://img.shields.io/badge/license-GPL-orange.svg)](https://www.gnu.org/licenses/gpl-3.0.html)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/org.nicosoft.config/config-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.nicosoft.config/config-client)


# DCMP

&#12288;&#12288;该项目基于Consul作为注册发现，服务端以SpringBoot实现，无代码侵入模式编程，支持原生web应用(Servlet) <br/>
,Struts2,SpringProject。"0"配置 (无任何XML) 使用简单方便，配置自动更新，Git仓库配置文件一旦更改配置数据也 <br/>
即刻刷新，不需要MQ。低侵入性或无侵入性、强兼容性。<br/>
<br/>

## 组件

*   config-web      服务端
*   config-client   客户端
<br/>

## 如何使用

&#12288;&#12288;Spring 项目使用方法，引入```config-client```包后在resource目录创建```config.yml```: <br/>
```yaml
    config:
        profile: dev                                    # profile                                            
    token: er34xfwefwerqxrx2                            # 交换的Token可有可无
    consul:
        host: localhost:8500                            # consul 服务器地址
        ttl: 3                                          # 刷新时间
        port: 8080                                      # 本地开放的端口
        check: http://localhost:8080/health             # 心跳检测地址
        serviceId: config-client                        # 服务ID
        serviceName: config-client                      # 服务器名
    filePath: /home/travis/build/speedyproject/dcmp/config-client/repertory # 配置文件临时路径
```
创建好项目后，运行时在类成员上使用Spring原生```@Value("${key}")```取值

文档持续更新中。。。。
<br/>

## 联系与建议

*	E-mail: gksqlsdy@gmail.com


