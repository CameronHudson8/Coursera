package algorithmsAndDataStructures;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This class extends the TreeSet object with additional methods.
 * 
 * @author Cameron Hudson
 * @date 2018-03-20
 *
 * @param <E> The type of object contained in this TreeSet.
 */
public class TreeSetExtended<E> extends TreeSet<E> {

    // Include a serialVersionUID to satisfy the compiler.
    private static final long serialVersionUID = -8997912750118513924L;

    /**
     * No-args constructor.
     */
    public TreeSetExtended() {
        super();
    }

    /**
     * Constructs a TreeSetExtended from a SortedSet.
     * 
     * @param ss A SortedSet (such as returned by TreeSet.headSet(root)).
     */
    public TreeSetExtended(SortedSet<E> ss) {
        super(ss);
    }

    /**
     * Returns the root element of this TreeSetExtended.
     *
     * @return The root element.
     */
    @SuppressWarnings("unchecked")
    public E getTreeRoot() {
        try {
            Field mField = TreeSet.class.getDeclaredField("m");
            mField.setAccessible(true);
            return getTreeRoot((TreeMap<E, Object>) mField.get(this));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Internals of TreeSet has changed", e);
        }
    }

    /**
     * Gets the root element of a TreeMap.
     *
     * @param tm The TreeMap from which to find the root element.
     * @return The root element.
     */
    @SuppressWarnings("unchecked")
    private static <K, V> K getTreeRoot(TreeMap<K, V> tm) {
        try {
            Field rootField = TreeMap.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Map.Entry<K, V> root = (Map.Entry<K, V>) rootField.get(tm);
            return (root == null ? null : root.getKey());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Internals of TreeMap has changed", e);
        }
    }

    /**
     * Returns the element in this TreeMapExtended corresponding to the ith statistic.
     * 
     *
     * @param statistic The index i of the ith statistic to find.
     * @return The element corresponding to the ith statistic.
     */
    public E getStatistic(int i) {
        E root = this.getTreeRoot();
        E result = this.getStatisticRecurse(i, root);
        return result;
    }

    /**
     * Recursively traverses this TreeMapExtended and returns the element corresponding to the ith
     * statistic relative to the supplied root element.
     *
     * @param i The ith statistic to find.
     * @param root The root from which to begin.
     * @return The element corresponding to the ith statistic.
     */
    private E getStatisticRecurse(int i, E root) {

        SortedSet<E> headSet = this.headSet(root, false);

        // Is our desired i in the left side of the tree?
        if (headSet.size() >= i) {

            // In this case, recurse on the left side of the tree.
            TreeSetExtended<E> headSetTreeExtended = new TreeSetExtended<E>(headSet);
            root = headSetTreeExtended.getTreeRoot();
            return headSetTreeExtended.getStatisticRecurse(i, root);
        }

        // Or, is our desired i in the right side of the tree?
        if (headSet.size() + 1 < i) {

            // In this case, recurse on the right side of the tree.
            i = i - headSet.size() - 1;
            SortedSet<E> tailSet = this.tailSet(root, false);
            TreeSetExtended<E> tailSetTreeExtended = new TreeSetExtended<E>(tailSet);
            root = tailSetTreeExtended.getTreeRoot();
            return tailSetTreeExtended.getStatisticRecurse(i, root);
        }

        // Otherwise, the root is our desired statistic.
        return root;
    }
}
