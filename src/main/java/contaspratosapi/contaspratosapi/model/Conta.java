package contaspratosapi.contaspratosapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer codigo;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    public LocalDate data;

    @Column(nullable = false)
    public Double valor;
}
