package com.salesianos.triana.appbike.controller;

import com.salesianos.triana.appbike.dto.Bike.GetBicicletaDTO;
import com.salesianos.triana.appbike.dto.Uso.UsoResponse;
import com.salesianos.triana.appbike.model.Usuario;
import com.salesianos.triana.appbike.service.UsoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/use")
@Tag(name = "Uses", description = "Controller who manages all requests regarding uses")
public class UsoController {

    private final UsoService usoService;

    @Operation(summary = "Gets a use from its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The use has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "fechaInicio": "2023-11-26T10:31:40.804104",
                                        "fechaFin": null,
                                        "coste": 0.0,
                                        "bicicleta": "Pacote",
                                        "estacionFin": "El viaje aun no tiene estación de fin",
                                        "usuario": "5b08c955-1463-43d5-8326-8b511663e848"
                                    }
   
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any use", content = @Content),
    })
    @GetMapping("/{id}")
    public UsoResponse findById (@PathVariable Long id){
        return UsoResponse.of(usoService.findById(id));
    }

    @Operation(summary = "Gets an active use by an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The use has been found", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsoResponse.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "fechaInicio": "2023-11-26T10:31:40.804104",
                                        "fechaFin": null,
                                        "coste": 0.0,
                                        "bicicleta": "Pacote",
                                        "estacionFin": "El viaje aun no tiene estación de fin",
                                        "usuario": "5b08c955-1463-43d5-8326-8b511663e848"
                                    }
                                                                        """) }) }),
            @ApiResponse(responseCode = "404", description = "Not found any active use", content = @Content),
    })
    @GetMapping("/active")
    public UsoResponse findActiveUse (@AuthenticationPrincipal Usuario usuario){
        return UsoResponse.of(usoService.findActiveUseByUser(usuario.getId()));
    }
}