# FarmerHomeOA
### 项目介绍
农民之家OA系统包括县级，乡级，部门，村级等账户。村级账户录入农户信息，各级账户根据自己所在乡村可以看到对应的农户信息及统计图表。游客可以在官网咨询，县级账号在OA系统中看到这些咨询之后，分配给对应的乡和部门去办理，办理人接收到事项办理短信，若在最后一天期限还未办理，会再次收到督办短信。办理人填写办理结果后，游客根据咨询号可在官网查询到结果，并反馈满意度。每个季度第一天，村级对应人员会收到上传报表提醒短信，并会在期限内最后一天在收到提醒短信。超过期限上传的会归档到逾期上传，未上传的会归档到未上传。上传报表后由乡级审查，上传至县级，县级审核通过则归档。若不通过，按原路返回。各级账户可看到对应的归档以及服务报表统计图表。
### 技术介绍
项目的基础框架使用的是spring boot + mybatis搭建，文件上传用的是阿里云的oss.
代码方面涉及到了spring boot + mybatic的集成，spring boot 的过滤器，监听器，定时器，跨域等配置，已及mybatis的使用。
使用注入得到数据源，通过构造方法赋值命名空间，传入方法ID得到完整名空间。
