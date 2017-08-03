/**
 * Copyright 2017 the Greycat Tasks and Actions authors.  All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gala;

import gala.worldtime.ActionCheckForFuture;
import greycat.ActionFunction;
import greycat.TaskContext;
import greycat.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static greycat.Tasks.newTask;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ActionCheckForFutureTest extends ActionTest {

    @BeforeEach
    public void runBeforeTestMethod() {
        initGraph();
        newTask()
                .travelInTime("4")
                .readIndex("roots")
                .setAttribute("modify", Type.BOOL, "true")
                .execute(graph, null);
    }


    @AfterEach
    public void runAfterTestMethod() {
        removeGraph();
    }


    @Test
    public void testoneNodeWithoutFuture() {
        final boolean[] bool = {false};
        final int[] count = {0};
        newTask()
                .travelInTime("5")
                .readIndex("roots")
                .action(ActionCheckForFuture.NAME)
                .thenDo(new ActionFunction() {
                    public void eval(TaskContext ctx) {
                        bool[0] = true;
                        count[0]++;
                        ctx.continueTask();
                    }
                }).execute(graph, null);

        assertEquals(count[0], 1);
        assert (bool[0]);
    }

    @Test
    public void testoneNodeWithFuture() {
        final boolean[] bool = {true};
        final int[] count = {0};
        newTask()
                .travelInTime("3")
                .readIndex("roots")
                .action(ActionCheckForFuture.NAME)
                .thenDo(new ActionFunction() {
                    public void eval(TaskContext ctx) {
                        bool[0] = false;
                        count[0]++;
                        ctx.continueTask();
                    }
                }).execute(graph, null);

        assertEquals(count[0], 0);
        assert (bool[0]);
    }

    @Test
    public void testMixFuture() {
        final boolean[] bool = {true};
        final int[] count = {0};
        newTask()
                .travelInTime("3")
                .readIndex("nodes")
                .action(ActionCheckForFuture.NAME)
                .thenDo(new ActionFunction() {
                    public void eval(TaskContext ctx) {
                        bool[0] = false;
                        count[0]++;
                        ctx.continueTask();
                    }
                }).execute(graph, null);

        assertEquals(count[0], 0);
        assert (bool[0]);
    }

}