package com.jkwebservice.service.core;

import java.io.Serializable;

public class RequestDTO<D> implements Serializable {
    private D data;

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
