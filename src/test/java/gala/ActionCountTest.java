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

import gala.helper.ActionCount;
import greycat.ActionFunction;
import greycat.TaskContext;
import org.junit.jupiter.api.Test;

import static greycat.Tasks.newTask;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ActionCountTest extends ActionTest {

    @Test
    public void testEmpty() {
        initGraph();
        final int[] counter = {0};
        newTask()
                .action(ActionCount.NAME)
                .thenDo(new ActionFunction() {
                    public void eval(TaskContext context) {

                        assertEquals(context.result().get(0), 0);
                        counter[0]++;
                        context.continueTask();
                    }
                })
                .execute(graph, null);
        assertEquals(1, counter[0]);
        removeGraph();
    }

    @Test
    public void testComplex() {
        initGraph();
        final int[] counter = {0};
        newTask()
                .readIndex("nodes")
                .action(ActionCount.NAME)
                .thenDo(new ActionFunction() {
                    public void eval(TaskContext context) {

                        assertEquals(context.result().get(0), 3);
                        counter[0]++;
                        context.continueTask();
                    }
                })
                .execute(graph, null);
        assertEquals(1, counter[0]);
        removeGraph();
    }


}