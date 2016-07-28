/*
 * Copyright (c) 2016. Filip "Filus" Pająk
 */

package com.github.filus_pa.hot2016.formula.game.controller.map;

import com.google.common.base.VerifyException;
import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.apis.BDDCatchException.caughtException;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created for HoT 2016 internships, by Filus
 * <p>
 * Date: 2016-07-27
 */
@Test
public class LaneTest {

	private final Random random = new Random();

	@Test(expectedExceptions = NullPointerException.class)
	public void shouldNotCreatedIfFirstFieldIsNull() {
		//given

		//when
		new Lane(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void shouldNotCreatedIfNoFieldsAreGiven() {
		//given
		final Collection<RegularField> fields = new ArrayList<>();

		//when
		new Lane(fields);
	}

	@Test(dataProvider = "fieldsPositions")
	public void shouldReturnFieldAtPosition(final RegularField[] fields, final int position, final RegularField expectedField) {
		//given
		final Lane lane = new Lane(Arrays.asList(fields));
		//when
		final RegularField actualField = lane.getFieldAt(position);

		//then
		assertThat(actualField).as("Check field at position %d", position).isEqualTo(expectedField);
	}

	@Test(dataProvider = "positionsOutOfBounds")
	public void shouldThrowOutOfBoundExceptionIfGivenPositionIsOutOfLaneSize(final RegularField[] fields, final int position) {
		//given
		final Lane lane = new Lane(Arrays.asList(fields));

		//when
		catchException(lane).getFieldAt(position);

		//then
		assertThat(caughtException()).isInstanceOf(IndexOutOfBoundsException.class);
	}

	@Test(dataProvider = "fieldsPositions")
	public void shouldReturnPositionOfField(final RegularField[] fields, final int expectedPosition, final RegularField field) {
		//given
		final Lane lane = new Lane(Arrays.asList(fields));

		//when
		final int actualPosition = lane.getPositionOfField(field);

		//then
		assertThat(actualPosition).as("Check field %s position in lane", field).isEqualTo(expectedPosition);
	}

	public void shouldCheckReturnTrueIfFieldIsOnLane() {
		//given
		final RegularField[] fields = fieldsGenerator();
		final Lane lane = new Lane(Arrays.asList(fields));
		final RegularField field = fields[random.nextInt(fields.length)];

		//when
		final boolean actualCheckResult = lane.hasField(field);

		//then
		assertThat(actualCheckResult).isTrue();
	}

	public void shouldCheckReturnFalseIfFieldIsNotOnLane() {
		//given
		final RegularField[] fields = fieldsGenerator();
		final Lane lane = new Lane(Arrays.asList(fields));
		final RegularField field = new RegularField(random.nextInt(100) + fields.length, null);

		//when
		final boolean actualCheckResult = lane.hasField(field);

		//then
		assertThat(actualCheckResult).isFalse();
	}

	public void shouldThrowExceptionIfFieldIsOutOfLane() {
		//given
		final RegularField[] fields = fieldsGenerator();
		final Lane lane = new Lane(Arrays.asList(fields));
		final RegularField field = new RegularField(random.nextInt(100) + fields.length, null);

		//when
		catchException(lane).getPositionOfField(field);

		//then
		assertThat(caughtException()).isInstanceOf(VerifyException.class).hasMessage("Lane doesn't contains %s", field);

	}

	@Test(dataProvider = "fieldsPositions")
	public void shouldDuplicatedFieldHasIndexOfFirstGivenInstance(final RegularField[] fields, final int expectedPosition, final RegularField duplicatedField) {
		//given
		final List<RegularField> fieldsAsList = Lists.newArrayList(fields);
		fieldsAsList.add(0, duplicatedField);
		final Lane testedLane = new Lane(fieldsAsList);

		//when
		final int actualPosition = testedLane.getPositionOfField(duplicatedField);

		//then
		assertThat(actualPosition).isEqualTo(expectedPosition);

	}

	@DataProvider(name = "fieldsPositions")
	public Object[][] fieldsPositionProvider() {
		final RegularField[] fields = fieldsGenerator();
		final int fieldsCount = fields.length;

		final Object[][] data = new Object[fieldsCount][3];

		for (int i = 0; i < fieldsCount; i++) {
			data[i][0] = fields;
			data[i][1] = i;
			data[i][2] = fields[i];
		}

		return data;
	}

	@DataProvider(name = "positionsOutOfBounds")
	public Object[][] positionsOutOfBoundsProvider() {
		final RegularField[] fields = fieldsGenerator();
		final int fieldsLength = fields.length;
		final Object[][] data = new Object[][] {
				{fields, -10},
				{fields, -1},
				{fields, fieldsLength},
				{fields, fieldsLength + random.nextInt(100)}
		};

		return data;
	}


	private RegularField[] fieldsGenerator() {
		final int laneSize = random.nextInt(7) + 3;
		final RegularField[] fields = new RegularField[laneSize];

		fields[laneSize - 1] = new RegularField(laneSize - 1, null);

		for (int i = laneSize - 2; i >= 0; i--) {
			final RegularField current = new RegularField(i, fields[i + 1]);
			fields[i] = current;
		}

		return fields;
	}


}