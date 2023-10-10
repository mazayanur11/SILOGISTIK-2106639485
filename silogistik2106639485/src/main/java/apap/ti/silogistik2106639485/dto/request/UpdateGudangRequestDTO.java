package apap.ti.silogistik2106639485.dto.request;

import apap.ti.silogistik2106639485.model.GudangBarang;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO {
    @NotNull
    private Long id;

    private List<GudangBarang> listGudangBarang;
}