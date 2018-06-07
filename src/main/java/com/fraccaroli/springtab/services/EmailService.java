package com.fraccaroli.springtab.services;

import org.springframework.mail.SimpleMailMessage;

import com.fraccaroli.springtab.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	
}
