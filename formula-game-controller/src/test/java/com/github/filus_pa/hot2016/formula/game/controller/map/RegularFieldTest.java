/*
 * Copyright (c) 2016. Filip "Filus" Pająk
 */

package com.github.filus_pa.hot2016.formula.game.controller.map;

import com.github.filus_pa.hot2016.formula.game.controller.Speed;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created for HoT 2016 internships, by Filus
 * <p>
 * Date: 2016-07-23
 */
@Test
public class RegularFieldTest {
	private Random random = new Random();

	public void shouldHaveVmaxAsSpeedLimitIfNotGiven() {
		//given
		final RegularField testedField = new RegularField(0, null);
		final Speed expectedSpeed = Speed.getVMax();

		//when
		final Speed actualSpeedLimit = testedField.speedLimit;

		//then
		assertThat(actualSpeedLimit).as("Check speed limit on field").isEqualTo(expectedSpeed);
	}

	public void shouldHaveVMaxAsSpeedLimitIfNullGiven() {
		//given
		final RegularField testedField = new RegularField(0, null, null);
		final Speed expectedSpeed = Speed.getVMax();

		//when
		final Speed actualSpeedLimit = testedField.speedLimit;

		//then
		assertThat(actualSpeedLimit).as("Check speed limit on field").isEqualTo(expectedSpeed);
	}

	public void shouldHaveGivenVMaxAsSpeedLimit() {
		//given
		final Speed expectedSpeed = Speed.V160;
		final RegularField testedField = new RegularField(0, expectedSpeed, null);

		//when
		final Speed actualSpeedLimit = testedField.speedLimit;

		//then
		assertThat(actualSpeedLimit).as("Check speed limit on field").isEqualTo(expectedSpeed);
	}

	public void shouldHaveGivenAbsolutePosition() {
		//given
		final int expectedPosition = Math.abs(random.nextInt()) ;
		final RegularField testedField = new RegularField(expectedPosition, null);

		//when
		final int actualAbsolutePosition = testedField.absolutePosition;

		//then
		assertThat(actualAbsolutePosition).as("Check absolute position of field").isEqualTo(expectedPosition);
	}

	public void shouldHaveZeroAbsolutePositionIfGivenNegative() {
		//given
		final int negativePosition = (random.nextInt(128) + 1) * -1;
		final RegularField testedField = new RegularField(negativePosition, null);

		//when
		final int actualAbsolutePosition = testedField.absolutePosition;

		//then
		assertThat(actualAbsolutePosition).as("Check absolute position of field").isZero();
	}

	public void shouldBeEqualsIfHaveTheSameAbsolutePosition() {
		//given
		final int position = Math.abs(random.nextInt());

		//when
		final RegularField firstField = new RegularField(position, Speed.V80, null);
		final RegularField secondField = new RegularField(position, Speed.V240, null);

		//then
		assertThat(firstField).as("Check fields equality").isEqualTo(secondField);
	}

	public void shouldNotBeEqualsIfHaveDifferentAbsolutePosition() {
		//given
		final int firstPosition = Math.abs(random.nextInt());
		final int secondPosition = Math.abs(random.nextInt());

		//when
		final RegularField firstField = new RegularField(firstPosition, null);
		final RegularField secondField = new RegularField(secondPosition, null);

		//then
		assertThat(firstField).as("Check fields equality").isNotEqualTo(secondField);
	}

	public void shouldHaveTheSameHashCodeIfHaveTheSameAbsolutePosition() {
		//given
		final int position = Math.abs(random.nextInt());

		//when
		final int firstHashCode = new RegularField(position, Speed.V80, null).hashCode();
		final int secondHashCode = new RegularField(position, Speed.V240, null).hashCode();

		//then
		assertThat(firstHashCode).as("Check hash codes equality").isEqualTo(secondHashCode);
	}

	public void shouldHaveDifferentHashCodesIfHaveDifferentAbsolutePosition() {
		//given
		final int firstPosition = Math.abs(random.nextInt());
		final int secondPosition = Math.abs(random.nextInt());

		//when
		final int firstHashCode = new RegularField(firstPosition, null).hashCode();
		final int secondHashCode = new RegularField(secondPosition, null).hashCode();

		//then
		assertThat(firstHashCode).as("Check hash code equality").isNotEqualTo(secondHashCode);
	}

	public void shouldReturnOptionalLeftExitIfNotGiven() {
		//given
		final RegularField leftExit = null;
		final RegularField forwardExit = new RegularField(4, null, null, null);
		final RegularField rightExit = new RegularField(2, null, null, null);

		final RegularField testedField = new RegularField(1, leftExit, forwardExit, rightExit);

		//when
		final Optional<RegularField> actualLeftExit = testedField.leftExit;

		//then
		assertThat(actualLeftExit).as("Check left exit optionality").isNotPresent();
	}

	public void shouldReturnOptionalLeftExitIfGiven() {
		//given
		final RegularField leftExit = new RegularField(0, null, null, null);
		final RegularField forwardExit = new RegularField(4, null, null, null);
		final RegularField rightExit = new RegularField(2, null, null, null);

		final RegularField testedField = new RegularField(1, leftExit, forwardExit, rightExit);

		//when
		final Optional<RegularField> actualLeftExit = testedField.leftExit;

		//then
		assertThat(actualLeftExit).as("Check left exit optionality").isPresent();
		assertThat(actualLeftExit).as("Check left exit optionality").hasValue(leftExit);
	}

	public void shouldReturnOptionalForwardExitIfNotGiven() {
		//given
		final RegularField leftExit = new RegularField(0, null, null, null);
		final RegularField forwardExit = null;
		final RegularField rightExit = new RegularField(2, null, null, null);

		final RegularField testedField = new RegularField(1, leftExit, forwardExit, rightExit);

		//when
		final Optional<RegularField> actualForwardExit = testedField.forwardExit;

		//then
		assertThat(actualForwardExit).as("Check forward exit optionality").isNotPresent();
	}

	public void shouldReturnOptionalForwardExitIfGiven() {
		//given
		final RegularField leftExit = new RegularField(0, null, null, null);
		final RegularField forwardExit = new RegularField(4, null, null, null);
		final RegularField rightExit = new RegularField(2, null, null, null);

		final RegularField testedField = new RegularField(1, leftExit, forwardExit, rightExit);

		//when
		final Optional<RegularField> actualForwardExit = testedField.forwardExit;

		//then
		assertThat(actualForwardExit).as("Check forward exit optionality").isPresent();
		assertThat(actualForwardExit).as("Check forward exit optionality").hasValue(forwardExit);
	}

	public void shouldReturnOptionalRightExitIfNotGiven() {
		//given
		final RegularField leftExit = new RegularField(0, null, null, null);
		final RegularField forwardExit = new RegularField(4, null, null, null);
		final RegularField rightExit = null;

		final RegularField testedField = new RegularField(1, leftExit, forwardExit, rightExit);

		//when
		final Optional<RegularField> actualRightExit = testedField.rightExit;

		//then
		assertThat(actualRightExit).as("Check right exit optionality").isNotPresent();
	}

	public void shouldReturnOptionaRightExitIfGiven() {
		//given
		final RegularField leftExit = new RegularField(0, null, null, null);
		final RegularField forwardExit = new RegularField(4, null, null, null);
		final RegularField rightExit = new RegularField(2, null, null, null);

		final RegularField testedField = new RegularField(1, leftExit, forwardExit, rightExit);

		//when
		final Optional<RegularField> actualRightExit = testedField.rightExit;

		//then
		assertThat(actualRightExit).as("Check right exit optionality").isPresent();
		assertThat(actualRightExit).as("Check right exit optionality").hasValue(rightExit);
	}

	public void shouldBeLessIfAbsolutePositionIsLessThanOthers() {
		//given
		final RegularField firstField = new RegularField(12, null);
		final RegularField secondField = new RegularField(34, null);

		//when

		//then
		assertThat(firstField).isLessThan(secondField);
	}

	public void shouldBeEqualInComparingIfAbsolutePositionsAreTheSame() {
		//given
		final RegularField firstField = new RegularField(12, null);
		final RegularField secondField = new RegularField(12, null);

		//when

		//then
		assertThat(firstField).isEqualByComparingTo(secondField);
	}

	public void shouldBeGreaterIfAbsolutePositionIsGreaterThanOthers() {
		//given
		final RegularField firstField = new RegularField(42, null);
		final RegularField secondField = new RegularField(34, null);

		//when

		//then
		assertThat(firstField).isGreaterThan(secondField);
	}

}