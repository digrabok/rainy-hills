package com.digrabok.crx.rainyHills.web.beans;

public enum NavigationEnum {
    list,
    detail,
    add;

    private Object payload;

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
