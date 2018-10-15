package jdbc.tracker.input;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Input {

    String ask(String question);

    int ask(String question, int[] range);
}
