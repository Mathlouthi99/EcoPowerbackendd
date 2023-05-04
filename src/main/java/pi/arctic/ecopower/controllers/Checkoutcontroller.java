package pi.arctic.ecopower.controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.sun.istack.NotNull;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.arctic.ecopower.DTO.Achat;
import pi.arctic.ecopower.DTO.Payment;
import pi.arctic.ecopower.DTO.Reponseachat;
import pi.arctic.ecopower.services.CheckoutserviceImp;
import pi.arctic.ecopower.services.EmailService;

import javax.servlet.http.HttpServletRequest;
@CrossOrigin(origins = "*")// spring et angular
@RestController
@RequestMapping(
        "/checkout")
public class Checkoutcontroller {
    @Autowired
     EmailService emailService;
    @Autowired
    CheckoutserviceImp checkoutservice ;
    @PostMapping("/achat")
    //passer une commande et la triater
 public Reponseachat placeorder(@NotNull HttpServletRequest Request , @RequestBody Achat achat ) throws Exception {
        Reponseachat repenseachat = checkoutservice.placeOrder(Request,achat);
        return repenseachat;
    }
    /*@PostMapping("/payment")
    public ResponseEntity<String>createPaymentIntent(@RequestBody Payment payment )throws StripeException{
        PaymentIntent paymentIntent =checkoutservice.createPaymentIntent(payment);
        String payments = paymentIntent.toJson();
        return new ResponseEntity<>(payments , HttpStatus.OK);
    }*/
    @PostMapping("/payment")
    public ResponseEntity<String> createPaymentIntent(@RequestBody Payment payment) throws StripeException {
        PaymentIntent paymentIntent = checkoutservice.createPaymentIntent(payment);

        // Here, you can access the paymentIntent object and retrieve its properties
        String paymentIntentId = paymentIntent.getId();
        String paymentStatus = paymentIntent.getStatus();

        // Process the paymentIntent object and send the appropriate response
         if (paymentStatus.equals("succeeded")) {
            String payments = paymentIntent.toJson();
             emailService.sendPaymentReceiptEmail(payment.getEmailrecu(), payment);

             return new ResponseEntity<>(payments , HttpStatus.OK);

        }
         else {
             String payments = paymentIntent.toJson();
             return new ResponseEntity<>(payments , HttpStatus.INTERNAL_SERVER_ERROR);

        }

        }

}
