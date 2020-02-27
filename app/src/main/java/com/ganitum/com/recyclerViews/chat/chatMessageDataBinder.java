package com.ganitum.com.recyclerViews.chat;

public class chatMessageDataBinder
{
    private String message = "";
    private String sender = "";
    private String receiver = "";
    private long timeStamp = 0;

    public chatMessageDataBinder(String message, String sender, String receiver, long timeStamp)
    {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
        this.timeStamp = timeStamp;
    }

    public String getMessage()
    {
        return message;
    }

    public String getSender()
    {
        return sender;
    }

    public String getReceiver()
    {
        return receiver;
    }

    public String getTime()
    {
        return receiver;
    }
}
