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

package com.cadenzauk.core.reflect.util;

import com.cadenzauk.core.lang.RuntimeInstantiationException;
import com.cadenzauk.core.reflect.Factory;
import org.junit.Test;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static com.cadenzauk.core.testutil.FluentAssert.calling;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FieldUtilTest {
    @Test
    public void cannotInstantiate() {
        calling(() -> Factory.forClass(FieldUtil.class).get())
            .shouldThrow(RuntimeException.class)
            .withCause(InvocationTargetException.class)
            .withCause(RuntimeInstantiationException.class);
    }

    @Test
    public void set() throws Exception {
        ClassWithStringField target = new ClassWithStringField();
        String value = UUID.randomUUID().toString();
        Field field = ClassUtil.getDeclaredField(ClassWithStringField.class, "stringField");

        FieldUtil.set(field, target, value);

        assertThat(target.getStringField(), equalTo(value));
    }

    @Test
    public void get() throws Exception {
        ClassWithStringField target = new ClassWithStringField();
        String value = UUID.randomUUID().toString();
        target.setStringField(value);
        Field field = ClassUtil.getDeclaredField(ClassWithStringField.class, "stringField");

        Object result = FieldUtil.get(field, target);

        assertThat(result, equalTo(value));
    }

    @Test
    public void hasAnnotationPresent() throws Exception {
        Field field = ClassUtil.getDeclaredField(ClassWithStringField.class, "stringField");

        boolean result = FieldUtil.hasAnnotation(XmlElement.class, field);

        assertThat(result, is(true));
    }

    @Test
    public void hasAnnotationNotPresent() throws Exception {
        Field field = ClassUtil.getDeclaredField(ClassWithStringField.class, "stringField");

        boolean result = FieldUtil.hasAnnotation(XmlAttribute.class, field);

        assertThat(result, is(false));
    }

    private static class ClassWithStringField {
        @XmlElement
        private String stringField;

        public String getStringField() {
            return stringField;
        }

        public void setStringField(String stringField) {
            this.stringField = stringField;
        }
    }

}
