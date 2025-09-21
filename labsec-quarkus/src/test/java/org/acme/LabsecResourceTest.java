package org.acme;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LabsecResourceTest {
    private Map<Integer, BigInteger> map = new HashMap<>();

    private BigInteger labsec(int n){
        // Popula o mapa com os casos base, caso estes não existam
        if (!map.containsKey(0) || !map.containsKey(1) || !map.containsKey(2) || !map.containsKey(3)){
            map.put(0, BigInteger.ZERO);
            map.put(1, BigInteger.ONE);
            map.put(2, BigInteger.ZERO);
            map.put(3, BigInteger.ONE);
        }

        if (n < 0){
            throw new IllegalArgumentException("Numbers must be positive Integers.");
        }

        // Retorna os casos base quando forem pedidos
        if (n == 0 || n ==1 || n ==2 || n ==3){
            return map.get(n);
        }

        // No caso de não haver valores guardados em caching, começa nos casos base
        BigInteger a = map.get(0), b = map.get(1), temp;
        int latest = 3;

        // Obtem o par de valores mais proximos de n de acordo com a sequencia ( a = n-4, b = n-3)
        for (int i = n; i >= 3; i--){
            if (map.containsKey(i)){
                latest = i;
                a = map.get(i-3);
                b = map.get(i-2);
                break;
            }
        }

        // Calcula e grava cada valor da sequência até n
        for (int i = latest + 1; i <= n; i++){
            temp = a.add(b);
            a = b;
            b = temp;
            map.put(i, b);
        }

        return b;
    }

    @BeforeEach
    void setUp() {
        labsec(10);
    }

    @AfterEach
    void tearDown() {
        map.clear();
    }

    @Test
    void baseCaseTest() {
        assertEquals(labsec(0), BigInteger.ZERO);
        assertEquals(labsec(1), BigInteger.ONE);
    }

    @Test
    void mapStoresCorrectValuesTest() {
        assertEquals(map.get(0), labsec(0));
        assertEquals(map.get(1), labsec(1));
    }

    @Test
    void formerItemsAreStoredTest() {
        assertTrue(map.containsKey(2));
        assertTrue(map.containsKey(3));
        assertTrue(map.containsKey(4));
        assertTrue(map.containsKey(5));
        assertTrue(map.containsKey(6));
        assertTrue(map.containsKey(7));
        assertTrue(map.containsKey(8));
        assertTrue(map.containsKey(9));
    }

    @Test
    void valuesAreCorrectTest() {
        assertEquals(BigInteger.valueOf(21), map.get(10));
        assertEquals(BigInteger.valueOf(13), map.get(9));
    }

    @Test
    void cacheIsUsedTest() {
        int sizeBefore = map.size();
        labsec(10);
        assertEquals(sizeBefore, map.size());
    }

    @Test
    void largeValueTest() {
        BigInteger result = labsec(50);
        assertEquals(new BigInteger("1836311903"), result);
    }

    @Test
    void invalidInputThrowsTest() {
        assertThrows(IllegalArgumentException.class, () -> labsec(-5));
    }
}