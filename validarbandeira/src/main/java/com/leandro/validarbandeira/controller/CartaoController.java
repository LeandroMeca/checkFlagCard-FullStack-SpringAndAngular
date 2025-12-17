package com.leandro.validarbandeira.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.validarbandeira.serviceBandeira.ServiceCartao;

import lombok.Data;


@Data
@RestController
@RequestMapping("cartao")
public class CartaoController {


    private final ServiceCartao serviceCartao;


    @GetMapping("teste")
    public String gfsfg(){
        return "ok";
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<String> validarBandeira(@RequestParam("numero") String numero) {
    if (serviceCartao == null) {
        return ResponseEntity.internalServerError().body("ServiceCartao não inicializado");
    }
    Object resposta = serviceCartao.validarBandeira(numero);
    if (resposta instanceof ResponseEntity) {
        @SuppressWarnings("unchecked")
        ResponseEntity<String> resp = (ResponseEntity<String>) resposta;
        return resp;
    }
    return ResponseEntity.ok(String.valueOf(resposta));
    }
}
