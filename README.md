# 教程

- 打开exe 设置源位置 官方源 BMCLAPI源(默认生成配置为官方源)

# 配置文件
```json5
{
  "sourceHome": "0", // 0为官方源 1为BMCLAPI源
  "download-mode": "0", // 模式 0 -> gugug官方模式 1 -> curse打包 2 -> modrinth打包, 3 -> ftb打包
  "is-client": true, // 是否是下载客户端
  "mc-version": "1.20.1" // 模式0的情况下调用
}
```