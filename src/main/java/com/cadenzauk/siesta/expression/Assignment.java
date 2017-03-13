/*
 * Copyright (c) 2017 Cadenza United Kingdom Limited
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.cadenzauk.siesta.expression;

import com.cadenzauk.siesta.Scope;
import com.cadenzauk.siesta.expression.Expression;
import com.cadenzauk.siesta.expression.assignment.AssignmentValue;

import java.util.stream.Stream;

public class Assignment {
    private final Expression lhs;
    private final AssignmentValue rhs;

    public Assignment(Expression lhs, AssignmentValue rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String sql(Scope scope) {
        return lhs.sql(scope) + rhs.sql(scope);
    }

    public Stream<Object> args() {
        return Stream.concat(lhs.args(), rhs.args());
    }
}