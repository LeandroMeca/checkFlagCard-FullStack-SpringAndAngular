package com.leandro.validarbandeira.serviceBandeira;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServiceCartao {


    //regras de negocio


    public ResponseEntity<?> validarBandeira(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Parâmetro 'numero' é obrigatório");
        }
        String digits = numero.replaceAll("\\D", "");
        int len = digits.length();

        if (len < 13 || len > 19) {
            return ResponseEntity.badRequest().body("Número inválido: deve conter entre 13 e 19 dígitos");
        }

        String fileName;
        if (digits.startsWith("4")) {
            if (len == 13 || len == 16 || len == 19) {
                fileName = "visa.svg";
            } else {
                return ResponseEntity.badRequest().body("VISA inválido: deve conter 13, 16 ou 19 dígitos");
            }
        } else if (digits.startsWith("34") || digits.startsWith("37")) {
            if (len == 15) {
                fileName = "amex.svg";
            } else {
                return ResponseEntity.badRequest().body("AMEX inválido: deve conter 15 dígitos");
            }
        } else if (digits.matches("^5[1-5].*")) {
            if (len == 16) {
                fileName = "mastercard.svg";
            } else {
                return ResponseEntity.badRequest().body("MASTERCARD inválido: deve conter 16 dígitos");
            }
        } else if (digits.startsWith("6011") || digits.startsWith("65") || digits.matches("^64[4-9].*")) {
            if (len == 16) {
                fileName = "discover.svg";
            } else {
                return ResponseEntity.badRequest().body("DISCOVER inválido: deve conter 16 dígitos");
            }
        } else {
            fileName = "unknown.svg";
        }

        String path = "static/images/" + fileName;
        org.springframework.core.io.Resource resource = new ClassPathResource(path);
        if (!resource.exists()) {
            return ResponseEntity.status(500).body("Imagem não encontrada: " + path);
        }

        org.springframework.http.MediaType mediaType = fileName.endsWith(".svg")
                ? MediaType.parseMediaType("image/svg+xml")
                : MediaType.IMAGE_PNG;

        return org.springframework.http.ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }


}
