/*
 * Copyright (c) 2017 Cadenza United Kingdom Limited.
 *
 * All rights reserved.   May not be used without permission.
 */

package com.cadenzauk.siesta;

import java.util.stream.Stream;

public class ColumnTest<T, R> implements Expression {
    private final Alias<R> alias;
    private final Column<T, R> column;
    private final Condition condition;

    public ColumnTest(Alias<R> alias, Column<T,R> column, Condition condition) {
        this.alias = alias;
        this.column = column;
        this.condition = condition;
    }

    @Override
    public String sql() {
        return alias.inExpression(column) + " " + condition.sql();
    }

    @Override
    public Stream<Object> args() {
        return condition.args();
    }
}
