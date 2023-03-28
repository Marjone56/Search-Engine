import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HTCTest {
    // TODO: accuracy tests

    HashTableWithChaining<String, String> hashTableWithChaining = new HashTableWithChaining<>();
    @Test
    public void insert(){
        hashTableWithChaining.put("Test1", "test1");
        hashTableWithChaining.put("Test2", "test2");
        hashTableWithChaining.put("Test3", "test3");
        hashTableWithChaining.put("Test4", "test4");
        hashTableWithChaining.put("Test5", "test5");
        hashTableWithChaining.put("Test6", "test6");
        hashTableWithChaining.put("Test7", "test7");
        hashTableWithChaining.put("Test8", "test8");
        hashTableWithChaining.put("Test9", "test9");
        hashTableWithChaining.put("Test10", "test10");
        hashTableWithChaining.put("Test11", "test11");
        hashTableWithChaining.put("Test12", "test12");
        hashTableWithChaining.put("Test13", "test13");
        hashTableWithChaining.put("Test14", "test14");
        hashTableWithChaining.put("Test15", "test15");

    }

    @Test
    public void get(){
        insert();
        assertEquals("test4", hashTableWithChaining.get("Test4"));
        assertEquals("test15", hashTableWithChaining.get("Test15"));
        assertEquals("test7", hashTableWithChaining.get("Test7"));

        assertEquals(null, hashTableWithChaining.get("Test20"));
        assertEquals(null, hashTableWithChaining.get("TestNone"));
        assertEquals(null, hashTableWithChaining.get("Random"));

    }

    @Test
    public void containsKey(){
        insert();

        assertTrue(hashTableWithChaining.containsKey("Test4"));
        assertTrue(hashTableWithChaining.containsKey("Test15"));
        assertTrue(hashTableWithChaining.containsKey("Test7"));

        assertFalse(hashTableWithChaining.containsKey("Test20"));
        assertFalse(hashTableWithChaining.containsKey("TestNone"));
        assertFalse(hashTableWithChaining.containsKey("Random"));
    }

    @Test
    public void delete(){
        insert();

        assertTrue(hashTableWithChaining.remove("Test4"));
        assertTrue(hashTableWithChaining.remove("Test15"));
        assertTrue(hashTableWithChaining.remove("Test7"));
        assertEquals(null, hashTableWithChaining.get("Test4"));
        assertEquals(null, hashTableWithChaining.get("Test15"));
        assertEquals(null, hashTableWithChaining.get("Test7"));

        assertFalse(hashTableWithChaining.remove("Test20"));
        assertFalse(hashTableWithChaining.remove("TestNone"));
        assertFalse(hashTableWithChaining.remove("Random"));

    }



}
