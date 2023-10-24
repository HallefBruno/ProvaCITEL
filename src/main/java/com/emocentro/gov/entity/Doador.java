package com.emocentro.gov.entity;

import com.emocentro.gov.dto.ImcMedioDto;
import com.emocentro.gov.dto.MediaIdadeSanguineoDto;
import com.emocentro.gov.dto.ObesosHomemMulherDto;
import com.emocentro.gov.dto.QuantidadeCandidatosPorEstadoDto;
import com.emocentro.gov.dto.QuantidadeDoadoresDto;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import static org.apache.commons.lang3.StringUtils.getDigits;

@SqlResultSetMapping(
    name = "QuantidadeCandidatosPorEstadoDto",
    classes = {
        @ConstructorResult(
            targetClass = QuantidadeCandidatosPorEstadoDto.class,
            columns = {
                @ColumnResult(name = "total", type = Integer.class),
                @ColumnResult(name = "estado", type = String.class)
            }
        )
    }
)
@NamedNativeQuery(
    name = "QuantidadeCandidatosPorEstadoDto",
    query = "SELECT COUNT(doador.estado) as total, doador.estado FROM doador Group by doador.estado Order by total",
    resultSetMapping = "QuantidadeCandidatosPorEstadoDto"
)

@SqlResultSetMapping(
    name = "ImcMedioDto",
    classes = {
        @ConstructorResult(
            targetClass = ImcMedioDto.class,
            columns = {
                @ColumnResult(name = "faixa_idade", type = String.class),
                @ColumnResult(name = "imc_medio", type = Double.class)
            }
        )
    }
)
@NamedNativeQuery(
    name = "ImcMedioDto",
    query = """
        SELECT
            CASE
                WHEN date_part('year', age(current_date, doador.data_nasc)) BETWEEN 0 AND 10 THEN '0-10 anos'
                WHEN date_part('year', age(current_date, doador.data_nasc)) BETWEEN 11 AND 20 THEN '11-20 anos'
                WHEN date_part('year', age(current_date, doador.data_nasc)) BETWEEN 21 AND 30 THEN '21-30 anos'
                WHEN date_part('year', age(current_date, doador.data_nasc)) BETWEEN 31 AND 40 THEN '31-40 anos'
                WHEN date_part('year', age(current_date, doador.data_nasc)) BETWEEN 41 AND 50 THEN '41-50 anos'
                ELSE '51+ anos'
            END AS faixa_idade,
            ROUND(AVG(CAST(peso as numeric) / (CAST(altura as numeric) * cast(altura as numeric)))) AS imc_medio
        FROM doador
        GROUP BY faixa_idade
        ORDER BY MIN(date_part('year', age(current_date, doador.data_nasc)));
        """,
    resultSetMapping = "ImcMedioDto"
)


@SqlResultSetMapping(
    name = "ObesosHomemMulherDto",
    classes = {
        @ConstructorResult(
            targetClass = ObesosHomemMulherDto.class,
            columns = {
                @ColumnResult(name = "sexo", type = String.class),
                @ColumnResult(name = "obesos", type = Integer.class),
                @ColumnResult(name = "total", type = Integer.class),
                @ColumnResult(name = "percentual_obesos", type = Double.class)
            }
        )
    }
)
@NamedNativeQuery(
    name = "ObesosHomemMulherDto",
    query = """
        SELECT
            sexo,
            COUNT(*) FILTER (WHERE imc > 30) AS obesos,
            COUNT(*) AS total,
            ROUND(CAST(COUNT(*) FILTER (WHERE imc > 30) AS numeric) / CAST(COUNT(*) AS numeric) * 100) AS percentual_obesos
        FROM (
            SELECT
                sexo,
                CAST(peso AS numeric) / (CAST(altura AS numeric) * CAST(altura AS numeric)) AS imc
            FROM doador
        ) AS calculado_imc
        GROUP BY sexo;
        """,
    resultSetMapping = "ObesosHomemMulherDto"
)

@SqlResultSetMapping(
    name = "MediaIdadeSanguineoDto",
    classes = {
        @ConstructorResult(
            targetClass = MediaIdadeSanguineoDto.class,
            columns = {
                @ColumnResult(name = "tipo_sanguineo", type = String.class),
                @ColumnResult(name = "media_idade", type = Integer.class),
            }
        )
    }
)
@NamedNativeQuery(
    name = "MediaIdadeSanguineoDto",
    query = """
        SELECT
            tipo_sanguineo,
            ROUND(AVG(EXTRACT(YEAR FROM age(CURRENT_DATE, data_nasc)))) AS media_idade
        FROM doador
        GROUP BY tipo_sanguineo;
        """,
    resultSetMapping = "MediaIdadeSanguineoDto"
)


@SqlResultSetMapping(
    name = "QuantidadeDoadoresDto",
    classes = {
        @ConstructorResult(
            targetClass = QuantidadeDoadoresDto.class,
            columns = {
                @ColumnResult(name = "receptor", type = String.class),
                @ColumnResult(name = "quantidade_doadores", type = Integer.class),
            }
        )
    }
)
@NamedNativeQuery(
    name = "QuantidadeDoadoresDto",
    query = """
        SELECT
            receptor.tipo_sanguineo AS receptor,
            COUNT(doadores.nome) AS quantidade_doadores
        FROM doador AS receptor
        LEFT JOIN doador AS doadores ON (
            (receptor.tipo_sanguineo = 'A+' AND doadores.tipo_sanguineo IN ('A+', 'O+') AND date_part('year', age(current_date, doadores.data_nasc)) >= 16 AND date_part('year', age(current_date, doadores.data_nasc)) <= 69 AND CAST(doadores.peso as numeric) > 50) OR
            (receptor.tipo_sanguineo = 'A-' AND doadores.tipo_sanguineo IN ('A+', 'A-', 'O+', 'O-') AND date_part('year', age(current_date, doadores.data_nasc)) >= 16 AND date_part('year', age(current_date, doadores.data_nasc)) <= 69 AND CAST(doadores.peso as numeric) > 50) OR
            (receptor.tipo_sanguineo = 'B+' AND doadores.tipo_sanguineo IN ('B+', 'O+') AND date_part('year', age(current_date, doadores.data_nasc)) >= 16 AND date_part('year', age(current_date, doadores.data_nasc)) <= 69 AND CAST(doadores.peso as numeric) > 50) OR
            (receptor.tipo_sanguineo = 'B-' AND doadores.tipo_sanguineo IN ('B+', 'B-', 'O+', 'O-') AND date_part('year', age(current_date, doadores.data_nasc)) >= 16 AND date_part('year', age(current_date, doadores.data_nasc)) <= 69 AND CAST(doadores.peso as numeric) > 50) OR
            (receptor.tipo_sanguineo = 'AB+' AND doadores.tipo_sanguineo IN ('A+', 'A-', 'B+', 'B+', 'AB+', 'O+', 'O-') AND date_part('year', age(current_date, doadores.data_nasc)) >= 16 AND date_part('year', age(current_date, doadores.data_nasc)) <= 69 AND CAST(doadores.peso as numeric) > 50) OR
            (receptor.tipo_sanguineo = 'AB-' AND doadores.tipo_sanguineo IN ('A-', 'B-', 'AB-', 'O-') AND date_part('year', age(current_date, doadores.data_nasc)) >= 16 AND date_part('year', age(current_date, doadores.data_nasc)) <= 69 AND CAST(doadores.peso as numeric) > 50) OR
            (receptor.tipo_sanguineo = 'O+' AND doadores.tipo_sanguineo IN ('O+', 'O-') AND date_part('year', age(current_date, doadores.data_nasc)) >= 16 AND date_part('year', age(current_date, doadores.data_nasc)) <= 69 AND CAST(doadores.peso as numeric) > 50) OR
            (receptor.tipo_sanguineo = 'O-' AND doadores.tipo_sanguineo = 'O-' AND date_part('year', age(current_date, doadores.data_nasc)) >= 16 AND date_part('year', age(current_date, doadores.data_nasc)) <= 69 AND CAST(doadores.peso as numeric) > 50)
        )
        GROUP BY receptor.tipo_sanguineo;
        """,
    resultSetMapping = "QuantidadeDoadoresDto"
)


@Data
@Entity
public class Doador implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 255)
    private String nome;
    
    @Column(length = 13)
    private String cpf;
    
    @Column(length = 15)
    private String rg;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nasc")
    private LocalDate dataNascimento;
    
    @Column(length = 11)
    private String sexo;
    
    @Column(length = 255)
    private String mae;
    
    @Column(length = 255)
    private String pai;
    
    @Column(length = 255)
    private String email;
    
    @Column(length = 12)
    private String cep;
    
    @Column(length = 255)
    private String endereco;
    
    private Integer numero;
    
    @Column(length = 255)
    private String bairro;
    
    @Column(length = 255)
    private String cidade;
    
    @Column(length = 2)
    private String estado;
    
    @Column(name = "telefone_fixo", length = 14)
    private String telefone;
    
    @Column(length = 14)
    private String celular;
    
    @Column(length = 5)
    private String altura;
    
    @Column(length = 3)
    private String peso;
    
    @Column(name = "tipo_sanguineo", length = 5)
    private String tipoSanguineo;
    
    @PrePersist
    private void prePersist() {
        this.cpf = getDigits(this.cpf);
        this.rg = getDigits(this.rg);
        this.cep = getDigits(this.cep);
        this.telefone = getDigits(this.telefone);
        this.celular = getDigits(this.celular);
    }
    
}
