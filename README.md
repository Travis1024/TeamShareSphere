# TeamShareSphere

![Static Badge](https://img.shields.io/badge/License-MIT-blue)   ![Static Badge](https://img.shields.io/badge/Statue-Developing-blue)   ![Static Badge](https://img.shields.io/badge/Spring_Cloud-v2021.0.8-orange)  ![Static Badge](https://img.shields.io/badge/Sonar_Qube-All_A-g)  



## 功能介绍

团队文档共享及在线预览平台

- 分布式架构：使用 Spring Cloud、Spring Boot 进行模块化开发。
- CI / CD 部署与搭建：基于 Jenkins、Dockerfile、Harbor 等实现多模块自动编译、镜像生成及推送、持续集成与部署动作执行，并结合 SonarQube 实现代码审查。
- 分布式存储架构：底层采用 Minio 实现分布式对象存储及数据保护，保障文件服务的高可用及高扩展性。
- 媒体文件多源选择：音视频流提供多源播放方案，支持 Http-Range 与 HLS-M3U8 两种播放方案。
- 直链生成：支持文件直链生成与分享，支持设置文件直链过期时间。
- 开源方案整合：整合 KkFileview 文件在线浏览解决方案。
- 多文件类型支持：支持 Office 办公文档、流程图文件、图像文件、软件模型文件、3D 模型文件、CAD 模型文件、压缩包、代码文件、音视频媒体等文件的在线预览，共计 70 余种。



## 在线预览类型支持名单

- 办公文档：xls, xlsx, doc, docx, ppt, pptx, txt, odt, ott, sxw, rtf, wpd, xsi, ods, ots, sxc, csv, tsv, odp, otp, pdf
- 压缩包文件：zip, rar, jar, tar, gzip, 7z
- 音视频文件：mp3, wav, mp4, avi, mov, flv, mkv, wmv, asf, rmvb
- 图像文件：png, jpeg, jpg
- 程序文件：py, java, cpp, c, xml, php, js, json, css, html
- 3D模型文件：obj, 3ds, stl, ply, gltf, glb, off, 3dm, fbx, dae, wrl, 3mf, ifc, brep, step, iges, fcstd, bim
- CAD模型文件：dwg, dxf, dwf, iges , igs, dwt, dng, ifc, dwfx, stl, cf2, plt
- 其他文件：xmind, drawio, eml, epub, bpmn, svg



## 项目架构

- 架构图

![image-20240420171855662](https://travisnotes.oss-cn-shanghai.aliyuncs.com/mdpic/202404201718776.png)

- CI / CD 流程图

![image-20240420172024007](https://travisnotes.oss-cn-shanghai.aliyuncs.com/mdpic/202404201720034.png)
