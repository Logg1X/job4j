package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Toporov Pavel (mailto:per4mancerror@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class StartUITest {
    private final PrintStream stream = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Tracker tracker = new Tracker();

    @Test
    public void wenAddItemUseMenu0() {
        Input input = new StubInput(new String[]{"1", "TestName", "TestDescription",
                "1", "TestName1", "TestDescription1",
                "1", "TestName1", "TestDescription1",
                "7"});
        new StartUI(input, this.tracker).init();
        Item item = new Item("TestName1", "TestDescription1");
        item.setId(this.tracker.findAll().get(1).getId());
        assertThat(this.tracker.findAll().get(1).getName(), is(item.getName()));
        assertThat(this.tracker.findAll().get(1).getDescription(), is(item.getDescription()));
        assertThat(this.tracker.findAll().get(1).getId(), is(item.getId()));

    }


    @Test
    public void whenEditNameAndDescription() {
        this.tracker.add(new Item("TestName", "TestDescription"));
        this.tracker.add(new Item("TestName2", "TestDescription2"));
        this.tracker.add(new Item("TestName3", "TestDescription3"));
        Input input = new StubInput(new String[]{
                "3", this.tracker.findByName("TestName2").get(0).getId(), "NameCHANGED", "DescCHANGED", "7"});
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findAll().get(1).getName(), is("NameCHANGED"));
        assertThat(this.tracker.findAll().get(1).getDescription(), is("DescCHANGED"));
    }

    @Test
    public void whenDeleteItem() {
        this.tracker.add(new Item("TestName", "TestDescription"));
        this.tracker.add(new Item("TestName2", "TestDescription2"));
        this.tracker.add(new Item("TestName3", "TestDescription3"));
        this.tracker.add(new Item("TestName4", "TestDescription4"));
        Input input = new StubInput(new String[]{
                "4", this.tracker.findByName("TestName3").get(0).getId(), "7"});
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findAll().get(2), is(this.tracker.findByName("TestName4").get(0)));
    }


/*    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stream);
    }

    @Test
    public void whenShowAllItem() {
        Item item1 = new Item("Test", "TestDesc");
        Item item2 = new Item("TestName", "TestDescription");
        this.trackerRum.add(item1);
        this.trackerRum.add(item2);
        this.trackerRum.findAll()[0].setId("191#7");
        this.trackerRum.findAll()[1].setId("243#63");
        Input input = new StubInput(new String[]{
                "1", "6"});
        new StartUI(input, this.trackerRum).init();
        assertThat(new String(this.out.toByteArray()), is(new StringBuilder()
                .append(this.menu)
                .append("------------ Список всех задач --------------").append(System.lineSeparator())
                .append("________________________________________________ ").append(System.lineSeparator())
                .append("Задача №: 191#7.").append(System.lineSeparator())
                .append("Имя: Test.").append(System.lineSeparator())
                .append("Описание: TestDesc.").append(System.lineSeparator())
                .append("________________________________________________").append(System.lineSeparator())
                .append("________________________________________________ ").append(System.lineSeparator())
                .append("Задача №: 243#63.").append(System.lineSeparator())
                .append("Имя: TestName.").append(System.lineSeparator())
                .append("Описание: TestDescription.").append(System.lineSeparator())
                .append("________________________________________________").append(System.lineSeparator())
                .append(this.menu)
                .toString()));
    }

    @Test
    public void whenFindByID() {
        Item item1 = new Item("Test", "TestDesc");
        Item item2 = new Item("Test", "Testers");
        Item item3 = new Item("TestName", "TestDescription");
        this.trackerRum.add(item1);
        this.trackerRum.add(item2);
        this.trackerRum.add(item3);
        this.trackerRum.findAll()[0].setId("191#7");
        this.trackerRum.findAll()[1].setId("243#63");
        this.trackerRum.findAll()[2].setId("666#93");
        Input input = new StubInput(new String[]{
                "5", "Test", "6"});
        new StartUI(input, this.trackerRum).init();
        assertThat(new String(this.out.toByteArray()), is(new StringBuilder()
                .append(this.menu)
                .append("________________________________________________ ").append(System.lineSeparator())
                .append("Задача №: 191#7.").append(System.lineSeparator())
                .append("Имя: Test.").append(System.lineSeparator())
                .append("Описание: TestDesc.").append(System.lineSeparator())
                .append("________________________________________________").append(System.lineSeparator())
                .append("________________________________________________ ").append(System.lineSeparator())
                .append("Задача №: 243#63.").append(System.lineSeparator())
                .append("Имя: Test.").append(System.lineSeparator())
                .append("Описание: Testers.").append(System.lineSeparator())
                .append("________________________________________________").append(System.lineSeparator())
                .append(this.menu)
                .toString()));
    }*/
}