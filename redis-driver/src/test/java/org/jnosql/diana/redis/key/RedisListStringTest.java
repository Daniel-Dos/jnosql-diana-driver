/*
 *  Copyright (c) 2017 Otávio Santana and others
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 */

package org.jnosql.diana.redis.key;

import org.jnosql.diana.api.key.BucketManagerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class RedisListStringTest {


    private static final String FRUITS = "fruits-string";

    private BucketManagerFactory keyValueEntityManagerFactory;

    private List<String> fruits;

    @Before
    public void init() {
        keyValueEntityManagerFactory = RedisTestUtils.get();
        fruits = keyValueEntityManagerFactory.getList(FRUITS, String.class);
    }

    @Test
    public void shouldReturnsList() {
        assertNotNull(fruits);
    }

    @Test
    public void shouldAddList() {
        assertTrue(fruits.isEmpty());
        fruits.add("banana");
        assertFalse(fruits.isEmpty());
        String banana = fruits.get(0);
        assertNotNull(banana);
        assertEquals(banana, "banana");
    }

    @Test
    public void shouldSetList() {

        fruits.add("banana");
        fruits.add(0, "orange");
        assertTrue(fruits.size() == 2);

        assertEquals(fruits.get(0), "orange");
        assertEquals(fruits.get(1), "banana");

        fruits.set(0, "waterMelon");
        assertEquals(fruits.get(0), "waterMelon");
        assertEquals(fruits.get(1), "banana");

    }

    @Test
    public void shouldRemoveList() {
        fruits.add("banana");
    }

    @Test
    public void shouldReturnIndexOf() {

        fruits.add("orange");
        fruits.add("banana");
        fruits.add("watermellon");
        fruits.add("banana");
        assertTrue(fruits.indexOf("banana") == 1);
        assertTrue(fruits.lastIndexOf("banana") == 3);

        assertTrue(fruits.contains("banana"));
        assertTrue(fruits.indexOf("melon") == -1);
        assertTrue(fruits.lastIndexOf("melon") == -1);
    }

    @Test
    public void shouldReturnContains() {

        fruits.add("orange");
        fruits.add("banana");
        fruits.add("watermellon");
        assertTrue(fruits.contains("banana"));
        assertFalse(fruits.contains("melon"));
        assertTrue(fruits.containsAll(Arrays.asList("banana", "orange")));
        assertFalse(fruits.containsAll(Arrays.asList("banana", "melon")));

    }

    @SuppressWarnings("unused")
    @Test
    public void shouldIterate() {
        fruits.add("melon");
        fruits.add("banana");
        int count = 0;
        for (String fruiCart : fruits) {
            count++;
        }
        assertTrue(count == 2);
        fruits.remove(0);
        fruits.remove(0);
        count = 0;
        for (String fruiCart : fruits) {
            count++;
        }
        assertTrue(count == 0);
    }

    @After
    public void end() {
        fruits.clear();
    }
}