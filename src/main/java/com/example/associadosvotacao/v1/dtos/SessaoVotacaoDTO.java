package com.example.associadosvotacao.v1.dtos;

import com.example.associadosvotacao.v1.model.Pauta;
import com.example.associadosvotacao.v1.model.SessaoVotacao;
import com.example.associadosvotacao.v1.model.enums.OpcaoVoto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacaoDTO extends BaseDto<SessaoVotacao>{
    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @ApiModelProperty(required = true, example = "Votação para novo presidente da empresa")
    private String descricao;

    @NotNull @NotEmpty
    @ApiModelProperty(required = true, example = "25")
    private Pauta pauta;

    @NotNull @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @ApiModelProperty(required = true, example = "2023-05-13T15:40:28.593Z")
    private LocalDateTime inicio;

    @NotNull @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @ApiModelProperty(required = true, example = "2023-05-13T15:40:28.593Z")
    private LocalDateTime termino;

    @NotNull @NotEmpty
    @ApiModelProperty(required = true, example = "2")
    private Long associadoId;

    @Override
    public SessaoVotacao convertToModel() {
        return convertToModel(new SessaoVotacao());
    }

    @Override
    public SessaoVotacao convertToModel(SessaoVotacao sessaoVotacao) {
        sessaoVotacao.setId(this.getId());
        sessaoVotacao.setTotalVotos(0);
        sessaoVotacao.setDescricao(this.getDescricao());
        sessaoVotacao.setPauta(this.getPauta());
        sessaoVotacao.setAssociadoId(this.getAssociadoId());
        sessaoVotacao.setInicio(this.getInicio());
        sessaoVotacao.setTermino(this.getTermino());
        return sessaoVotacao;
    }
}
