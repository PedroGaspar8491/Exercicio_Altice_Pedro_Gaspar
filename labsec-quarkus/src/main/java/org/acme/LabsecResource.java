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

    @Path("/{n}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponses({
            @APIResponse(responseCode = "200", description = "The n-th Labsec number", content = @Content(schema = @Schema(type = SchemaType.STRING))),
            @APIResponse(responseCode = "400", description = "Invalid input, n must be a positive integer")
    })
    public String getLabsec(@PathParam("n") int n) {

        // Verifica se o valor para n já foi gravado em cache
        if (map.containsKey(n)) {
            return map.get(n).toString();
        }
        return labsec(n).toString();
    }

    private BigInteger labsec(int n) {
        // Popula o mapa com os casos base, caso estes não existam
        if (!map.containsKey(0) || !map.containsKey(1) || !map.containsKey(2) || !map.containsKey(3)) {
            map.put(0, BigInteger.ZERO);
            map.put(1, BigInteger.ONE);
            map.put(2, BigInteger.ZERO);
            map.put(3, BigInteger.ONE);
        }

        // Verifica se o input é válido
        if (n < 0) {
            throw new WebApplicationException("Numbers must be positive Integers.", Response.Status.BAD_REQUEST);
        }

        // Retorna os casos base quando forem pedidos
        if (n == 0 || n == 1 || n == 2 || n == 3) {
            return map.get(n);
        }

        // No caso de não haver valores guardados em caching, começa nos casos base
        BigInteger a = map.get(0), b = map.get(1), temp;
        int latest = 3;

        // Obtem o par de valores mais proximos de n de acordo com a sequencia ( a =
        // n-4, b = n-3)
        for (int i = n; i >= 3; i--) {
            if (map.containsKey(i)) {
                latest = i;
                a = map.get(i - 3);
                b = map.get(i - 2);
                break;
            }
        }

        // Calcula e grava cada valor da sequência até n
        for (int i = latest + 1; i <= n; i++) {
            temp = a.add(b);
            a = b;
            b = temp;
            map.put(i, b);
        }

        return b;
    }
}
