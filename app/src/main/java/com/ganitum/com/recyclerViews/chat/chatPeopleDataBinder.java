package com.ganitum.com.recyclerViews.chat;

public class chatPeopleDataBinder
{
    private String peopleName = "";
    private String peopleImageURL = "";

    public chatPeopleDataBinder(String peopleName, String peopleImageURL)
    {
        this.peopleName = peopleName;
        this.peopleImageURL = peopleImageURL;
    }

    public String getPeopleName()
    {
        return peopleName;
    }

    public String getPeopleImageURL()
    {
        return peopleImageURL;
    }
}
