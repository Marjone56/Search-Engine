import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class SearchEngineTest {
    // TODO: accuracy tests + 1 efficiency test to make sure both hashtables build in under a minute

    @Test
    public void build() throws IOException {




        long startTime2 = System.nanoTime();
        SearchEngine chaining = new SearchEngine(6);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);
        duration2 /= 1000000000;
        System.out.println(duration2 + " seconds");

        assertTrue(duration2 <= 60);

        // cannot get this to do under 1 min
        long startTime = System.nanoTime();
        SearchEngine openAdd = new SearchEngine(5);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        duration /= 1000000000;
        System.out.println(duration + " seconds");

        assertTrue(duration <= 60);


    }

}