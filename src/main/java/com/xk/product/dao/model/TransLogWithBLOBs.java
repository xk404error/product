package com.xk.product.dao.model;

public class TransLogWithBLOBs extends TransLog {
    private String req;

    private String res;

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req == null ? null : req.trim();
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res == null ? null : res.trim();
    }
}