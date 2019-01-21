package io.socets.bot;

import com.google.common.base.Joiner;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class ServerTest {
    @Mock
    private Socket socket;

    private void testServer(String input, String expected) throws IOException {
        MockitoAnnotations.initMocks(this);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(this.socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenAskHiThenGreatOracle() throws IOException {
        this.testServer(
                Joiner.on(System.lineSeparator()).join("hi", "exit"),
                Joiner.on(System.lineSeparator()).join(
                        "Hello, dear friend, I'm a oracle.",
                        "",
                        "",
                        "",
                        ""
                )
        );
    }

    @Test
    public void whenAskHoweAreYouThenItsOk() throws IOException {
        this.testServer(
                Joiner.on(System.lineSeparator()).join("how are u?", "exit"),
                Joiner.on(System.lineSeparator()).join(
                        "I'm fine!",
                        "",
                        "",
                        "",
                        ""
                )
        );
    }

    @Test
    public void whenAskUnsupportedThenIDonNotUnderstand() throws IOException {
        this.testServer(
                Joiner.on(System.lineSeparator()).join("Unsupported ask", "exit"),
                Joiner.on(System.lineSeparator()).join(
                        "I'm don't understand what you mean...",
                        "",
                        "",
                        "",
                        ""
                )
        );
    }

    @Test
    public void whenAskExitThenCloseProgram() throws IOException {
        this.testServer(
                "exit",
                Joiner.on(System.lineSeparator()).join(
                        "",
                        "",
                        ""
                )
        );
    }
}