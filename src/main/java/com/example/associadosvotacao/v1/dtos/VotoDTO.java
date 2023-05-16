package com.example.associadosvotacao.v1.dtos;

import com.example.associadosvotacao.v1.model.Associado;
import com.example.associadosvotacao.v1.model.SessaoVotacao;
import com.example.associadosvotacao.v1.model.Voto;
import com.example.associadosvotacao.v1.model.enums.OpcaoVotoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VotoDTO extends BaseDto<Voto>{
    @ApiModelProperty(required = true, example = "1")
    private Long id;
    @NotEmpty
    @NotNull
    @ApiModelProperty(required = true, example = "1")
    private SessaoVotacao sessaoVotacao;
    @NotEmpty
    @NotNull
    @ApiModelProperty(required = true, example = "1")
    private Associado associado;

    @NotEmpty
    @NotNull
    @ApiModelProperty(required = true, example = "SIM")
    private OpcaoVotoEnum voto;

    @Override
    public Voto convertToModel() {
        return convertToModel(new Voto());
    }

    @Override
    public Voto convertToModel(Voto voto) {
        voto.setId(this.getId());
        voto.setSessaoVotacao(this.getSessaoVotacao());
        voto.setAssociado(this.getAssociado());
        voto.setData(LocalDateTime.now());
        voto.setVoto(this.getVoto());
        return voto;
    }

    @JsonIgnoreProperties({"id","ativo"})
    @ApiModel(value = "VotoModel")
    public static class SaveModel extends VotoDTO {}

}
