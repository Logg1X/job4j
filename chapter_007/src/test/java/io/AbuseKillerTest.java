package io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AbuseKillerTest {

    @Test
    public void whenDeleteAbuse() throws IOException {
        AbuseKiller killer = new AbuseKiller();
        String[] abuse = {"Папа", "Дочка", "Вместе"};
        String input = "Мама      Папа         мыла      \n"
                + "раму       Дочка       Вместе        Папа";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        killer.dropAbuses(in, out, abuse);
        String exp = "Мама мыла\r\n"
                + "раму\r\n";
        assertThat(exp, is(out.toString()));
    }
}