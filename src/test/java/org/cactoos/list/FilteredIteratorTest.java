package org.cactoos.list;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Test case for {@link FilteredIterator}.
 *
 * @author Tim Hinkes (timmeey@timmeey.de)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class FilteredIteratorTest {

    @Test
    public void notRemovesUnreturnedElement() {
        List<Integer> list = IntStream.range(0, 1).boxed().collect(Collectors.toList());

        Iterator<Integer> filteredIterator = new FilteredIterator<>(list.iterator(), input -> true);
//        Iterator<Integer> filteredIterator = list.iterator();

        filteredIterator.hasNext();//pulls 0 via the next() from underlying iterator
        // underlying: (0,1)
        // pointer      ^
        //expected: (0,1)
        //expected   ^
        filteredIterator.hasNext(); //pulls 1 via next() from underlying iterator
        // underlying: (0,1)
        // pointer        ^
        //expected: (0,1)
        //expected   ^
        filteredIterator.remove();
        //since the underlying iterator points to 1 already, remove() will remove the 1 instead of 0;

        MatcherAssert.assertThat("Should not contain 0 anymore", list.contains(0) == false);
        MatcherAssert.assertThat("Should still contain 1", list.contains(1));
    }
}
