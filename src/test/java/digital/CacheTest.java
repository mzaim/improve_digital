package digital;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

class CacheTest {

    Cache cache = Cache.getInstance();

    @Test
    void put() {
        cache.clear();
        cache.put("word1", "file1");
        cache.put("word2", "file1");
        cache.put("word3", "file1");
        cache.put("word3", "file1");
        cache.put("word3", "file1");
        cache.put("word4", "file2");
        cache.put("word5", "file2");
        cache.put("word1", "file2");
        cache.put("word1", "file2");
        cache.put("word2", "file2");

        Map actual = cache.getContent();

        Map<String, Map<String, Integer>> expected = new HashMap<>();
        Map<String,Integer> w1 = new HashMap<>();
        Map<String,Integer> w2 = new HashMap<>();
        Map<String,Integer> w3 = new HashMap<>();
        Map<String,Integer> w4 = new HashMap<>();
        Map<String,Integer> w5 = new HashMap<>();
        w1.put("file1", 1);
        w2.put("file1", 1);
        w2.put("file1", 1);
        w3.put("file1", 3);
        w4.put("file2", 1);
        w5.put("file2", 1);
        w1.put("file2", 2);
        w2.put("file2", 1);
        expected.put("word1", w1);
        expected.put("word2", w2);
        expected.put("word3", w3);
        expected.put("word4", w4);
        expected.put("word5", w5);

        assertThat(actual, Matchers.is(expected));
    }

    @Test
    void getContent() {
    }
}