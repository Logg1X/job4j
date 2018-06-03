package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test
* @author Pavel Toporov (per4mancerror@gmail.com).
* @version $id$
* @since 0.1
*/

public class CalculateTest{
	/**
	* Test echo.
	*/ @Test
	public void whenTakeNameThenTreeEchoPlusName() {
    String input = "Toporov Pavel";
    String expect = "Echo, echo, echo : Toporov Pavel";
    Calculate calc = new Calculate();
    String result = calc.echo(input);
    assertThat(result, is(expect));
	}	
}