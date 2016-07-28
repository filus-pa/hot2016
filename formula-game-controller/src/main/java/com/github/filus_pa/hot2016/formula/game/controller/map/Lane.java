/*
 * Copyright (c) 2016. Filip "Filus" Pająk
 */

package com.github.filus_pa.hot2016.formula.game.controller.map;

import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * Created for HoT 2016 internships, by Filus
 * <p>
 * Date: 2016-07-27
 */

public class Lane {
	private final Set<RegularField> fields;

	public Lane(final RegularField field, final RegularField ... otherFields) {
		Preconditions.checkNotNull(field);
		this.fields = ImmutableSortedSet.<RegularField>naturalOrder().add(field).add(otherFields).build();
	}

	public Lane(final Collection<RegularField> fields) {
		Preconditions.checkNotNull(fields);
		Preconditions.checkArgument(fields.size() > 0, "Fields cannot be empty!");

		this.fields = ImmutableSortedSet.<RegularField>naturalOrder().addAll(fields).build();
	}

	public boolean hasField(final RegularField field) {
		return fields.contains(field);
	}

	public RegularField getFieldAt(final int position) {
		return Iterables.get(fields, position);
	}

	public int getPositionOfField(final RegularField field) {
		final int indexOf = Iterables.indexOf(fields, input -> Objects.equals(input, field));
		Verify.verify(indexOf >= 0, "Lane doesn't contains %s", field);

		return indexOf;
	}


}
