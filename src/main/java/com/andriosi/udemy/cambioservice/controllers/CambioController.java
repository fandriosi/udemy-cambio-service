package com.andriosi.udemy.cambioservice.controllers;

import com.andriosi.udemy.cambioservice.model.Cambio;
import com.andriosi.udemy.cambioservice.repositories.CambioRepository;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("cambio-service")
public class CambioController {
    @Autowired
    private Environment environment;
    @Autowired
    private CambioRepository repository;
    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON)
    public Cambio getCambio(@PathVariable("amount") BigDecimal amount,
                            @PathVariable("from") String from,
                            @PathVariable("to") String to){
        Cambio cambio = repository.findByFromAndTo(from, to);
        if(cambio == null) throw new RuntimeException("Currency Unsupported");
        String port = environment.getProperty("local.server.port");
        cambio.setConvertedValue(getConvertedValue(amount, cambio));
        cambio.setEnviroment(port);
        return cambio;
    }

    private BigDecimal getConvertedValue(BigDecimal amount, Cambio cambio) {
        return cambio.getConvertionFactor().multiply(amount).setScale(2, RoundingMode.CEILING);
    }
}
