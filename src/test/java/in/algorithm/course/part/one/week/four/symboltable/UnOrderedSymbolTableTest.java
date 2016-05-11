package in.algorithm.course.part.one.week.four.symboltable;

import in.algorithm.course.util.RandomInt;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class UnOrderedSymbolTableTest {


    private SymbolTable<String, Integer> symbolTable;
    private int size;

    @Before
    public void setUp() throws Exception {
        size = RandomInt.next(100, 1000);
        symbolTable = UnOrderedSymbolTable.createNew();

    }

    @Test
    public void shouldGetValuesHaveBeenPut() throws Exception {

        for (int i = 0; i < size; i++) {
            symbolTable.put(Integer.toString(i), Integer.valueOf(i));
        }


        for (int i = 0; i < size; i++) {
            assertEquals(Integer.valueOf(i), symbolTable.get(Integer.toString(i)));
        }

    }

    @Test
    public void shouldUpdateExistingValueOnPut() throws Exception {

        for (int i = 0; i < size; i++) {
            symbolTable.put(Integer.toString(i), Integer.valueOf(i));
        }

        for (int i = 0; i < size; i++) {
            symbolTable.put(Integer.toString(i), Integer.valueOf(i * 2));
        }

        for (int i = 0; i < size; i++) {
            assertEquals(Integer.valueOf(i * 2), symbolTable.get(Integer.toString(i)));
        }

    }


    @Test
    public void shouldDeleteKeys() throws Exception {

        for (int i = 0; i < size; i++) {
            symbolTable.put(Integer.toString(i), Integer.valueOf(i));
        }

        for (int i = 0; i < size; i++) {
            assertEquals(Integer.valueOf(i), symbolTable.get(Integer.toString(i)));
            symbolTable.remove(Integer.toString(i));
            assertNull(symbolTable.get(Integer.toString(i)));
        }
    }

    @Test
    public void shouldCheckIsEmpty() throws Exception {
        assertTrue(symbolTable.isEmpty());

        final Integer random = RandomInt.next();
        symbolTable.put(random.toString(), random);
        assertFalse(symbolTable.isEmpty());

        symbolTable.remove(random.toString());
        assertTrue(symbolTable.isEmpty());
    }

    @Test
    public void shouldCheckIfContains() throws Exception {

        for (int i = 0; i < size; i++) {
            symbolTable.put(Integer.toString(i), Integer.valueOf(i));
            assertTrue(symbolTable.contains(Integer.toString(i)));
        }

        final Integer unknown = RandomInt.next(size, 100000);
        assertFalse(symbolTable.contains(unknown.toString()));
    }

    @Test
    public void shouldGetSizeAsNumberOfKeys() throws Exception {
        assertEquals(0, symbolTable.size());
        for (int i = 0; i < size; i++) {
            symbolTable.put(Integer.toString(i), Integer.valueOf(i));
            assertEquals(i+1, symbolTable.size());
        }

        for (int i = 0; i < size; i++) {
            symbolTable.remove(Integer.toString(i));
            assertEquals(size - i - 1, symbolTable.size());
        }
    }

    @Test
    public void shouldGetAllKeys() throws Exception {
        Iterator<String> keys = symbolTable.keys();
        assertFalse(keys.hasNext());

        final List<String> keysInserted = new ArrayList();
        for (int i = 0; i < size; i++) {
            final Integer value = i;
            keysInserted.add(value.toString());
            symbolTable.put(value.toString(), value);
        }

        keys = symbolTable.keys();
        final List<String> keysIterated = new ArrayList();
        while (keys.hasNext()) {
            keysIterated.add(keys.next());
        }

        assertEquals(keysInserted.size(), keysIterated.size());
        assertTrue(keysInserted.containsAll(keysIterated));
        assertTrue(keysIterated.containsAll(keysInserted));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToPutNull() throws Exception {
        symbolTable.put(null, RandomInt.next());
    }
}
