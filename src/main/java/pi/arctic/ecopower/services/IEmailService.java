package pi.arctic.ecopower.services;

import pi.arctic.ecopower.DTO.Payment;

public interface IEmailService {
    public void sendPaymentReceiptEmail(String recipientEmail, Payment payment);
}
