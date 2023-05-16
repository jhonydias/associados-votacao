package com.example.associadosvotacao.v1.dtos;

import com.example.associadosvotacao.v1.model.Associado;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDTO extends BaseDto<Associado>{
    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @NotEmpty
    @NotNull
    @ApiModelProperty(required = true, example = "login")
    private String login;

    @NotEmpty
    @NotNull
    @ApiModelProperty(required = true, example = "senha")
    private String senha;

    @NotEmpty
    @NotNull
    @ApiModelProperty(required = true, example = "fulano de tal")
    private String nome;

    @NotEmpty
    @NotNull
    @ApiModelProperty(required = true, example = "123456789")
    private String cpf;

    @Override
    public Associado convertToModel() {
        return convertToModel(new Associado());
    }

    @Override
    public Associado convertToModel(Associado associado) {
        associado.setId(this.getId());
        associado.setLogin(this.getLogin());
        associado.setSenha(BCrypt.hashpw(this.getSenha(), BCrypt.gensalt()));
        associado.setNome(this.getNome());
        associado.setCpf(this.getCpf());
        return associado;
    }

}
