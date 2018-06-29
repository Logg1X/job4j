package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    private StringBuilder menu = new StringBuilder()
            .append("_________- МЕНЮ -________").append(System.lineSeparator())
            .append("| 0. Add new Item       |").append(System.lineSeparator())
            .append("|_______________________|").append(System.lineSeparator())
            .append("| 1. Show all items     |").append(System.lineSeparator())
            .append("|_______________________|").append(System.lineSeparator())
            .append("| 2. Edit item          |").append(System.lineSeparator())
            .append("|_______________________|").append(System.lineSeparator())
            .append("| 3. Delete item        |").append(System.lineSeparator())
            .append("|_______________________|").append(System.lineSeparator())
            .append("| 4. Find item by Id    |").append(System.lineSeparator())
            .append("|_______________________|").append(System.lineSeparator())
            .append("| 5. Find items by name |").append(System.lineSeparator())
            .append("|_______________________|").append(System.lineSeparator())
            .append("| 6. Exit Program       |").append(System.lineSeparator())
            .append("|_______________________|").append(System.lineSeparator());

    @Test
    public void wenAddItemUseMenu0() {
        Input input = new StubInput(new String[]{"0", "TestName", "TestDescription",
                "0", "TestName1", "TestDescription1",
                "0", "TestName1", "TestDescription1",
                "6"});
        new StartUI(input, this.tracker).init();
        Item item = new Item("TestName1", "TestDescription1");
        item.setId(this.tracker.findAll()[1].getId());
        assertThat(this.tracker.findAll()[1].getName(), is(item.getName()));
        assertThat(this.tracker.findAll()[1].getDescripton(), is(item.getDescripton()));
        assertThat(this.tracker.findAll()[1].getId(), is(item.getId()));

    }


    @Test
    public void whenEditNameAndDescription() {
        this.tracker.add(new Item("TestName", "TestDescription"));
        this.tracker.add(new Item("TestName2", "TestDescription2"));
        this.tracker.add(new Item("TestName3", "TestDescription3"));
        Input input = new StubInput(new String[]{
                "2", this.tracker.findByName("TestName2")[0].getId(), "y", "NameCHANGED", "y", "DescCHANGED", "6"});
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findAll()[1].getName(), is("NameCHANGED"));
        assertThat(this.tracker.findAll()[1].getDescripton(), is("DescCHANGED"));
    }

    @Test
    public void whenEditDescription() {
        this.tracker.add(new Item("TestName", "TestDescription"));
        this.tracker.add(new Item("TestName2", "TestDescription2"));
        this.tracker.add(new Item("TestName3", "TestDescription3"));
        Input input = new StubInput(new String[]{
                "2", this.tracker.findByName("TestName2")[0].getId(), "n", "y", "DescCHANGED", "6"});
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findAll()[1].getDescripton(), is("DescCHANGED"));
        assertThat(this.tracker.findAll()[1].getName(), is("TestName2"));
    }

    @Test
    public void whenEditName() {
        this.tracker.add(new Item("TestName", "TestDescription"));
        this.tracker.add(new Item("TestName2", "TestDescription2"));
        this.tracker.add(new Item("TestName3", "TestDescription3"));
        Input input = new StubInput(new String[]{
                "2", this.tracker.findByName("TestName2")[0].getId(), "y", "NameCHANGED", "n", "6"});
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findAll()[1].getName(), is("NameCHANGED"));
        assertThat(this.tracker.findAll()[1].getDescripton(), is("TestDescription2"));

    }

    @Test
    public void whenDeleteItem() {
        this.tracker.add(new Item("TestName", "TestDescription"));
        this.tracker.add(new Item("TestName2", "TestDescription2"));
        this.tracker.add(new Item("TestName3", "TestDescription3"));
        this.tracker.add(new Item("TestName4", "TestDescription4"));
        Input input = new StubInput(new String[]{
                "3", this.tracker.findByName("TestName3")[0].getId(), "6"});
        new StartUI(input, this.tracker).init();
        assertThat(this.tracker.findAll()[2], is(this.tracker.findByName("TestName4")[0]));
    }


    @Before
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
        this.tracker.add(item1);
        this.tracker.add(item2);
        this.tracker.findAll()[0].setId("191#7");
        this.tracker.findAll()[1].setId("243#63");
        Input input = new StubInput(new String[]{
                "1", "6"});
        new StartUI(input, this.tracker).init();
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
        this.tracker.add(item1);
        this.tracker.add(item2);
        this.tracker.add(item3);
        this.tracker.findAll()[0].setId("191#7");
        this.tracker.findAll()[1].setId("243#63");
        this.tracker.findAll()[2].setId("666#93");
        Input input = new StubInput(new String[]{
                "5", "Test", "6"});
        new StartUI(input, this.tracker).init();
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
    }
}