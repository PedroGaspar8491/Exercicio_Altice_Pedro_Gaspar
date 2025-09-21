package org.acme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.WebApplicationException;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

class LabsecResourceTest {
    private LabsecResource resource;

    @BeforeEach
    void setUp() {
        resource = new LabsecResource();

    }

    @Test
    void baseCaseTest() {
        assertEquals(resource.getLabsec(0), "0");
        assertEquals(resource.getLabsec(1), "1");
        assertEquals(resource.getLabsec(2), "0");
        assertEquals(resource.getLabsec(3), "1");
    }

    @Test
    void valuesAreCorrectTest() {
        assertEquals("1", resource.getLabsec(4));
        assertEquals("1", resource.getLabsec(5));
        assertEquals("1", resource.getLabsec(6));
        assertEquals("2", resource.getLabsec(7));
        assertEquals("2", resource.getLabsec(8));
        assertEquals("2", resource.getLabsec(9));
        assertEquals("3", resource.getLabsec(10));
        assertEquals("4", resource.getLabsec(11));
        assertEquals("4", resource.getLabsec(12));
        assertEquals("5", resource.getLabsec(13));
    }

    @Test
    void largerValuesAreCorrect() {
        assertEquals("8", resource.getLabsec(15));
        assertEquals("21", resource.getLabsec(20));
        assertEquals("59", resource.getLabsec(25));
    }

    @Test
    void invalidInputThrowsTest() {
        var ex = assertThrows(
                WebApplicationException.class,
                () -> resource.getLabsec(-5));
        assertEquals(400, ex.getResponse().getStatus());
    }
}