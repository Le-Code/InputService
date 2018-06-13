package com.yao.entity;

/**
 * 命令类
 */
public class Command {

    public static final int FORBIDDEN = 1;
    public static final int SENDMESSAGE = 2;


    public static final int MESSAGE_NOTHING = 0;
    public static final int MESSAG_ETEXT = 1;
    public static final int MESSAGE_IMAGEANDTEXT = 2;

    /**
     * 命令类型
     * 1.表示强制关闭，2表示推送消息
     */
    private int commandType;

    /**
     * 消息类型
     * 0表示什么都不是
     * 1表示纯文本
     * 2表示图片加信息
     */
    private int messageType;

    /**
     * 目标主机的ip
     * 如果为null，则发送所有在线的主机
     */
    private String destIp;

    public Command() {
    }

    public Command(int commandType, int messageType, String destIp) {
        this.commandType = commandType;
        this.messageType = messageType;
        this.destIp = destIp;
    }

    public int getCommandType() {
        return commandType;
    }

    public void setCommandType(int commandType) {
        this.commandType = commandType;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getDestIp() {
        return destIp;
    }

    public void setDestIp(String destIp) {
        this.destIp = destIp;
    }
}
