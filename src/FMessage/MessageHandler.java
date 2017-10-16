/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FMessage;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;

/**
 * 消息处理器
 * @author sq
 */
public class MessageHandler {
    
    private String Owner;
    public MessageHandler(String owner)
    {
        this.Owner=owner;
    }
    
    public TransmittedMessage HelloHello(TransmittedMessage helloMsg) throws Exception
    {
        String receiver=helloMsg.getSender();
        String sender=helloMsg.getReceiver();
        long sendTime=helloMsg.getTimeStamp();
        String sendMsgId=helloMsg.getMessageId();
        String sendMsgType=helloMsg.getMessageType();
        FOperationCode sendCode=helloMsg.getCode();
        FOperationStatus sendStatus=helloMsg.getStatus();
        HashMap<String,Object> sendData=helloMsg.getData();
        if(sender != Owner || sendMsgType != "Request" || sendStatus != FOperationStatus.Send)
        {
            return new TransmittedMessage(Owner,receiver,System.currentTimeMillis() / 1000,"Response",sendMsgId,sendCode,FOperationStatus.WTF,sendData);
        }
        else
        {
            if (sendCode != FOperationCode.HelloWorld) {
                return new TransmittedMessage(Owner,receiver,System.currentTimeMillis() / 1000,"Response",sendMsgId,sendCode,FOperationStatus.Error,sendData);
            }
            else
                return new TransmittedMessage(Owner,receiver,System.currentTimeMillis() / 1000,"Response",sendMsgId,sendCode,FOperationStatus.Return,sendData);
        }
                
    }
    
    public static void main(String[] args) throws Exception {
        String sender = "Tester";
        TransmittedMessageIDPool idPool = new TransmittedMessageIDPool(sender);
        String receiver = "Debuger";
        long timeStamp = System.currentTimeMillis() / 1000;
        String messageType = "Request";
        String messageId = idPool.getOneRandomID(sender);
        FOperationCode code = FOperationCode.HelloWorld;
        FOperationStatus status = FOperationStatus.Send;
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("ProjectName", "MyFirstProject");
        data.put("LayerName", "MyFirstLayer");
        data.put("Feature", "MyFirserFeature");
        TransmittedMessage sMessage = new TransmittedMessage(sender, receiver, timeStamp, messageType, messageId, code, status, data);
        //测试部分
        MessageHandler msgHandler= new MessageHandler("Debuger");
        TransmittedMessage sReturnMsg = msgHandler.HelloHello(sMessage);
        JSONObject jObject = sReturnMsg.convertMessageToJson();
        System.out.print(jObject);
    }
    
}
