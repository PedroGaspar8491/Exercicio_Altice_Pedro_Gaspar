package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/labsec")
public class LabsecResource {
    private Map<Integer, BigInteger> map = new HashMap<>();

    public LabsecResource() {
        // Popula o mapa com os casos base
        map.put(0, BigInteger.ZERO);
        map.put(1, BigInteger.ONE);
        map.put(2, BigInteger.ZERO);
        map.put(3, BigInteger.ONE);
    }

    @Path("/{n}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponses({
            @APIResponse(responseCode = "200", description = "The n-th Labsec number", content = @Content(schema = @Schema(type = SchemaType.STRING))),
            @APIResponse(responseCode = "400", description = "Invalid input, n must be a positive integer")
    })
    public String getLabsec(@PathParam("n") int n) {
        return labsec(n).toString();
    }

    private BigInteger labsec(int n) {

        // Verifica se o input é válido
        if (n < 0) {
            throw new WebApplicationException("Numbers must be positive Integers.", Response.Status.BAD_REQUEST);
        }

        // Verifica se o valor para n já foi gravado em cache
        if (map.containsKey(n)) {
            return map.get(n);
        }

        // Determina o ponto de partida para o cálculo
        int start = map.keySet().stream().max(Integer::compareTo).orElse(3);

        // Calcula e grava cada valor da sequência até n
        for (int i = start + 1; i <= n; i++) {
            BigInteger value = map.get(i - 4).add(map.get(i - 3));
            map.put(i, value);
        }

        return map.get(n);
    }
}
