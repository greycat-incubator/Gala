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

import gala.helper.ActionListOfIndexedNodes;
import greycat.Action;
import greycat.Task;
import gala.cf.ActionIfEmptyThen;
import gala.cf.ActionIfEmptyThenElse;
import gala.cf.ActionIfNotEmptyThen;
import gala.cf.ActionIfNotEmptyThenElse;
import gala.helper.ActionCount;
import gala.helper.ActionIncrement;
import gala.helper.ActionKeepFirstResult;
import gala.var.ActionInjectAsVar;
import gala.worldtime.ActionCheckForFuture;
import gala.worldtime.ActionExecuteAtWorldAndTime;
import gala.worldtime.ActionReadUpdatedTimeVar;

public class Actions {

    public static Action count() {
        return new ActionCount();
    }

    public static Action checkForFuture() {
        return new ActionCheckForFuture();
    }

    public static Action ifEmptyThen(final Task then) {
        return new ActionIfEmptyThen(then);
    }

    public static Action ifNotEmptyThen(final Task then) {
        return new ActionIfNotEmptyThen(then);
    }

    public static Action ifEmptyThenElse(final Task then, final Task _else) {
        return new ActionIfEmptyThenElse(then, _else);
    }

    public static Action ifNotEmptyThenElse(final Task then, final Task _else) {
        return new ActionIfNotEmptyThenElse(then, _else);
    }

    public static Action injectAsVar(final String p_variable, final Object obj) {
        return new ActionInjectAsVar(p_variable, obj);
    }

    public static Action increment(final String p_variable, final int p_incrementValue) {
        return new ActionIncrement(p_variable, p_incrementValue);
    }

    public static Action keepFirstResult() {
        return new ActionKeepFirstResult();
    }

    public static Action executeAtWorldAndTime(final String world, final String time, final Task then) {
        return new ActionExecuteAtWorldAndTime(world, time, then);
    }

    public static Action readUpdatedTimeVar(final String name) {
        return new ActionReadUpdatedTimeVar(name);
    }

    public static Action listOfIndexedNodes(final String relation) {
        return new ActionListOfIndexedNodes(relation);
    }

}
