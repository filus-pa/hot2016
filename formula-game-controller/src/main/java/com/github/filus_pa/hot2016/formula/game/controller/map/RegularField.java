/*
 * Copyright (c) 2016. Filip "Filus" Pająk
 */

package com.github.filus_pa.hot2016.formula.game.controller.map;

import com.github.filus_pa.hot2016.formula.game.controller.Speed;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.util.Optional;

/**
 * Created for HoT 2016 internships, by Filus
 * <p>
 * Date: 2016-07-23
 */

public class RegularField implements Comparable<RegularField> {
	public final int absolutePosition;
	public final Speed speedLimit;
	public final Optional<RegularField> leftExit;
	public final Optional<RegularField> forwardExit;
	public final Optional<RegularField> rightExit;

	public RegularField(final int absolutePosition, final RegularField forwardExit) {
		this(absolutePosition, null, null, forwardExit, null);
	}

	public RegularField(final int absolutePosition, final Speed speedLimit, final RegularField forwardExit) {
		this(absolutePosition, speedLimit, null, forwardExit, null);
	}

	public RegularField(final int absolutePosition, final RegularField leftExit, final RegularField forwardExit, final RegularField rightExit) {
		this(absolutePosition, null, leftExit, forwardExit, rightExit);
	}

	public RegularField(final int absolutePosition, final Speed speedLimit, final RegularField leftExit, final RegularField forwardExit, final RegularField rightExit) {
		this.absolutePosition = absolutePosition >= 0 ? absolutePosition : 0;
		this.speedLimit = speedLimit != null ? speedLimit : Speed.getVMax();
		this.leftExit = Optional.ofNullable(leftExit);
		this.rightExit = Optional.ofNullable(rightExit);
		this.forwardExit = Optional.ofNullable(forwardExit);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("absolutePosition", absolutePosition)
				.add("speedLimit", speedLimit)
				.toString();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final RegularField that = (RegularField) o;
		return absolutePosition == that.absolutePosition;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(absolutePosition);
	}


	@Override
	public int compareTo(final RegularField other) {
		return ComparisonChain.start().compare(this.absolutePosition, other.absolutePosition).result();
	}
}
