package org.cactoos.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import org.cactoos.Func;

public class FuncAsIterator<X> implements Iterator<X> {

    private final Func<X, X> func;
    private final Stack<X> cache = new Stack<>();
    private final Stack<X> lastReturned = new Stack<>();

    public FuncAsIterator(Func<X, X> func) {
        this.func = func;
    }

    @Override
    public boolean hasNext() {
        try {
            if (cache.isEmpty()) {
                cache.add(
                    func.apply(getLastReturned())
                );
            }
            return !cache.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public X next() {
        if (hasNext()) {
            X toBeReturned = cache.pop();
            lastReturned.add(toBeReturned);
            return toBeReturned;

        } else {
            throw new NoSuchElementException("No more Elements");
        }
    }

    private X getLastReturned(){
        if(lastReturned.isEmpty()){
            return null;
        }
        return lastReturned.peek();

    }
}
