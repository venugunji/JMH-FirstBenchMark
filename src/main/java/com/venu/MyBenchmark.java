/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.venu;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyBenchmark {

    @State(Scope.Thread)
    public static class MyStateForSet {
        public Set<String> NO_TID_GENERATE_REQEUEST_MTI_SET =
                Stream.of("FINQ", "ADVQ", "CRNO", "MGTQ").collect(Collectors.toCollection(HashSet::new));
    }
    @State(Scope.Thread)
    public static class MyStateForList {
        public List<String> NO_TID_GENERATE_REQEUEST_MTI_LIST =
                Stream.of("FINQ", "ADVQ", "CRNO", "MGTQ").collect(Collectors.toCollection(ArrayList::new));
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testMethod_hashset_first_element(MyStateForSet myStateForSet, Blackhole blackhole) {
        boolean result = myStateForSet.NO_TID_GENERATE_REQEUEST_MTI_SET.contains("FINQ");
        blackhole.consume(result);

    }
    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testMethod_hashset_last_element(MyStateForSet myStateForSet, Blackhole blackhole) {
        boolean result = myStateForSet.NO_TID_GENERATE_REQEUEST_MTI_SET.contains("MGTQ");
        blackhole.consume(result);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testMethod_arraylist_first_element(MyStateForList myStateForList, Blackhole blackhole) {
        boolean result = myStateForList.NO_TID_GENERATE_REQEUEST_MTI_LIST.contains("FINQ");
        blackhole.consume(result);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testMethod_arraylist_last_element(MyStateForList myStateForList, Blackhole blackhole) {
        boolean result = myStateForList.NO_TID_GENERATE_REQEUEST_MTI_LIST.contains("MGTQ");
        blackhole.consume(result);
    }

}
