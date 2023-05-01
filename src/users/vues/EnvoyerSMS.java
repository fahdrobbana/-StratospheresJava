/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package users.vues;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author joujo
 */
public class EnvoyerSMS {
     // Vos identifiants Twilio
            private static final String ACCOUNT_SID = "AC91ce07546c98d7ef7de4f4d065364139";
            private static final String AUTH_TOKEN = "dca9092bf1335574b92c60d66fad9be8";

            // Numéro de téléphone Twilio
            private static final String TWILIO_PHONE_NUMBER = "+15675220383";

            public void send(String toPhoneNumber, String message) {
                try {
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                    Message twilioMessage = Message.creator(
                            new PhoneNumber(toPhoneNumber),
                            new PhoneNumber(TWILIO_PHONE_NUMBER),
                            message).create();
                    System.out.println("Message envoyé avec succées");
                } catch (Exception ex) {
                    System.out.println("Error sending SMS: " + ex.getMessage());
                }

        }
}
