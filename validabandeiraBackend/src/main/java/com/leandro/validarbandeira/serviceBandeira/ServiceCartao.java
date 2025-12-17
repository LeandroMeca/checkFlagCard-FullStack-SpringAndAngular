package com.leandro.validarbandeira.serviceBandeira;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceCartao {


    //regras de negocio


    public ResponseEntity validarBandeira(String numero) {
        // lógica para validar a bandeira do cartão com base no número fornecido
        if (numero == null || numero.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Parâmetro 'numero' é obrigatório");
        }
        String digits = numero.replaceAll("\\D", "");
        int len = digits.length();

        // Verificação geral de quantidade de dígitos (a maioria das bandeiras usa entre 13 e 19 dígitos)
        if (len < 13 || len > 19) {
            return ResponseEntity.badRequest().body("Número inválido: deve conter entre 13 e 19 dígitos");
        }

        // Verificações por bandeira com checagem de quantidade de dígitos esperada
        if (digits.startsWith("4")) {
            // VISA: normalmente 13, 16 ou 19 dígitos
            if (len == 13 || len == 16 || len == 19) {
            return ResponseEntity.ok("VISA");
            } else {
            return ResponseEntity.badRequest().body("VISA inválido: deve conter 13, 16 ou 19 dígitos");
            }
        } else if (digits.startsWith("34") || digits.startsWith("37")) {
            // AMEX: 15 dígitos
            if (len == 15) {
            return ResponseEntity.ok("AMEX");
            } else {
            return ResponseEntity.badRequest().body("AMEX inválido: deve conter 15 dígitos");
            }
        } else if (digits.matches("^5[1-5].*")) {
            // MASTERCARD: 16 dígitos
            if (len == 16) {
            return ResponseEntity.ok("MASTERCARD");
            } else {
            return ResponseEntity.badRequest().body("MASTERCARD inválido: deve conter 16 dígitos");
            }
        } else if (digits.startsWith("6011") || digits.startsWith("65") || digits.matches("^64[4-9].*")) {
            // DISCOVER: 16 dígitos (comuns)
            if (len == 16) {
            return ResponseEntity.ok("DISCOVER");
            } else {
            return ResponseEntity.badRequest().body("DISCOVER inválido: deve conter 16 dígitos");
            }
        } else {
            return ResponseEntity.ok("UNKNOWN");
        }
    }


}
