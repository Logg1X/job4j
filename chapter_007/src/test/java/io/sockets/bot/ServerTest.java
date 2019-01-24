package io.sockets.bot;

import com.google.common.base.Joiner;
import io.sockets.app.Client;
import io.sockets.app.Server;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class ServerTest {
//    @Mock
//    private Socket socket;
//
//    private void testServer(String input, String expected) throws IOException {
//        MockitoAnnotations.initMocks(this);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
//        when(socket.getInputStream()).thenReturn(in);
//        when(socket.getOutputStream()).thenReturn(out);
//        Server server = new Server(socket);
//        server.start();
//        assertThat(out.toString(), is(expected));
//    }
//
//    @Test
//    public void whenAskHiThenGreatOracle() throws IOException {
//        this.testServer(
//                Joiner.on(System.lineSeparator()).join("crazybot", "hi", "exit", "close program"),
//                Joiner.on(System.lineSeparator()).join(
//                        "",
//                        "Hello, dear friend, I'm a oracle.",
//                        "",
//                        "close program",
//                        "",
//                        ""
//                )
//        );
//    }
//
//    @Test
//    public void whenAskHoweAreYouThenItsOk() throws IOException {
//        this.testServer(
//                Joiner.on(System.lineSeparator()).join("crazybot", "how are u?", "exit", "close program"),
//                Joiner.on(System.lineSeparator()).join(
//                        "",
//                        "I'm fine!",
//                        "",
//                        "close program",
//                        "",
//                        ""
//                )
//        );
//    }
//
//    @Test
//    public void whenAskUnsupportedThenIDonNotUnderstand() throws IOException {
//        this.testServer(
//                Joiner.on(System.lineSeparator()).join("crazybot", "Unsupported ask", "exit", "close program"),
//                Joiner.on(System.lineSeparator()).join(
//                        "",
//                        "I'm don't understand what you mean...",
//                        "",
//                        "close program",
//                        "",
//                        ""
//                )
//        );
//    }
//
//    @Test
//    public void whenAskExitThenCloseProgram() throws IOException {
//        this.testServer(
//                "close program",
//                Joiner.on(System.lineSeparator()).join(
//                        "close program",
//                        "",
//                        ""
//                )
//        );
//
//    }
}