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
import greycat.TaskContext;
import greycat.internal.task.TaskHelper;
import greycat.struct.Buffer;

public class ActionIncrement implements Action {

    //Name declared for the Plugin
    public static final String NAME = "increment";

    private final String _variable;
    private final int _increment;

    public ActionIncrement(final String p_variable, final int p_increment) {
        super();
        _variable = p_variable;
        _increment = p_increment;
    }

    public void eval(final TaskContext taskContext) {
        int currentValue = taskContext.intVar(_variable);
        taskContext.setVariable(_variable, currentValue + _increment);
        taskContext.continueTask();
    }

    public void serialize(Buffer builder) {
        builder.writeString(NAME);
        builder.writeChar(Constants.TASK_PARAM_OPEN);
        TaskHelper.serializeString(_variable, builder, false);
        builder.writeChar(Constants.TASK_PARAM_SEP);
        TaskHelper.serializeString(Integer.toString(_increment), builder, false);
        builder.writeChar(Constants.TASK_PARAM_CLOSE);
    }

    @Override
    public String name() {
        return NAME;
    }

}
