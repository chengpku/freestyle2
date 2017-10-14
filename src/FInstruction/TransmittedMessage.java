/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FInstruction;

import java.util.HashMap;

/**
 * 传输信息类
 *
 * @author sq
 */
public class TransmittedMessage {

    private String Sender;
    private String Receiver;
    private long TimeStamp;
    private String MessageType;
    private String MessageId;
    private OperationCode Code;
    private HashMap<String, Object> Data;

    public TransmittedMessage() {

    }

    /**
     * 构造传输信息
     * @param sender    发送者
     * @param receiver  接收者
     * @param timeStamp Unix时间戳
     * @param messageType   信息类型
     * @param messageId 信息ID
     * @param code  指令代码
     * @param data  传输的数据
     */
    public TransmittedMessage(
            String sender,
            String receiver,
            long timeStamp,
            String messageType,
            String messageId,
            OperationCode code,
            HashMap<String, Object> data
    ) {
        this.Sender = sender;
        this.Receiver = receiver;
        this.TimeStamp = timeStamp;
        this.MessageType = messageType;
        this.MessageId = messageId;
        this.Code = code;
        this.Data = data;
    }
}
