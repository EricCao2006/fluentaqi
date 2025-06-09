# 东软环保公众监督系统(fluent design)

## 项目需求

    汇总监督员提供的空气质量信息，

    管理员将信息指派给网格员进行实地考察和检测，

    从而得到不同地区的空气质量AQI实时数据。

    再进行统计以作为环保方面决策者进行决策的依据。

## 项目架构

### 项目框架

    Java JDK17;

    Javafx 17.0.15;

    Transit 2.0.0;  ——适用于Javafx的类Fluent Design库

### 项目模块

    1、 NEPS：公众监督员端。

    2、 NEPG：AQI检测网格员端。

    3、 NEPM：系统管理端。

## 关于AQI空气质量指数

    Air Quality Index描述了空气清洁或者污染的程度。

    空气质量指数AQI计算公式：
        AQI = MAX（SO2_AQI，CO_AQI，PM2.5_AQI）