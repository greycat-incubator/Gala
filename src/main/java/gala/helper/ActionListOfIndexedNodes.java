package gala.helper;

import greycat.*;
import greycat.internal.task.TaskHelper;
import greycat.struct.Buffer;

import java.util.ArrayList;
import java.util.List;

public class ActionListOfIndexedNodes implements Action {

    public static final String NAME = "listOfIndexedNodes";

    private final String _indexedRelation;


    public ActionListOfIndexedNodes(final String p_indexedRelation) {
        super();
        _indexedRelation = p_indexedRelation;

    }

    public void eval(final TaskContext taskContext) {
        TaskResult<Node> nodes = taskContext.resultAsNodes();
        TaskResult taskResult = taskContext.newResult();
        for (int i = 0; i < nodes.size(); i++) {
            Node currentNode = nodes.get(i);
            Index index = currentNode.getIndex(_indexedRelation);
            if (index != null) {
                taskResult.add(index.all());
            }
        }
        taskContext.continueWith(taskResult);
    }

    @Override
    public void serialize(Buffer builder) {
        builder.writeString(NAME);
        builder.writeChar(Constants.TASK_PARAM_OPEN);
        TaskHelper.serializeString(_indexedRelation, builder, false);
        builder.writeChar(Constants.TASK_PARAM_CLOSE);
    }

    @Override
    public String name() {
        return NAME;
    }

}
