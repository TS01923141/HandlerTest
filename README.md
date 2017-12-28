# HandlerTest
用Thread控制進度條
 * 流程
 * 1.Thread2設定Message
 * 2.Thread2的Handler用sendMessage方法發出message至Thread1的MessageQueue
 * 3.Thread1的Handler用handleMessage方法透過Looper從MessageQueue接收Message
 * 4.Thread1的Handler用取得的Message資料設定進度條
