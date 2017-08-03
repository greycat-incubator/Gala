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
package gala.var;


import greycat.Action;
import greycat.TaskContext;
import greycat.struct.Buffer;

public class ActionInjectAsVar implements Action {

    //Name declared for the Plugin
    public static final String NAME = "injectAsVar";

    private final String _variable;
    private final Object _toInject;

    public ActionInjectAsVar(final String p_variable, final Object toInject) {
        _variable = p_variable;
        _toInject = toInject;
    }

    public void eval(final TaskContext taskContext) {
        taskContext.defineVariable(_variable, _toInject);
        taskContext.continueTask();
    }

    public void serialize(Buffer builder) {
        throw new RuntimeException("inject as var remote action not managed yet!");
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String toString() {
        return "injectAsVar(" + _toInject.toString() + "," + _variable + ")";
    }
}
