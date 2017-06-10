package org.cactoos.list;

import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.*;

public class FuncAsIterableTest {

    @Test
    public void generatesElement() {
        MatcherAssert.assertThat("Generates first Element",
                                 new FirstOf<Integer>(
                                     new FuncAsIterable<Integer>(input -> {
                                         if (input == null) {
                                             return 0;
                                         } else {
                                             return input + 1;
                                         }
                                     })
                                 ), new ScalarHasValue(0));
    }

    @Test
    public void generatesSequentialElements() {
        MatcherAssert.assertThat("Generates first Element",
                                 new UnmodifyableIterableAsList<>(
                                     new FuncAsIterable<Integer>(input -> {
                                         if (input == null) {
                                             return 0;
                                         } else {
                                             return input + 1;
                                         }
                                     })
                                 ).get(1), Matchers.is(1));
    }
}
