package com.preciseIT.auth.service;

import com.preciseIT.auth.TOTP;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

@Service
public class TotpService {
    public boolean verifyCode(String totpCode, String secret) {
        String totpCodeBySecret = generateTotpBySecret(secret);

        return totpCodeBySecret.equals(totpCode);
    }

    private String generateTotpBySecret(String secret) {
        long timeFrame = System.currentTimeMillis() / 1000L / 30;

        String timeEncoded = Long.toHexString(timeFrame);

        String totpCodeBySecret;
        try {
            char[] secretEncoded = (char[]) new Hex().encode(secret);

            totpCodeBySecret = TOTP.generateTOTP(String.copyValueOf(secretEncoded), timeEncoded, "6");
        } catch (EncoderException e) {
            throw new RuntimeException(e);
        }
        return totpCodeBySecret;
    }
}