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
package gala.worldtime;

import greycat.*;
import greycat.struct.Buffer;


import java.util.ArrayList;
import java.util.List;

import static greycat.Constants.END_OF_TIME;

public class ActionCheckForFuture implements Action {

    //Name declared for the Plugin
    public static final String NAME = "checkForFuture";

    @Override
    public void eval(final TaskContext ctx) {
        Long time = ctx.time();
        Object node;
        final boolean[] bool = {true};
        final List<Long> nodeIds = new ArrayList<>();
        TaskResultIterator it = ctx.resultAsNodes().iterator();
        while ((node = it.next()) != null) {
            final Node node1 = (Node) node;
            node1.timepoints(
                    time + 1, END_OF_TIME, result -> {
                        if (result.length != 0) {
                            bool[0] = false;
                            nodeIds.add(node1.id());
                        }
                    }
            );
        }
        if (bool[0]) {
            ctx.continueTask();
        } else {
            ctx.endTask(ctx.result(), new RuntimeException("Trying to modify the past of Node(s): " + nodeIds));
        }
    }

    @Override
    public void serialize(Buffer builder) {
        builder.writeString(NAME);
        builder.writeChar(Constants.TASK_PARAM_OPEN);
        builder.writeChar(Constants.TASK_PARAM_CLOSE);

    }

    @Override
    public String name() {
        return NAME;
    }
}
