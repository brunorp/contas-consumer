package contaspratosapi.contaspratosapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import contaspratosapi.contaspratosapi.model.Conta;
import contaspratosapi.contaspratosapi.model.Prato;
import contaspratosapi.contaspratosapi.repository.ContasRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ContasService {

    @Autowired
    private ContasRepository contasRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "prato")
    public void lerMensagem(String mensagem){
        try{
            Prato prato = objectMapper.readValue(mensagem, Prato.class);
            LocalDate localDate = LocalDate.now();
            Conta contas = new Conta();
            Double ultimoValor = retornaMaiorValor();
            if(prato.vendido){
                ultimoValor += prato.getValor();
                contas.setData(localDate);
                contas.setValor(ultimoValor);
            }
            if(tableIsEmpty() != 0) {
                LocalDate dataContaAtual = lerUltimoRegistro().getData();
                if (!dataContaAtual.equals(localDate))
                    contasRepository.save(contas);
                else {
                    Conta atualizaConta = lerUltimoRegistro();
                    atualizaConta.setValor(ultimoValor);
                    contasRepository.save(atualizaConta);
                }
            }
            else
                contasRepository.save(contas);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Double retornaMaiorValor(){
        if(tableIsEmpty() != 0)
            return lerUltimoRegistro().valor;
        else
            return 0.0;
    }

    public Conta lerUltimoRegistro(){
        return contasRepository.findByHighestDate(PageRequest.of(0, 1));
    }

    @NotNull
    private Long tableIsEmpty(){
        return contasRepository.count();
    }
}
