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
package gala.cf;

import greycat.*;
import greycat.internal.task.CF_Action;
import greycat.internal.task.CoreTask;
import greycat.plugin.SchedulerAffinity;
import greycat.struct.Buffer;

import java.util.Map;

public class ActionIfEmptyThen extends CF_Action {

    //Name declared for the Plugin
    public static final String NAME = "ifEmptyThen";

    private Task _action;

    public ActionIfEmptyThen(final Task action) {
        super();
        this._action = action;
    }

    public Task[] children() {
        Task[] children_tasks = new Task[1];
        children_tasks[0] = _action;
        return children_tasks;
    }

    public void cf_serialize(Buffer builder, Map<Integer, Integer> dagIDS) {
        builder.writeString(NAME);
        builder.writeChar(Constants.TASK_PARAM_OPEN);
        final CoreTask castedAction = (CoreTask) _action;
        final int castedActionHash = castedAction.hashCode();
        if (dagIDS == null || !dagIDS.containsKey(castedActionHash)) {
            castedAction.serialize(builder, dagIDS);
        } else {
            builder.writeString("" + dagIDS.get(castedActionHash));
        }
        builder.writeChar(Constants.TASK_PARAM_CLOSE);
    }

    public void eval(final TaskContext taskContext) {
        if (taskContext.result() == null) {
            _action.executeFrom(taskContext, taskContext.result(), SchedulerAffinity.SAME_THREAD, new Callback<TaskResult>() {
                public void on(TaskResult res) {
                    taskContext.continueWith(res);
                }
            });
        } else if (taskContext.result().size() == 0) {
            _action.executeFrom(taskContext, taskContext.result(), SchedulerAffinity.SAME_THREAD, new Callback<TaskResult>() {
                public void on(TaskResult res) {
                    taskContext.continueWith(res);
                }
            });
        } else taskContext.continueTask();
    }

    @Override
    public String name() {
        return NAME;
    }

}
