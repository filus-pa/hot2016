/*
 * Copyright (c) 2016. Filip "Filus" Pająk
 */

package com.github.filus_pa.hot2016.formula.game.controller;

import java.util.Arrays;
import java.util.List;

/**
 * Created for HoT 2016 internships, by Filus
 */
public enum Speed {
	V0(0),
	V40(1),
	V80(2),
	V120(3),
	V160(4),
	V200(5),
	V240(6),
	V280(7),
	V320(8);

	private final int value;

	Speed(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static Speed getVMax() {
		final List<Speed> speeds = Arrays.asList(Speed.values());

		//noinspection OptionalGetWithoutIsPresent
		final Speed vMax = speeds.stream().max((speed1, speed2) -> Integer.compare(speed1.getValue(), speed2.getValue())).get();

		return vMax;
	}


}
