import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HTOATest {
    // TODO: accuracy tests

    HashTableOpenAddressing<String, String> hashTableOpenAddressing = new HashTableOpenAddressing<>(1, 11, 0.75);
    HashTableOpenAddressing<Integer, Integer> intTable = new HashTableOpenAddressing<>(1, 11, 0.75);

    HashTableOpenAddressing<Integer, Integer> dubHash = new HashTableOpenAddressing<>(3, 23, 0.75);

    @Test
    public void insert(){
        dubHash.put(803, 803803);
        dubHash.put(531, 531531);
        dubHash.put(918, 918918); // needs to be in index 11; 23 - 21 = 2 tho? 2 is taken, now what?

        // expected: <{0: ;1: ;2: 531=531531,;3: ;4: 763=763763,;5: ;6: ;7: ;8: ;9: ;10: 56=5656,;11: 918=918918,;12: 58=5858,;13: 841=841841,;14: ;15: 222=222222,;16: ;17: ;18: ;19: ;20: 227=227227,;21: 803=803803,;22:}
        hashTableOpenAddressing.put("Test1", "test1");
        hashTableOpenAddressing.put("Test2", "test2");
        hashTableOpenAddressing.put("Test3", "test3");
        hashTableOpenAddressing.put("Test4", "test4");
        hashTableOpenAddressing.put("Test5", "test5");
        hashTableOpenAddressing.put("Test6", "test6");
        hashTableOpenAddressing.put("Test7", "test7");
        hashTableOpenAddressing.put("Test8", "test8");
        hashTableOpenAddressing.put("Test9", "test9");
        hashTableOpenAddressing.put("Test10", "test10");
        hashTableOpenAddressing.put("Test11", "test11");
        hashTableOpenAddressing.put("Test12", "test12");
        hashTableOpenAddressing.put("Test13", "test13");
        hashTableOpenAddressing.put("Test14", "test14");
        hashTableOpenAddressing.put("Test15", "test15");
        System.out.println(hashTableOpenAddressing);

    }

    @Test
    public void insertInt(){
        intTable.put(716, 716716);
        intTable.put(926, 926926);
        intTable.put(57, 5757);
        intTable.put(123, 123123);
        intTable.put(75, 7575);
        intTable.put(104, 104104);
        intTable.put(521, 521521);
        intTable.put(112, 112112);
        intTable.put(757, 757757);
    }




    @Test
    public void get(){
        insert();
        assertEquals("test4", hashTableOpenAddressing.get("Test4"));
        assertEquals("test15", hashTableOpenAddressing.get("Test15"));
        assertEquals("test7", hashTableOpenAddressing.get("Test7"));

        assertEquals(null, hashTableOpenAddressing.get("Test20"));
        assertEquals(null, hashTableOpenAddressing.get("TestNone"));
        assertEquals(null, hashTableOpenAddressing.get("Random"));

    }

    @Test
    public void containsKey(){
        insert();

        assertTrue(hashTableOpenAddressing.containsKey("Test4"));
        assertTrue(hashTableOpenAddressing.containsKey("Test15"));
        assertTrue(hashTableOpenAddressing.containsKey("Test7"));

        assertFalse(hashTableOpenAddressing.containsKey("Test20"));
        assertFalse(hashTableOpenAddressing.containsKey("TestNone"));
        assertFalse(hashTableOpenAddressing.containsKey("Random"));
    }

    @Test
    public void delete(){
        insert();

        assertTrue(hashTableOpenAddressing.remove("Test4"));
        assertTrue(hashTableOpenAddressing.remove("Test15"));
        assertTrue(hashTableOpenAddressing.remove("Test7"));
        assertEquals(null, hashTableOpenAddressing.get("Test4"));
        assertEquals(null, hashTableOpenAddressing.get("Test15"));
        assertEquals(null, hashTableOpenAddressing.get("Test7"));

        assertFalse(hashTableOpenAddressing.remove("Test20"));
        assertFalse(hashTableOpenAddressing.remove("TestNone"));
        assertFalse(hashTableOpenAddressing.remove("Random"));

    }


}
