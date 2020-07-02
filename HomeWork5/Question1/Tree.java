package Question1;

import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {

  E root();

  int parent(int p) throws IllegalArgumentException;

  Iterable<Integer> children(int p);

  int numChildren(int p) throws IllegalArgumentException;

  boolean isInternal(int p) throws IllegalArgumentException;

  boolean isExternal(int p) throws IllegalArgumentException;

  boolean isRoot(int p) throws IllegalArgumentException;

  int size();

  boolean isEmpty();

  Iterator<E> iterator();

  Iterable<Integer> positions();
}