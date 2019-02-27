package ood.tdd;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleGeneratorTest {

    @Test
    public void whenGenerateThenReturnCorrectString() {
        SimpleGenerator generator = new SimpleGenerator();
        String templ = " Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> keys = Map.of("${sos}", "Aaaaaaaa");
        String exp = " Help, Aaaaaaaa, Aaaaaaaa, Aaaaaaaa";
        assertThat(generator.generated(templ, keys), is(exp));
    }

    @Test
    public void whenGeneratedThenNotEnoughKeys() {
        this.checkException(Map.of("${name}", "Pavel"), "Not enough keys!");
    }

    @Test
    public void whenGeneratedThenExcessKeys() {
        this.checkException(Map.of("${name}", "Pavel",
                "${subject}", "You",
                "${Sub}", "Pavel"
        ), "Excess keys!");
    }

    @Test
    public void whenGeneratedThanThroeExceptionMessageKeysListIsEmpty() {
        this.checkException(Map.of(), "The list of keys is empty or the string does not contain any keys!");
    }

    private void checkException(Map<String, String> keys, String messageEx) {
        SimpleGenerator generator = new SimpleGenerator();
        String templ = "I am a ${name}, Who are ${subject}? ";
        try {
            generator.generated(templ, keys);
        } catch (UnsupportedOperationException e) {
            assertThat(e.getMessage(), is(messageEx));
        }
    }
}