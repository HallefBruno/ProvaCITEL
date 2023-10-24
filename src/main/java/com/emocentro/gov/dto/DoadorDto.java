package com.emocentro.gov.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class DoadorDto implements Serializable {
    
    private String nome;
    private String cpf;
    private String rg;
    @JsonProperty("data_nasc")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private String dataNascimento;
    private String sexo;
    private String mae;
    private String pai;
    private String email;
    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    @JsonProperty("telefone_fixo")
    private String telefone;
    private String celular;
    private String altura;
    private String peso;
    @JsonProperty("tipo_sanguineo")
    private String tipoSanguineo;
    
}
