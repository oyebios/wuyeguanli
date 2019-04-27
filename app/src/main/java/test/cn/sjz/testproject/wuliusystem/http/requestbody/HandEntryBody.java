package test.cn.sjz.testproject.wuliusystem.http.requestbody;

import java.io.Serializable;

/**
 * Created by lwd on 2019/4/27.
 * Explain
 */

public class HandEntryBody implements Serializable {

    /**
     * code : string
     * recipient : string
     * recipientAddress : string
     * recipientMobile : string
     * sender : string
     * senderAddress : string
     * senderMobile : string
     */

    private String code;
    private String recipient;
    private String recipientAddress;
    private String recipientMobile;
    private String sender;
    private String senderAddress;
    private String senderMobile;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
