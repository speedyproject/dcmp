## Distributed Configuration Management Platform

[![Build Status](https://travis-ci.org/speedyproject/dcmp.svg?branch=master)](https://travis-ci.org/speedyproject/dcmp)
[![Dependency Status](https://www.versioneye.com/user/projects/59a8c4c00fb24f003d09cf9b/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/59a8c4c00fb24f003d09cf9b)
[![codebeat badge](https://codebeat.co/badges/ae711829-ac75-4bfc-85b7-64e76e37d10a)](https://codebeat.co/projects/github-com-speedyproject-dcmp-master)
[![AUR](https://img.shields.io/badge/license-GPL-orange.svg)](https://www.gnu.org/licenses/gpl-3.0.html)
[![Project Stats](https://www.openhub.net/p/dcmp/widgets/project_thin_badge.gif)](https://www.openhub.net/p/dcmp)

# DCMP

&#12288;&#12288;该项目基于Consul作为注册发现，服务端以SpringBoot实现，无代码侵入模式编程，支持原生web应用(Servlet) <br/>
,Struts2,SpringProject。"0"配置 (无任何XML) 使用简单方便，配置自动更新，Git仓库配置文件一旦更改配置数据也 <br/>
即刻刷新，不需要MQ。低侵入性或无侵入性、强兼容性。<br/>
<br/>

## 组件

*	config-web       服务端
*	config-client    客户端

## 如何使用

&#12288;&#12288;Spring 项目使用方法，引入```config-client```包后: <br/>
```java
    @Bean(initMethod = "load", destroyMethod = "destroy")
    public ConfigurerBean configurerBean() {
        ConfigurerBean configurerBean = new ConfigurerBean();
        return configurerBean;
    }

    @Bean
    public SpringBeanFactory springBeanFactory() {
        return new SpringBeanFactory();
    }
```
配置好两个Bean，在类成员上使用Spring原生```@Value("${key}")```取值


文档持续更新中。。。。

## 联系与建议

*	E-mail: gksqlsdy@gmail.com


