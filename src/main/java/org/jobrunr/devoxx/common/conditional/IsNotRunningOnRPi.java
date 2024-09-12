package org.jobrunr.devoxx.common.conditional;

import com.pi4j.Pi4J;
import com.pi4j.boardinfo.definition.BoardModel;
import com.pi4j.context.Context;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class IsNotRunningOnRPi implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Context pi4jContext = Pi4J.newAutoContext();
        return pi4jContext.boardInfo().getBoardModel() == BoardModel.UNKNOWN;
    }
}
