package com.challengepicpay.services;

import com.challengepicpay.domain.user.User;
import com.challengepicpay.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {


    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getMail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("",
                notificationRequest, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("Serviço de notificação fora do ar");
            throw new Exception("Serviço de notificação fora do ar");
        }

    }
}
