package com.transport.reservation_service.controller;

import com.transport.reservation_service.dto.CreateSeatRequest;
import com.transport.reservation_service.dto.SeatResponse;
import com.transport.reservation_service.dto.UpdateSeatRequest;
import com.transport.reservation_service.service.SiegeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@Tag(name = "Sièges", description = "API de gestion des sièges de transport")
public class SeatController {

    private final SiegeService seatService;

    public SeatController(SiegeService seatService) {
        this.seatService = seatService;
    }

    @Operation(summary = "Créer un siège", description = "Ajoute un nouveau siège")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Siège créé avec succès",
                     content = @Content(schema = @Schema(implementation = SeatResponse.class))),
        @ApiResponse(responseCode = "400", description = "Erreur de validation")
    })
    @PostMapping
    public ResponseEntity<?> createSeat(@RequestBody CreateSeatRequest request) {
        try {
            return ResponseEntity.ok(seatService.createSeat(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Mettre à jour un siège")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Siège mis à jour"),
        @ApiResponse(responseCode = "404", description = "Siège non trouvé")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeat(
            @PathVariable Long id,
            @RequestBody UpdateSeatRequest request
    ) {
        try {
            return ResponseEntity.ok(seatService.updateSeat(id, request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Supprimer un siège")
    @ApiResponse(responseCode = "204", description = "Suppression effectuée")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeat(
            @Parameter(description = "ID du siège", example = "1") @PathVariable Long id
    ) {
        try {
            seatService.deleteSeat(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Obtenir un siège par ID")
    @ApiResponse(responseCode = "200", description = "Détails du siège")
    @GetMapping("/{id}")
    public ResponseEntity<?> getSeatById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(seatService.getSeatById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Lister tous les sièges")
    @ApiResponse(responseCode = "200", description = "Liste complète des sièges")
    @GetMapping
    public ResponseEntity<List<SeatResponse>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

}
