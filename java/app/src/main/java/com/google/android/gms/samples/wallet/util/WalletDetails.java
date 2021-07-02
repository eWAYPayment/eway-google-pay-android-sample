package com.google.android.gms.samples.wallet.util; ;

public class WalletDetails {

    private String Token;

    /**
     * No args constructor for use in serialization
     *
     */
    public WalletDetails() {
    }

    /**
     *
     * @param token
     */
    public WalletDetails(String token) {
        super();
        this.Token = token;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        this.Token = token;
    }

    public WalletDetails withToken(String token) {
        this.Token = token;
        return this;
    }


}