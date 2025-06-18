package com.Case_Study.Payment.controller;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Case_Study.Payment.feign.BookingClient;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class Payments {
	
	@Autowired
    private BookingClient bookingClient;

    @Value("${razorpay.key_id}")
    private String key;

    @Value("${razorpay.secret_key}")
    private String secret;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestParam long bookingId) {
        try {
            // Step 1: Get amount from Booking Microservice
            Double amount = bookingClient.showTotalCost(bookingId);

            // Step 2: Initialize Razorpay client
            RazorpayClient client = new RazorpayClient(key, secret);

            // Step 3: Create Razorpay order
            JSONObject options = new JSONObject();
            options.put("amount", amount.intValue() * 100); // convert to paise
            options.put("currency", "INR");
            options.put("receipt", "txn_" + UUID.randomUUID());
            options.put("payment_capture", 1);

            Order order = client.orders.create(options);
            return new ResponseEntity<>(order.toString(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Order Creation Failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
