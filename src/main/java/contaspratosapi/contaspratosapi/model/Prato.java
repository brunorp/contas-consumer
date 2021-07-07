package contaspratosapi.contaspratosapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Prato {
    public Integer codigo;
    public String nome;
    public String ingredientes;
    public Double valor;
    public boolean vendido;
}
