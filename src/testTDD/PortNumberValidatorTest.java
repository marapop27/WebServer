package testTDD;

import static org.junit.Assert.*;

import java.nio.file.InvalidPathException;

import org.junit.Test;

import validators.PortNumberValidator;

public class PortNumberValidatorTest {

	@Test(expected = InvalidPathException.class)
	public void testPortNumberInvalidMax() {
		PortNumberValidator.validate(67000);
	}
	
	@Test(expected = InvalidPathException.class)
	public void testPortNumberInvalid() {
		PortNumberValidator.validate(0);
	}
	
	@Test(expected = InvalidPathException.class)
	public void testPortNumberValid() {
		PortNumberValidator.validate(8000);
	}

}
