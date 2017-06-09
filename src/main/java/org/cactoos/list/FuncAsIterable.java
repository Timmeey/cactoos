package org.cactoos.list;

import java.util.Iterator;
import org.cactoos.Func;

public class FuncAsIterable<X> implements Iterable<X> {

    private final Func<X, X> origin;

    public FuncAsIterable(Func<X,X> origin){

        this.origin = origin;
    }
    @Override
    public Iterator<X> iterator() {
        return new FuncAsIterator<>(origin);
    }
}
