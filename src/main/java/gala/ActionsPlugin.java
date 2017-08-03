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

import gala.cf.ActionIfEmptyThen;
import gala.cf.ActionIfEmptyThenElse;
import gala.cf.ActionIfNotEmptyThen;
import gala.cf.ActionIfNotEmptyThenElse;
import gala.helper.ActionCount;
import gala.helper.ActionIncrement;
import gala.helper.ActionKeepFirstResult;
import gala.worldtime.ActionCheckForFuture;
import gala.worldtime.ActionExecuteAtWorldAndTime;
import gala.worldtime.ActionReadUpdatedTimeVar;
import greycat.Action;
import greycat.Graph;
import greycat.Task;
import greycat.Type;
import greycat.plugin.ActionFactory;
import greycat.plugin.Plugin;

import static gala.Actions.*;

public class ActionsPlugin implements Plugin {
    @Override
    public void start(Graph graph) {
        //Count
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionCount.NAME)
                .setParams()
                .setDescription("Count the number of result in the current context and put it as the new result")
                .setFactory(new ActionFactory() {
                                public Action create(Object[] params) {
                                    return count();
                                }
                            }

                );


        //Check for future
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionCheckForFuture.NAME)
                .setParams()
                .setDescription("Checks if nodes present in the current context result have modification in the future. If yes an exception is returned.")
                .setFactory(new ActionFactory() {
                    public Action create(Object[] params) {
                        return checkForFuture();
                    }
                });


        //Execute At World ANd Time
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionExecuteAtWorldAndTime.NAME)
                .setParams(Type.STRING, Type.STRING, Type.TASK)
                .setDescription("execute a given task at a given world and time and then come back to the current time")
                .setFactory(new ActionFactory() {
                    public Action create(Object[] params) {
                        return executeAtWorldAndTime((String) params[0], (String) params[1], (Task) params[2]);
                    }
                });


        //IF empty then
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionIfEmptyThen.NAME)
                .setParams(Type.TASK)
                .setDescription("launch the given task if the current result is empty")
                .setFactory(new ActionFactory() {
                    public Action create(Object[] params) {
                        return ifEmptyThen((Task) params[0]);
                    }
                });


        //If not empty then
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionIfNotEmptyThen.NAME)
                .setParams(Type.TASK)
                .setDescription("launch the given task if the current result is not empty")
                .setFactory(new ActionFactory() {
                    public Action create(Object[] params) {
                        return ifNotEmptyThen((Task) params[0]);
                    }
                });

        //IF empty then else
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionIfEmptyThenElse.NAME)
                .setParams(Type.TASK, Type.TASK)
                .setDescription("launch the first task if the current result is empty, else launch the second task")
                .setFactory(new ActionFactory() {
                    public Action create(Object[] params) {
                        return ifEmptyThenElse((Task) params[0], (Task) params[1]);
                    }
                });

        //If not empty then else
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionIfNotEmptyThenElse.NAME)
                .setParams(Type.TASK, Type.TASK)
                .setDescription("launch the first task if the current result is not empty, else launch the second task")
                .setFactory(new ActionFactory() {
                    public Action create(Object[] params) {
                        return ifNotEmptyThenElse((Task) params[0], (Task) params[1]);
                    }
                });

        //Increment
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionIncrement.NAME)
                .setParams(Type.STRING, Type.INT)
                .setDescription("increment the current value of a variable by X")
                .setFactory(new ActionFactory() {
                    public Action create(Object[] params) {
                        return increment((String) params[0], (Integer) params[1]);
                    }
                });


        //Keep First
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionKeepFirstResult.NAME)
                .setParams()
                .setDescription("modify the current context result to only keep its first element")
                .setFactory(new ActionFactory() {
                    public Action create(Object[] params) {
                        return keepFirstResult();
                    }
                });

        //Read Updated Time Var
        graph.actionRegistry()
                .getOrCreateDeclaration(ActionReadUpdatedTimeVar.NAME)
                .setParams(Type.STRING)
                .setDescription("Put the content of a var in the current result, if the var contains nodes they are put to the current context time")
                .setFactory(new ActionFactory() {
                    public Action create(Object[] params) {
                        return readUpdatedTimeVar((String) params[0]);
                    }
                });
    }

    @Override
    public void stop() {

    }
}
