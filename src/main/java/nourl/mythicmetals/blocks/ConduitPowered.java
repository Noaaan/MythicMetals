package nourl.mythicmetals.blocks;

/**
 * Marks a BlockEntity to be ticked when in range of an active Conduit
 */
public interface ConduitPowered {
    /**
     * Override this method to handle what happens when the Conduit is in range of the Conduit Block Entity
     */
    void activate();
}
