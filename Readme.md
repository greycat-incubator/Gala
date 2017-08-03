# Greycat AdditionaL Actions

The Greycat AdditionaL Actions (gala) project is a plugin for [Greycat](https://github.com/datathings/greycat) providing additional actions to the one offered by Greycat.

![version](https://img.shields.io/badge/version-11--Snapshot-blue.svg)


## Actions?

Actions are reusable elements hiding low-level, asynchronous task primitives behind an expressive API.
 Actions allows to traverse and manipulate the graph and can be chained to form a task. 


### Why additional Actions?

A great number of action are already implemented and available in Greycat, please refer to the  greycat.internal.task package of Greycat. 

However, thinking and implementing every possible actions would: 
1) be impossible 
2) drastically increase the size of the project. 

That's why only basic, i.e., atomic, actions are implemented. 

Yet, users might need more composed actions like Injecting an object as a variable or Putting the result of a get action in a variable instead of in the result. 

Thus this project has the following objectives: 
 
  * offer more complex general purpose actions 
  * provide an example of Actions development to developers eager to develop some for their own need :)

### What is currently offered:

Currently offered actions are: 

* [Check For Future](doc/CheckForFuture.md)
* [Count](doc/Count.md)
* [Execute at World and Time](doc/ExecuteAtWorldAndTime.md)
* [If empty then](doc/IfEmptyThen.md)
* [If empty then else](doc/IfEmptyThenElse.md)
* [If not empty then](doc/IfNotEmptyThen.md)
* [If not empty then else](doc/IfNotEmptyThenElse.md)
* [Inject as Var](doc/InjectAsVar.md)
* [Increment](doc/Increment.md)
* [Keep first result](doc/KeepFirstResult.md)
* [Flip Vars](doc/FlipVars.md)
* [Flip Var And Result](doc/FlipVarAndResult.md)
* [Read Updated Time Var](doc/ReadUpdatedTimeVar.md)
 

## How to use it?

This plugin can be used the same way as other Greycat plugins.

1 . Declare the plugin in your Graph Build

```java
Graph g = new GraphBuilder()
	.withPlugin(new ActionsPlugin())
	.build();
```

2 . Then call action(MyAction.Name) in your task chain or use then(Actions.myaction()) to launch it.

```java
newTask()
.action(ActionIncrement.NAME,"myvar","1")
.then(Actions.count())
```

Please Note that the first option is usable for almost all of the actions and works seamlessly in java or javascript. On the other hand, a function like injectAsVar that require a native object can only be called using then and can not be used from javascript.

3 . That's all =)