package br.com.ghdpreto.nlwunitejavapassin.config.attendee;


import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeBadgeResponseDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.general.ErrorResponseDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.general.ErrorValidationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(description = "Essa função é responsável por listar o cracha do participante")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = AttendeeBadgeResponseDTO.class))
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Requisição inválida",
                content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ErrorValidationResponseDTO.class)))),
        @ApiResponse(
                responseCode = "404",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(
                responseCode = "500",
                description = "Erro interno do servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),

})
public @interface GetAttendeeBadgeSwagger {
}
