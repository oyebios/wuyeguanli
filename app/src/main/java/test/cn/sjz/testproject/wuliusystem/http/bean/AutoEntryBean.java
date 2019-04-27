package test.cn.sjz.testproject.wuliusystem.http.bean;

import java.io.Serializable;

/**
 * Created by lwd on 2019/4/27.
 * Explain
 */

public class AutoEntryBean implements Serializable {

    /**
     * code : string
     * createTime : 0
     * id : 0
     * isDelete : 0
     * recipient : string
     * recipientAddress : string
     * recipientMobile : string
     * sender : string
     * senderAddress : string
     * senderMobile : string
     * status : 0
     */

    private String code= "";
    private int createTime;
    private int id;
    private int isDelete;
    private String recipient= "";
    private String recipientAddress= "";
    private String recipientMobile= "";
    private String sender= "";
    private String senderAddress= "";
    private String senderMobile= "";
    private int status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientMobile() {
        return recipientMobile;
    }

    public void setRecipientMobile(String recipientMobile) {
        this.recipientMobile = recipientMobile;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
