/**
 * Simple data types for ladder diagram elements.
 *
 * This file contains a small type hierarchy for common ladder diagram components:
 * - Coil (has an attribute `var`)
 * - Contact (has an attribute `var`)
 * - NegatedContact (inherits Contact)
 * - VerticalLink
 * - EndOfVerticalLink
 * - HorizontalLink
 * - LeftPowerRail
 * - Empty
 */
public final class LadderElements {

    private LadderElements() { /* utility holder */ }

    /** Marker interface for all ladder elements. */
    public interface LadderElement { }

    /** A coil with an associated variable name. */
    public static final class Coil implements LadderElement {
        private final String var;

        public Coil(String var) {
            this.var = var;
        }

        public String getVar() {
            return var;
        }

        @Override
        public String toString() {
            return "Coil{" + var + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coil)) return false;
            Coil coil = (Coil) o;
            return var == null ? coil.var == null : var.equals(coil.var);
        }

        @Override
        public int hashCode() {
            return var == null ? 0 : var.hashCode();
        }
    }

    /** A normally open contact with an associated variable name. */
    public static class Contact implements LadderElement {
        private final String var;

        public Contact(String var) {
            this.var = var;
        }

        public String getVar() {
            return var;
        }

        @Override
        public String toString() {
            return "Contact{" + var + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Contact)) return false;
            Contact contact = (Contact) o;
            return var == null ? contact.var == null : var.equals(contact.var);
        }

        @Override
        public int hashCode() {
            return var == null ? 0 : var.hashCode();
        }
    }

    /** A negated contact (inherits Contact). */
    public static final class NegatedContact extends Contact {
        public NegatedContact(String var) {
            super(var);
        }

        @Override
        public String toString() {
            return "NegatedContact{" + getVar() + '}';
        }
    }

    /** A vertical link in the ladder diagram. Use the singleton instance. */
    public static final class VerticalLink implements LadderElement {
        public static final VerticalLink INSTANCE = new VerticalLink();
        private VerticalLink() { }

        @Override
        public String toString() { return "VerticalLink"; }
    }

    /** The end marker of a vertical link. Singleton. */
    public static final class EndOfVerticalLink implements LadderElement {
        public static final EndOfVerticalLink INSTANCE = new EndOfVerticalLink();
        private EndOfVerticalLink() { }

        @Override
        public String toString() { return "EndOfVerticalLink"; }
    }

    /** A horizontal connection/link. Singleton. */
    public static final class HorizontalLink implements LadderElement {
        public static final HorizontalLink INSTANCE = new HorizontalLink();
        private HorizontalLink() { }

        @Override
        public String toString() { return "HorizontalLink"; }
    }

    /** The left-power rail of the ladder. Singleton. */
    public static final class LeftPowerRail implements LadderElement {
        public static final LeftPowerRail INSTANCE = new LeftPowerRail();
        private LeftPowerRail() { }

        @Override
        public String toString() { return "LeftPowerRail"; }
    }

    /** Represents an empty/no-op cell. Singleton. */
    public static final class Empty implements LadderElement {
        public static final Empty INSTANCE = new Empty();
        private Empty() { }

        @Override
        public String toString() { return "Empty"; }
    }
}
