/*
 * Copyright (c) 2016. Filip "Filus" Pająk
 */

package com.github.filus_pa.hot2016.formula.game.controller;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created for HoT 2016 internships, by Filus
 * <p>
 * Date: 2016-07-23
 */

@Test
public class SpeedTest {

	public void shouldReturnVMax() {
		//given
		final Speed expected = Speed.V320;

		//when
		final Speed actual = Speed.getVMax();

		//then
		assertEquals(actual, expected);
	}
}