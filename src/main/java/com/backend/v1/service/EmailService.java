package com.backend.v1.service;

import com.backend.v1.common.utils.EmailDetails;

public interface EmailService {

    void sendSimpleMail(EmailDetails details);

}
