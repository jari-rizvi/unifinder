package com.example.unifinder.Modal;

public class UserComplains {


    private int id;
    private String complainsNo;
    private String bookingNo;
    private String complainMsg;

    public UserComplains() {
        this.id = id;
        this.complainsNo = complainsNo;
        this.bookingNo = bookingNo;
        this.complainMsg = complainMsg;

    }

    public String getComplainMsg() {
        return complainMsg;
    }

    public void setComplainMsg(String complainMsg) {
        this.complainMsg = complainMsg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplainsNo() {
        return complainsNo;
    }

    public void setComplainsNo(String complainsNo) {
        this.complainsNo = complainsNo;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }
}
