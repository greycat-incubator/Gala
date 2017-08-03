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
package gala.helper;

import greycat.Action;
import greycat.Constants;
import greycat.Node;
import greycat.TaskContext;
import greycat.struct.Buffer;

public class ActionKeepFirstResult implements Action {

    //Name declared for the Plugin
    public static final String NAME = "keepFirstResult";

    public ActionKeepFirstResult() {
        super();
    }

    public void eval(final TaskContext taskContext) {
        if (taskContext.result().size() > 0) {
            Object resultToKeep = taskContext.result().get(0);
            if (resultToKeep instanceof Node) {
                taskContext.continueWith(taskContext.wrapClone(resultToKeep));
            } else {
                taskContext.continueWith(taskContext.wrap(resultToKeep));
            }
        } else taskContext.continueTask();
    }

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
